package pl.konczak.rental.web.rest.person;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

public class Hire {

    @Getter
    private final long hireId;

    @Getter
    private final LocalDateTime hireAt;

    @Getter
    private final LocalDate deadlineAt;

    @Getter
    private final long itemId;

    @Getter
    private final String name;

    public Hire(pl.konczak.rental.domain.hire.Hire hire) {
        this.hireId = hire.getId();
        this.hireAt = hire.getHireAt();
        this.deadlineAt = hire.getDeadlineAt();
        this.itemId = hire.getItem().getId();
        this.name = hire.getItem().getName();
    }

}
