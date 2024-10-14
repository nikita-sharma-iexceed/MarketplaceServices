package com.iexceed.marketplacesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.marketplacesrv.model.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

}
