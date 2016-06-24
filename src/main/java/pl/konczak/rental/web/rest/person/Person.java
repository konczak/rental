package pl.konczak.rental.web.rest.person;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class Person {

    @Getter
    private final long personId;

    @Getter
    private final String firstname;

    @Getter
    private final String lastname;

    @Getter
    private final String pesel;

    @Getter
    private final List<Hire> hires;

    Person(pl.konczak.rental.domain.person.Person person) {
        this.personId = person.getId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.pesel = person.getPesel();
        this.hires = person.getHires().stream()
                .map(Hire::new)
                .collect(Collectors.toList());
    }

}
