package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesSumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.amount, obj.date, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :min AND :max " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<SaleMinDTO> searchReport(LocalDate min, LocalDate max, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesSumDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :min AND :max "
            + "GROUP BY obj.seller.name")
    List<SalesSumDTO> searchSummary(LocalDate min, LocalDate max);

}