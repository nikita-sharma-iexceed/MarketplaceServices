package com.iexceed.marketplacesrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iexceed.marketplacesrv.model.SellerOrders;

public interface SellerOrdersRepository extends JpaRepository<SellerOrders, String> {

	@Query("SELECT b FROM SellerOrders b WHERE b.custId = :custId")
	List<SellerOrders> findByCustId(@Param("custId") String custId);

    List<SellerOrders> findByCustIdAndServiceId(String custId, Long serviceId);
	
}
