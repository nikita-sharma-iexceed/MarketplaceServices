package com.iexceed.marketplacesrv.model;

import com.iexceed.marketplacesrv.converters.CategoryDataConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_APZS_SERVICE_CATEGORIES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Categories {

    @Id
    @Column(name = "c_id")
    private String cId;

    @Column(name = "category_data", columnDefinition = "TEXT")
    @Convert(converter = CategoryDataConverter.class)
    private CategoryData categoryData;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CategoryData {
        private String category;
        private String imgUrl;
        private String categoryDesc;
    }
}