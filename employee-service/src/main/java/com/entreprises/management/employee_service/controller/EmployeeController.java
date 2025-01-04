package com.entreprises.management.employee_service.controller;

import com.entreprises.management.employee_service.dtos.*;
import com.entreprises.management.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok(employeeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<EmployeeResponse> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-division/{divisionId}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDivision(@PathVariable Long divisionId) {
        List<EmployeeResponse> employees = employeeService.getEmployeesByDivision(divisionId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByProject(@PathVariable Long projectId) {
        List<EmployeeResponse> employees = employeeService.getEmployeesByProject(projectId);
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/{id}/credits")
    public ResponseEntity<List<CreditResponse>> getEmployeeCredits(@PathVariable Long id) {
        List<CreditResponse> credits = employeeService.getEmployeeCredits(id);
        return ResponseEntity.ok(credits);
    }

    @PostMapping("/{id}/credit")
    public ResponseEntity<CreditResponse> addCredit(@PathVariable Long id, @RequestBody CreditRequest creditRequest) {
        CreditResponse creditResponse = employeeService.addCredit(id, creditRequest);
        return ResponseEntity.ok(creditResponse);
    }

    @PostMapping("/{id}/bonus")
    public ResponseEntity<BonusResponse> addBonus(@PathVariable Long id, @RequestBody BonusRequest bonusRequest) {
        BonusResponse bonusResponse = employeeService.addBonus(id, bonusRequest);
        return ResponseEntity.ok(bonusResponse);
    }

    @PostMapping("/{id}/salary-advance")
    public ResponseEntity<SalaryAdvanceResponse> requestSalaryAdvance(@PathVariable Long id, @RequestBody SalaryAdvanceRequest salaryAdvanceRequest) {
        SalaryAdvanceResponse salaryAdvanceResponse = employeeService.requestSalaryAdvance(id, salaryAdvanceRequest);
        return ResponseEntity.ok(salaryAdvanceResponse);
    }

    @PostMapping("/{id}/cnss-payment")
    public ResponseEntity<CnssPaymentResponse> processCnssPayment(@PathVariable Long id, @RequestBody CnssPaymentRequest cnssPaymentRequest) {
        CnssPaymentResponse cnssPaymentResponse = employeeService.processCnssPayment(id, cnssPaymentRequest);
        return ResponseEntity.ok(cnssPaymentResponse);
    }

    @GetMapping("/{id}/cnss-payment-history")
    public ResponseEntity<List<CnssPaymentHistoryResponse>> getCnssPaymentHistory(@PathVariable Long id) {
        List<CnssPaymentHistoryResponse> history = employeeService.getCnssPaymentHistory(id);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/{id}/pay-slips")
    public ResponseEntity<List<PaySlipResponse>> getPaySlipsByEmployee(@PathVariable Long id) {
        List<PaySlipResponse> paySlips = employeeService.getPaySlipsByEmployee(id);
        return ResponseEntity.ok(paySlips);
    }

    @PostMapping("/{id}/pay-slip")
    public ResponseEntity<PaySlipResponse> createPaySlip(@PathVariable Long id, @RequestBody PaySlipRequest paySlipRequest) {
        PaySlipResponse paySlipResponse = employeeService.createPaySlip(id, paySlipRequest);
        return ResponseEntity.ok(paySlipResponse);
    }

}