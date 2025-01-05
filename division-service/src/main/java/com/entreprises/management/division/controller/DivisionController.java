package com.entreprises.management.division.controller;

import com.entreprises.management.division.dtos.DivisionRequest;
import com.entreprises.management.division.dtos.DivisionResponse;
import com.entreprises.management.division.dtos.ServiceRequest;
import com.entreprises.management.division.dtos.ServiceResponse;
import com.entreprises.management.division.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @PostMapping
    public DivisionResponse createDivision(@RequestBody DivisionRequest divisionRequest) {
        return divisionService.createDivision(divisionRequest);
    }

    @PutMapping("/{id}")
    public DivisionResponse updateDivision(@PathVariable Long id, @RequestBody DivisionRequest divisionRequest) {
        return divisionService.updateDivision(id, divisionRequest);
    }

    @GetMapping("/{id}")
    public DivisionResponse getDivisionById(@PathVariable Long id) {
        return divisionService.getDivisionById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
    }

    @GetMapping("/{divisionId}/services")
    public List<ServiceResponse> getServicesByDivision(@PathVariable Long divisionId) {
        return divisionService.getServicesByDivision(divisionId);
    }
    @PutMapping("/{divisionId}/assign-chief/{employeeId}")
    public ResponseEntity<String> assignChiefToDivision(@PathVariable Long divisionId, @PathVariable Long employeeId) {
            divisionService.assignChiefToDivision(divisionId, employeeId);
            return ResponseEntity.ok("Chief assigned successfully to the division.");
    }
    @PostMapping("/{divisionId}/services")
    public ResponseEntity<ServiceResponse> addService(
            @PathVariable Long divisionId,
            @RequestBody ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = divisionService.addServiceToDivision(divisionId, serviceRequest);
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{divisionId}/services/{serviceId}")
    public ResponseEntity<ServiceResponse> updateService(
            @PathVariable Long divisionId,
            @PathVariable Long serviceId,
            @RequestBody ServiceRequest serviceRequest) {
        ServiceResponse serviceResponse = divisionService.updateServiceInDivision(divisionId, serviceId, serviceRequest);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{divisionId}/services/{serviceId}")
    public ResponseEntity<Void> deleteService(
            @PathVariable Long divisionId,
            @PathVariable Long serviceId) {
        divisionService.deleteServiceFromDivision(divisionId, serviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}