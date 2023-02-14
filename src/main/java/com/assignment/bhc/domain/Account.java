package com.assignment.bhc.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@Setter
@Getter
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private double balance;

    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "account" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transaction> transaction;


}
