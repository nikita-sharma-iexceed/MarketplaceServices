package com.iexceed.marketplacesrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iexceed.marketplacesrv.model.ServiceListings;
import com.iexceed.marketplacesrv.model.WorkOrderListing;

public interface WolRepository extends JpaRepository<WorkOrderListing, Long> {

	List<WorkOrderListing> findByServiceId(ServiceListings service);
}
