package com.iexceed.marketplacesrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iexceed.marketplacesrv.model.BuyerOrders;

public interface BuyerOrdersRepository extends JpaRepository<BuyerOrders, String> {

	@Query("SELECT b FROM BuyerOrders b WHERE b.custId = :custId")
	List<BuyerOrders> findByCustId(@Param("custId") String custId);
	
	BuyerOrders findByCustIdAndOrderId(String custId, String orderId);

	@Modifying
    @Query("UPDATE BuyerOrders b SET b.status = ?2 WHERE b.orderId = ?1")
    int updateStatusByOrderId(String orderId, String status);
}
