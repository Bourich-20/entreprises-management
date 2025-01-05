package com.entreprises.management.division.mapper;


import com.entreprises.management.division.dtos.DivisionResponse;
import com.entreprises.management.division.dtos.ServiceResponse;
import com.entreprises.management.division.entitie.Division;
import com.entreprises.management.division.entitie.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DivisionMapper {


    DivisionResponse toDivisionResponse(Division division);

    ServiceResponse toServiceResponse(Service service);
}