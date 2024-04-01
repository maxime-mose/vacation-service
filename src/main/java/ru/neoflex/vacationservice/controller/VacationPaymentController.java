package ru.neoflex.vacationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.vacationservice.model.VacationPayment;
import ru.neoflex.vacationservice.service.VacationPaymentService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class VacationPaymentController {

    private final VacationPaymentService vacationPaymentService;

    @GetMapping("calculate")
    public VacationPayment calculate(@RequestParam long averageSalary,
                                     @RequestParam long numberOffDays,
                                     @RequestParam(required = false) LocalDate startDate) {
        return vacationPaymentService.calculate(averageSalary, numberOffDays, startDate);
    }
}
