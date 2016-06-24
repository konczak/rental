package pl.konczak.rental.web.rest.person;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.konczak.rental.domain.person.IPersonRepository;
import pl.konczak.rental.web.rest.item.Item;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
class PersonApi {

    private final IPersonRepository personRepository;

    @Autowired
    PersonApi(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    HttpEntity<List<Person>> list() {
        List<Person> persons = personRepository.findAll().stream()
                .map(Person::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(persons);
    }

    @RequestMapping(method = RequestMethod.POST)
    HttpEntity<Item> add(@Valid @RequestBody PersonNew personNew) {
        pl.konczak.rental.domain.person.Person person = new pl.konczak.rental.domain.person.Person(personNew.getFirstname(), personNew.getLastname(), personNew.getPesel());

        person = personRepository.saveAndFlush(person);

        return new ResponseEntity(new Person(person), HttpStatus.CREATED);
    }
}
