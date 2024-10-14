package com.iexceed.marketplacesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.marketplacesrv.model.QuoteReceivedDetails;

public interface QuoteReceivedRepository extends JpaRepository<QuoteReceivedDetails, Long> {

	QuoteReceivedDetails findByOrderId(String orderId);
}
