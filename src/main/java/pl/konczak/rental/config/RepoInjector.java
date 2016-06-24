package pl.konczak.rental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import pl.konczak.rental.domain.item.IItemRepository;
import pl.konczak.rental.domain.person.IPersonRepository;
import pl.konczak.rental.domain.person.Person;

import javax.annotation.PostConstruct;

@Configuration
public class RepoInjector {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IPersonRepository personRepository;

    @PostConstruct
    public void init() {
        Person.itemRepository = itemRepository;
        Person.personRepository = personRepository;
    }
}
