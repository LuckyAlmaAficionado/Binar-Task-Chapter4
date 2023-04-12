package com.binar.demo.repository;

import com.binar.demo.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomersRepostiory extends JpaRepository<Customers, Integer> {

    @Query(value = "SELECT * FROM public.customers WHERE active = 1 ORDER BY createdate DESC LIMIT 10", nativeQuery = true)
    List<Customers> findByCustomerActiveDesc();


    List<Customers> findByFirstName(String name);

}
