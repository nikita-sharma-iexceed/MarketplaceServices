package com.iexceed.marketplacesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iexceed.marketplacesrv.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
