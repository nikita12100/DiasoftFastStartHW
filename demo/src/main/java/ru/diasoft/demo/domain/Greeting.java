package ru.diasoft.demo.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "greeting")
public class Greeting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public Greeting(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
