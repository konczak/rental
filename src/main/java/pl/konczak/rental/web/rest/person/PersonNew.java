package pl.konczak.rental.web.rest.person;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class PersonNew {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @NotNull
    @Getter
    @Setter
    private String pesel;

}
