package com.mstransferenciafinanceira.mapper;


import com.mstransferenciafinanceira.dto.TransferenciaDTO;
import com.mstransferenciafinanceira.entity.Transferencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    TransferenciaMapper INSTANCE = Mappers.getMapper(TransferenciaMapper.class);

    @Mapping(target = "dataTransferencia", ignore = true)
    @Mapping(target = "dataAgendamento", ignore = true)
    @Mapping(target = "taxa", ignore = true)
    Transferencia toEntity(TransferenciaDTO dto);

    @Mapping(target = "dataTransferencia", expression = "java(entity.getDataTransferencia().toString())")
    TransferenciaDTO toDTO(Transferencia entity);

}
