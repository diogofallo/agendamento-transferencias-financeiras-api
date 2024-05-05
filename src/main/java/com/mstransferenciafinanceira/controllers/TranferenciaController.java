package com.mstransferenciafinanceira.controllers;

import com.mstransferenciafinanceira.dto.TransferenciaDTO;
import com.mstransferenciafinanceira.mapper.ReceivableMapper;
import com.mstransferenciafinanceira.response.DPage;
import com.mstransferenciafinanceira.response.Response;
import com.mstransferenciafinanceira.service.TransferenciaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamento")
public class TranferenciaController {

    @Autowired
    TransferenciaService transferenciaService;

    @Autowired
    ReceivableMapper mapper;

    @PostMapping()
    public ResponseEntity<Response> creatNewTask(@Valid @RequestBody TransferenciaDTO transferenciaDTO) throws Exception {
        Response response = new Response();
        transferenciaService.newAgtoendamen(transferenciaDTO);

        response.setMsgRetorno("Agendamento feito com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/listaTransferencias")
    public ResponseEntity<DPage<TransferenciaDTO>>getListTransfers(HttpServletRequest request,
                                                                          @RequestParam(defaultValue = "0", required = false) int page,
                                                                          @RequestParam(defaultValue = "20", required = false) int size) {
        Page<TransferenciaDTO> response;
        response = transferenciaService.returnAllTransfers(request, 0, 20);

        DPage<TransferenciaDTO> dResponse = mapper.toDPageResponse(response);
        return new ResponseEntity<>(dResponse, HttpStatus.OK);
    }


}
