package com.iexceed.marketplacesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.marketplacesrv.model.BuyerStages;

public interface BuyerStageRepository extends JpaRepository<BuyerStages, Long> {

	BuyerStages findByOrderIdAndStageId(String orderId, String stageId);
}
