package com.mstransferenciafinanceira.repository;

import com.mstransferenciafinanceira.entity.TaxaTranferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TaxaTransferenciaRepository extends JpaRepository<TaxaTranferencia, Long> {

    @Query(value = "select * from tb_taxa  where  :days >= de and " +
            " :days <= ate", nativeQuery = true)
    TaxaTranferencia returnTaxa(int days);

    @Query(value = "select taxa from tb_taxa  where  :days >= de and " +
            " :days <= ate", nativeQuery = true)
    Double returnValorTaxa(int days);

}
