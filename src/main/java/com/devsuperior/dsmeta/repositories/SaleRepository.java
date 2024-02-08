package com.devsuperior.dsmeta.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT obj " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :min AND :max " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<Sale> report12Months(LocalDate min, LocalDate max, String name, Pageable pageable);
}
