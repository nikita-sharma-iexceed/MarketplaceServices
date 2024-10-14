package com.iexceed.marketplacesrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iexceed.marketplacesrv.model.ServiceListings;
import com.iexceed.marketplacesrv.model.ServiceProviderInfo;
import com.iexceed.marketplacesrv.response.ServiceListingSearchResponse;

@Repository
public interface ServiceListingsRepository extends JpaRepository<ServiceListings, Long> {

//	@Query("SELECT s FROM ServiceListings s WHERE s.category LIKE %:keyword% OR s.serviceTitle LIKE %:keyword%")
//	List<ServiceListings> findByCategoryOrServiceTitle(@Param("keyword") String keyword);

	@Query("SELECT new com.iexceed.marketplacesrv.response.ServiceListingSearchResponse(s, sp.spid, sp.spName, sp.profileImgPath) "
			+ "FROM ServiceProviderInfo sp JOIN sp.serviceListings s "
			+ "WHERE s.category LIKE %:keyword% OR s.serviceTitle LIKE %:keyword%")
	List<ServiceListingSearchResponse> findListingsByCategoryOrTitleWithProviderInfo(@Param("keyword") String keyword);

	List<ServiceListings> findByServiceProviderId(ServiceProviderInfo sp);
}
