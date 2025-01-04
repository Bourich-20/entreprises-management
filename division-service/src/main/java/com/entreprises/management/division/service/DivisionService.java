package com.entreprises.management.division.service;

import com.entreprises.management.division.dtos.DivisionRequest;
import com.entreprises.management.division.dtos.DivisionResponse;
import com.entreprises.management.division.dtos.ServiceResponse;

import java.util.List;

public interface DivisionService {
    DivisionResponse createDivision(DivisionRequest divisionRequest);

    DivisionResponse updateDivision(Long id, DivisionRequest divisionRequest);

    DivisionResponse getDivisionById(Long id);

    void deleteDivision(Long id);

    List<ServiceResponse> getServicesByDivision(Long divisionId);

    void assignChiefToDivision(Long divisionId, Long employeeId);
}