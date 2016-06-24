package pl.konczak.rental.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository
        extends JpaRepository<Person, Long> {

}
