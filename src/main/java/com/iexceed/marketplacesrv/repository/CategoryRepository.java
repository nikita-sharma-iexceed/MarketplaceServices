package com.iexceed.marketplacesrv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iexceed.marketplacesrv.model.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, String> {
	
}
