package pl.konczak.rental.web.rest.hire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.konczak.rental.domain.item.IItemRepository;
import pl.konczak.rental.domain.item.Item;
import pl.konczak.rental.domain.person.IPersonRepository;
import pl.konczak.rental.domain.person.Person;

import javax.validation.Valid;

@RestController
@RequestMapping("/hire")
class HireApi {

    private final IPersonRepository personRepository;

    private final IItemRepository itemRepository;

    @Autowired
    HireApi(IPersonRepository personRepository, IItemRepository itemRepository) {
        this.personRepository = personRepository;
        this.itemRepository = itemRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    HttpEntity hire(@Valid @RequestBody HireRequest hireRequest) {
        Item item = itemRepository.findOne(hireRequest.getItemId());
        if (item == null) {
            return ResponseEntity.badRequest().build();
        }

        Person person = personRepository.findOne(hireRequest.getPersonId());
        if (person == null) {
            return ResponseEntity.badRequest().build();
        }

        if (hireRequest.getDeadlineAt() == null) {
            person.hire(hireRequest.getItemId());
        } else {
            person.hire(hireRequest.getItemId(), hireRequest.getDeadlineAt());
        }

        return ResponseEntity.ok().build();
    }
}
