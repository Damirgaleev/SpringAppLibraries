package ru.galeev.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.galeev.spring.models.Book;
import ru.galeev.spring.models.Person;
import ru.galeev.spring.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sort) {
        if (sort)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findPag(Integer page, Integer perPage, boolean sort) {
        if (sort)
            return booksRepository.findAll(PageRequest.of(page, perPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, perPage)).getContent();

    }

    public Book findById(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        Book toBeUpdateBook = booksRepository.findById(id).get();
        updateBook.setOwner(toBeUpdateBook.getOwner());
        updateBook.setId(id);
        booksRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakeAt(null);
                });
    }

    @Transactional
    public void assign(int id, Person person) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(person);
            book.setTakeAt(new Date());
        });
    }

    public List<Book> searchByName(String startName) {
        return booksRepository.findByNameStartingWith(startName);
    }


}
