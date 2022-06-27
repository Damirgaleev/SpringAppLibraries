package ru.galeev.spring.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "имя не должно быть пустым")
    @Column(name = "name")
    private String name;
    @Min(value = 1900, message = "год не должен быть меньше 1900")
    @Column(name = "b_year")
    private int b_year;
    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    public Person() {
    }

    public Person(String name, int b_year) {
        this.name = name;
        this.b_year = b_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getB_year() {
        return b_year;
    }

    public void setB_year(int b_year) {
        this.b_year = b_year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
