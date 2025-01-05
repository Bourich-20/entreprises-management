package com.entreprises_management.project_service.dtos;

public record InvoiceDTO(
        Long id,
        String invoiceNumber,
        Double amount
) {
}
