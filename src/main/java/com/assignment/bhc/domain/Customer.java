package com.assignment.bhc.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@Setter
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String  surname;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Account> accounts;
}
