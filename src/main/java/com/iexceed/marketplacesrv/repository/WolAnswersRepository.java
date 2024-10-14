package com.iexceed.marketplacesrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.marketplacesrv.model.SellerOrders;
import com.iexceed.marketplacesrv.model.WolAnswers;

public interface WolAnswersRepository extends JpaRepository<WolAnswers, Long> {

	List<WolAnswers> findBySellerOrders(SellerOrders orderId);
}
