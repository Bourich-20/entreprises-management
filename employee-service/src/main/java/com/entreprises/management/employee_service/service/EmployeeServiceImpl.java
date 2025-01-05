package com.entreprises.management.employee_service.service;


import com.entreprises.management.employee_service.client.DivisionClient;
import com.entreprises.management.employee_service.dtos.*;
import com.entreprises.management.employee_service.entities.*;
import com.entreprises.management.employee_service.mapper.EmployeeMapper;
import com.entreprises.management.employee_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private SalaryAdvanceRepository salaryAdvanceRepository;

    @Autowired
    private CnssPaymentRepository cnssPaymentRepository;

    @Autowired
    private PaySlipRepository paySlipRepository;
    @Autowired
    private DivisionClient divisionClient;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEntity(employeeRequest);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeMapper.updateEntityFromRequest(employeeRequest, employee);
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(updatedEmployee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return employeeMapper.toResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
    public List<EmployeeResponse> getEmployeesByDivision(Long divisionId) {
        DivisionResponse division = divisionClient.getDivisionById(divisionId);

        if (division == null) {
            throw new RuntimeException("Division not found");
        }

        return employeeRepository.findByDivisionId(divisionId)
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponse> getEmployeesByProject(Long projectId) {
        return employeeRepository.findByProjectId(projectId)
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }
    @Override
    public List<CreditResponse> getEmployeeCredits(Long employeeId) {
        List<Credit> credits = creditRepository.findByEmployeeId(employeeId);

        return credits.stream().map(credit -> new CreditResponse(
                credit.getId(),
                credit.getAmount(),
                credit.getDescription(),
                credit.getDate()
        )).collect(Collectors.toList());
    }

    @Override
    public CreditResponse addCredit(Long employeeId, CreditRequest creditRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Credit credit = new Credit();
        credit.setEmployee(employee);
        credit.setAmount(creditRequest.amount());
        credit.setDescription(creditRequest.description());

        employee.getCredits().add(credit);

        creditRepository.save(credit);

        return new CreditResponse(credit.getId(), credit.getAmount(), credit.getDescription(), credit.getDate());
    }

    @Override
    public BonusResponse addBonus(Long employeeId, BonusRequest bonusRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Bonus bonus = new Bonus();
        bonus.setEmployee(employee);
        bonus.setBonusAmount(bonusRequest.bonusAmount());
        bonus.setReason(bonusRequest.reason());

        employee.getBonuses().add(bonus);

        bonusRepository.save(bonus);

        return new BonusResponse(bonus.getId(), bonus.getBonusAmount(), bonus.getReason(), bonus.getDate());
    }

    @Override
    public SalaryAdvanceResponse requestSalaryAdvance(Long employeeId, SalaryAdvanceRequest salaryAdvanceRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        SalaryAdvance salaryAdvance = new SalaryAdvance();
        salaryAdvance.setEmployee(employee);
        salaryAdvance.setAmount(salaryAdvanceRequest.amount());
        salaryAdvance.setReason(salaryAdvanceRequest.reason());

        employee.getSalaryAdvances().add(salaryAdvance);

        salaryAdvanceRepository.save(salaryAdvance);

        return new SalaryAdvanceResponse(salaryAdvance.getId(), salaryAdvance.getAmount(), salaryAdvance.getReason(), salaryAdvance.getDate());
    }
    @Override
    public CnssPaymentResponse processCnssPayment(Long employeeId, CnssPaymentRequest cnssPaymentRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        CnssPayment cnssPayment = new CnssPayment();
        cnssPayment.setEmployee(employee);
        cnssPayment.setAmount(cnssPaymentRequest.amount());
        cnssPayment.setPaymentMethod(cnssPaymentRequest.paymentMethod());

        employee.getCnssPayments().add(cnssPayment);

        cnssPaymentRepository.save(cnssPayment);

        return new CnssPaymentResponse(cnssPayment.getId(), cnssPayment.getAmount(), cnssPayment.getPaymentMethod(), cnssPayment.getPaymentDate());
    }


    @Override
    public List<CnssPaymentHistoryResponse> getCnssPaymentHistory(Long employeeId) {
        List<CnssPayment> history = cnssPaymentRepository.findHistoryByEmployeeId(employeeId);

        return history.stream()
                .map(h -> new CnssPaymentHistoryResponse(h.getId(), h.getAmount(), h.getPaymentMethod(), h.getPaymentDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaySlipResponse> getPaySlipsByEmployee(Long employeeId) {
        List<PaySlip> paySlips = paySlipRepository.findByEmployeeId(employeeId);

        return paySlips.stream()
                .map(p -> new PaySlipResponse(p.getId(), p.getBasicSalary(), p.getBonuses(), p.getDeductions(), p.getNetSalary(), p.getPayDate()))
                .collect(Collectors.toList());
    }

    @Override
    public PaySlipResponse createPaySlip(Long employeeId, PaySlipRequest paySlipRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        PaySlip paySlip = new PaySlip();
        paySlip.setEmployee(employee);
        paySlip.setBasicSalary(paySlipRequest.basicSalary());
        paySlip.setBonuses(paySlipRequest.bonuses());
        paySlip.setDeductions(paySlipRequest.deductions());

        double netSalary = paySlipRequest.basicSalary() + paySlipRequest.bonuses() - paySlipRequest.deductions();
        paySlip.setNetSalary(netSalary);

        paySlip.setPayDate(new java.util.Date());

        employee.getPaySlips().add(paySlip);

        paySlipRepository.save(paySlip);

        return new PaySlipResponse(paySlip.getId(), paySlip.getBasicSalary(), paySlip.getBonuses(), paySlip.getDeductions(), paySlip.getNetSalary(), paySlip.getPayDate());
    }

}
