package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.dtos.ProductDto;
import com.snportela.inventory_system.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDto productToDto(Product product);

    Product dtoToProduct(ProductDto productDto);
}
