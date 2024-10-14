package com.iexceed.marketplacesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.marketplacesrv.model.SellerStages;

public interface SellerStagesRepository extends JpaRepository<SellerStages, Long> {

	SellerStages findByOrderIdAndStageId(String orderId, String stageId);

}
