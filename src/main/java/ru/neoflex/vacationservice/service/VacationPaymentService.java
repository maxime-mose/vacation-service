package ru.neoflex.vacationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.vacationservice.model.VacationPayment;
import ru.neoflex.vacationservice.repository.VacationPaymentRepository;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class VacationPaymentService {

    private static final double DAYS_IN_MONTH = 29.3;

    private final VacationPaymentRepository vacationPaymentRepository;

    public VacationPayment calculate(long averageSalary, long numberOffDays, LocalDate startDate) {
        long count = holidaysCount(numberOffDays, startDate);
        long payment = (long) Math.floor(averageSalary * (numberOffDays - count) / DAYS_IN_MONTH);
        return new VacationPayment(payment);
    }

    private long holidaysCount(long numberOffDays, LocalDate startDate) {
        if (startDate == null) {
            return 0;
        }

        Set<LocalDate> holidays = vacationPaymentRepository.getHolidays();
        return LongStream.range(0, numberOffDays)
                .mapToObj(startDate::plusDays)
                .filter(holidays::contains)
                .count();
    }
}
