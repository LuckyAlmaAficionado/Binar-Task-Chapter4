package com.binar.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid")
    int customerId;
    @Column(name = "storeid")
    int storeId;
    @Column(name = "firstname")
    String firstName;
    @Column(name = "lastname")
    String lastName;
    @Column(name = "email")
    String email;
    @Column(name = "addressid")
    int addressId;
    @Column(name = "active")
    int active;
    @Column(name = "createdate")
    Date createDate;
    @Column(name = "lastupdate")
    Date lastUpdate;

}
