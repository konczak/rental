package pl.konczak.rental.web.rest.item;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.konczak.rental.domain.item.IItemRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
class ItemApi {

    private final IItemRepository itemRepository;

    @Autowired
    ItemApi(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    HttpEntity<List<Item>> list() {
        List<Item> items = itemRepository.findAll().stream()
                .map(Item::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(items);
    }

    @RequestMapping(method = RequestMethod.POST)
    HttpEntity<Item> add(@Valid @RequestBody ItemNew itemNew) {
        if (itemRepository.findByName(itemNew.getName()) != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        pl.konczak.rental.domain.item.Item item = new pl.konczak.rental.domain.item.Item(itemNew.getName(), itemNew.getDescription());

        item = itemRepository.saveAndFlush(item);

        return new ResponseEntity(new Item(item), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{itemId}",
                    method = RequestMethod.DELETE)
    HttpEntity<Void> remove(@PathVariable("itemId") long itemId) {
        itemRepository.delete(itemId);

        return ResponseEntity.noContent()
                .build();
    }
}
