package com.entreprises_management.project_service.dtos;

public record ExpenseDTO(
        Long id,
        String expenseType,
        Double amount,
        String description
) {
}
