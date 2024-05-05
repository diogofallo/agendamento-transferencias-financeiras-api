package com.mstransferenciafinanceira.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name="tb_transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "conta_origem")
    private String contaOrigem;

    @Column(name = "conta_destino")
    private String contaDestino;

    @Column(name = "valor_transferencia")
    private BigDecimal valorTransferencia;

    @Column(name = "taxa")
    private BigDecimal taxa;

    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;

    @Column(name = "data_agendamento")
    private LocalDate dataAgendamento;

}
