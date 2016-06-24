package pl.konczak.rental.web.rest.hire;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class HireRequest {

    @Getter
    @Setter
    private long personId;

    @Getter
    @Setter
    private long itemId;

    @Getter
    @Setter
    private LocalDate deadlineAt;

}
