package com.mstransferenciafinanceira.mapper;


import com.mstransferenciafinanceira.dto.TransferenciaDTO;
import com.mstransferenciafinanceira.response.DPage;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ReceivableMapper {

    default DPage<TransferenciaDTO> toDPageResponse(Page<TransferenciaDTO> servico){
        return PageMapper.toDPage(servico);
    }

}
