package com.mstransferenciafinanceira.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="tb_taxa")
public class TaxaTranferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "de")
    private int de;

    @Column(name = "ate")
    private int ate;

    @Column(name = "taxa")
    private BigDecimal taxa;
}
