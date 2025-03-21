package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.dto.ProductDto;
import com.snportela.inventory_system.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDto productToDto(Product product);

    Product dtoToProduct(ProductDto productDto);
}
