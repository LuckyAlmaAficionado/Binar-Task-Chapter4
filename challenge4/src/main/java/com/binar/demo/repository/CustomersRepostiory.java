package com.binar.demo.repository;

import com.binar.demo.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomersRepostiory extends JpaRepository<Customers, Integer> {

    List<Customers> findByFirstName(String name);

}
