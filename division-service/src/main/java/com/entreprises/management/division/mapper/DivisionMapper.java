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

    @Mapping(source = "chief.nom", target = "chiefName")
    @Mapping(source = "chief.id", target = "chiefId")
    ServiceResponse toServiceResponse(Service service);
}