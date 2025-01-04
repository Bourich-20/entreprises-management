package com.entreprises.management.employee_service.mapper;


import com.entreprises.management.employee_service.dtos.EmployeeRequest;
import com.entreprises.management.employee_service.dtos.EmployeeResponse;
import com.entreprises.management.employee_service.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeRequest request);
    EmployeeResponse toResponse(Employee entity);

    void updateEntityFromRequest(EmployeeRequest request, @MappingTarget Employee entity);
}