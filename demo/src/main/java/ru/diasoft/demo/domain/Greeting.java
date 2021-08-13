package ru.diasoft.demo.domain;

import javax.persistence.*;
import lombok.*;
import java.io.Serializable;


@Entity
@Table(name = "greeting")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Greeting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
}
