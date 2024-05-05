package com.mstransferenciafinanceira.service;

import com.mstransferenciafinanceira.dto.TransferenciaDTO;
import com.mstransferenciafinanceira.entity.TaxaTranferencia;
import com.mstransferenciafinanceira.entity.Transferencia;
import com.mstransferenciafinanceira.exception.ResourceNotFoundException;
import com.mstransferenciafinanceira.repository.TaxaTransferenciaRepository;
import com.mstransferenciafinanceira.repository.TransferenciaRepository;
import com.mstransferenciafinanceira.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    TaxaTransferenciaRepository taxaTransferenciaRepository;

    @Autowired
    ModelMapper modelMapper;

    public TransferenciaDTO newAgtoendamen(TransferenciaDTO dto) throws ParseException {
        Transferencia vo = modelMapper.map(dto, Transferencia.class);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAgendamento = LocalDate.now();
        LocalDate dataTransferencia = LocalDate.parse(dto.getDataTransferencia(), formato);

        Double valorTaxaTransf = this.applyValueRate(vo);

        vo.setTaxa((Utils.arredondarDecimal(BigDecimal.valueOf(valorTaxaTransf))));
        vo.setDataAgendamento(dataAgendamento);
        vo.setDataTransferencia(dataTransferencia);

        Transferencia salvo = transferenciaRepository.save(vo);
        return modelMapper.map(vo, TransferenciaDTO.class);
    }

    public Page<TransferenciaDTO> returnAllTransfers(HttpServletRequest request, int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);;
        Page<Transferencia> result = transferenciaRepository.findAll(pageRequest);
        List<TransferenciaDTO> response = result
                .getContent()
                .stream()
                .map(p -> modelMapper.map(p , TransferenciaDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(response, result.getPageable(), result.getTotalElements());
    }

    public Optional<TaxaTranferencia> findByTaxa(int days) {
        return Optional.ofNullable(this.taxaTransferenciaRepository.returnTaxa(days));
    }

    private Double applyValueRate(Transferencia transferencia){
        Double valorPercentual, valorTaxaTransf;

        Double valorTransferencia = transferencia.getValorTransferencia().doubleValue();
        Double taxa = this.returnTaxa(transferencia.getDataTransferencia(), transferencia.getDataAgendamento());

        valorPercentual = taxa / 100;
        valorTaxaTransf = valorPercentual * valorTransferencia;

        return  valorTaxaTransf;
    }

    private Double returnTaxa(LocalDate dateTransfer, LocalDate dateScheduling){
        int days = Utils.returnDays(dateTransfer, dateScheduling);

        Optional<TaxaTranferencia> taxa = this.findByTaxa(days);
        if(!taxa.isPresent())
            throw new ResourceNotFoundException(String.format("Agendamendo da Transferência não permitida!"));

        Double valueRate = taxaTransferenciaRepository.returnValorTaxa(days);
        return valueRate;
    }


}
