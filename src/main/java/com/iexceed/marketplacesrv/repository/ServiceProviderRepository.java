package com.iexceed.marketplacesrv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iexceed.marketplacesrv.model.ServiceProviderInfo;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProviderInfo, Long> {

	Optional<ServiceProviderInfo> findBySpNameContaining(String spName);

	Optional<ServiceProviderInfo> findByCustId(String custId);

	@Query("SELECT sp FROM ServiceProviderInfo sp JOIN sp.serviceListings sl WHERE sl.category = :category")
	List<ServiceProviderInfo> findByServiceListingsCategory(@Param("category") String category);

	@Query("SELECT sp FROM ServiceProviderInfo sp JOIN sp.serviceListings s WHERE s.category LIKE %:keyword% OR s.serviceTitle LIKE %:keyword%")
	List<ServiceProviderInfo> findByServiceListingsCategoryOrServiceTitle(@Param("keyword") String keyword);

}
