package com.mstransferenciafinanceira.repository;

import com.mstransferenciafinanceira.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

}
