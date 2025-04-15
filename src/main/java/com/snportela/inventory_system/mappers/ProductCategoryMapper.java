package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.dtos.ProductCategoryDto;
import com.snportela.inventory_system.domain.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {

    ProductCategoryDto productCategoryToDto(ProductCategory productCategory);

    ProductCategory dtoToProductCategory(ProductCategoryDto productCategoryDto);
}
