package com.entreprises.management.employee_service.service;




import com.entreprises.management.employee_service.dtos.*;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);
    EmployeeResponse getEmployeeById(Long id);
    List<EmployeeResponse> getAllEmployees();
    void deleteEmployee(Long id);
    List<EmployeeResponse> getEmployeesByDivision(Long divisionId);
    List<EmployeeResponse> getEmployeesByProject(Long projectId);
    List<CreditResponse> getEmployeeCredits(Long employeeId);
    CreditResponse addCredit(Long employeeId, CreditRequest creditRequest);
    BonusResponse addBonus(Long employeeId, BonusRequest bonusRequest);
    SalaryAdvanceResponse requestSalaryAdvance(Long employeeId, SalaryAdvanceRequest salaryAdvanceRequest);
    CnssPaymentResponse processCnssPayment(Long employeeId, CnssPaymentRequest cnssPaymentRequest);
    List<CnssPaymentHistoryResponse> getCnssPaymentHistory(Long employeeId);
    List<PaySlipResponse> getPaySlipsByEmployee(Long employeeId);
    PaySlipResponse createPaySlip(Long employeeId, PaySlipRequest paySlipRequest);
}