package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> findReport(String name,
									String minDate, String maxDate, Pageable pageable) {

			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			LocalDate min, max;

			if(minDate.equals("") && maxDate.equals("")) {
				max = today;
				min = today.minusYears(1L);
			}
			else if(minDate.equals("")){
				min = LocalDate.parse(maxDate).minusYears(1L);
				max = LocalDate.parse(maxDate);
			}
			else if(maxDate.equals("")){
				max = today;
				min = min = LocalDate.parse(minDate);
			}
			else{
				min = LocalDate.parse(minDate);
				max = LocalDate.parse(maxDate);
			}

			Page<Sale> result = repository.report12Months(min, max, name, pageable);
			return result.map(x -> new SaleMinDTO(x));
	}
}