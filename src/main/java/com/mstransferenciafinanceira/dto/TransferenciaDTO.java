package com.mstransferenciafinanceira.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    @NotNull(message = "Informe a conta origem!")
    private String contaOrigem;

    private String contaDestino;

    private BigDecimal valorTransferencia;

    private BigDecimal taxa;

    private String dataTransferencia;

    private String dataAgendamento;

}
