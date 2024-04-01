package ru.neoflex.vacationservice.repository;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Repository
public class VacationPaymentRepository {

    public Set<LocalDate> getHolidays() {
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/holidays.csv"))) {
            return lines.map(LocalDate::parse).collect(toSet());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось открыть файл holidays.csv или получить данные о праздниках из него", e);
        }
    }
}
