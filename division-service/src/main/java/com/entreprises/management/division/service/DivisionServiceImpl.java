package com.entreprises.management.division.service;
import com.entreprises.management.division.client.EmployeeClient;
import com.entreprises.management.division.dtos.*;
import com.entreprises.management.division.entitie.Division;
import com.entreprises.management.division.entitie.Service;
import com.entreprises.management.division.mapper.DivisionMapper;


import com.entreprises.management.division.repository.DivisionRepository;
import com.entreprises.management.division.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private DivisionMapper divisionMapper;
    @Autowired
    private ServiceRepository serviceRepository;

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
    @Override
    public ServiceResponse addServiceToDivision(Long divisionId, ServiceRequest serviceRequest) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found"));

        // Validate that all employee IDs exist
        validateEmployeeIds(serviceRequest.employeeIds());

        Service service = new Service();
        service.setName(serviceRequest.name());
        service.setDivision(division);
        service.setChiefId(serviceRequest.chiefId());
        service.setEmployeeIds(serviceRequest.employeeIds());

        serviceRepository.save(service);

        return new ServiceResponse(service.getId(), service.getName(), divisionId, service.getChiefId(), service.getEmployeeIds());
    }

    @Override
    public ServiceResponse updateServiceInDivision(Long divisionId, Long serviceId, ServiceRequest serviceRequest) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found"));

        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        validateEmployeeIds(serviceRequest.employeeIds());

        service.setName(serviceRequest.name());
        service.setChiefId(serviceRequest.chiefId());
        service.setEmployeeIds(serviceRequest.employeeIds());

        serviceRepository.save(service);

        return new ServiceResponse(service.getId(), service.getName(), divisionId, service.getChiefId(), service.getEmployeeIds());
    }

    @Override
    public void deleteServiceFromDivision(Long divisionId, Long serviceId) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found"));

        Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        serviceRepository.delete(service);
    }

    private void validateEmployeeIds(List<Long> employeeIds) {
        for (Long employeeId : employeeIds) {
            if (employeeClient.getEmployeeById(employeeId) == null) {
                throw new RuntimeException("Employee with ID " + employeeId + " does not exist");
            }
        }
    }
}