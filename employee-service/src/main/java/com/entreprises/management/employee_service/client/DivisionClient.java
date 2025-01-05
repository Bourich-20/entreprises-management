package com.entreprises.management.employee_service.client;

import com.entreprises.management.employee_service.dtos.DivisionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "division-service")
public interface DivisionClient {

    @GetMapping("/divisions/{id}")
    DivisionResponse getDivisionById(@PathVariable("id") Long id);
}
