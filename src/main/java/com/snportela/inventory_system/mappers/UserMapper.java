package com.snportela.inventory_system.mappers;

import com.snportela.inventory_system.domain.User;
import com.snportela.inventory_system.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto userToDto(User user);

    User dtoToUser(UserDto userDto);
}
