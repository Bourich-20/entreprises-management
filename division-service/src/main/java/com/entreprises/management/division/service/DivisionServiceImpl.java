package com.entreprises.management.division.service;
import com.entreprises.management.division.client.EmployeeClient;
import com.entreprises.management.division.dtos.DivisionRequest;
import com.entreprises.management.division.dtos.EmployeeDTO;
import com.entreprises.management.division.dtos.ServiceResponse;
import com.entreprises.management.division.entitie.Division;
import com.entreprises.management.division.mapper.DivisionMapper;


import com.entreprises.management.division.dtos.DivisionResponse;
import com.entreprises.management.division.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private DivisionMapper divisionMapper;


    @Override
    public DivisionResponse createDivision(DivisionRequest divisionRequest) {
        Division division = new Division();
        division.setName(divisionRequest.name());
        Division savedDivision = divisionRepository.save(division);
        return divisionMapper.toDivisionResponse(savedDivision);
    }

    @Override
    public DivisionResponse updateDivision(Long id, DivisionRequest divisionRequest) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Division not found with id: " + id));
        division.setName(divisionRequest.name());
        Division updatedDivision = divisionRepository.save(division);
        return divisionMapper.toDivisionResponse(updatedDivision);
    }

    @Override
    public DivisionResponse getDivisionById(Long id) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Division not found with id: " + id));
        return divisionMapper.toDivisionResponse(division);
    }

    @Override
    public void deleteDivision(Long id) {
        if (!divisionRepository.existsById(id)) {
            throw new RuntimeException("Division not found with id: " + id);
        }
        divisionRepository.deleteById(id);
    }

    @Override
    public List<ServiceResponse> getServicesByDivision(Long divisionId) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found with id: " + divisionId));

        return division.getServices().stream()
                .map(divisionMapper::toServiceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void assignChiefToDivision(Long divisionId, Long employeeId) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found with id: " + divisionId));

        EmployeeDTO employee = employeeClient.getEmployeeById(employeeId);
        if (employee != null) {
            division.setChiefId(employeeId);
            divisionRepository.save(division);
        } else {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }
    }
}