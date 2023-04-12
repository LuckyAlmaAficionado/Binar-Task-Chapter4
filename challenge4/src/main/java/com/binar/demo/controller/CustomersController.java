package com.binar.demo.controller;

import com.binar.demo.model.Customers;
import com.binar.demo.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    private CustomersService service;

    @PostMapping("/addCustomerAuto")
    public int addCustomerAuto(@RequestBody Customers customers) {
        return service.saveCustomer(customers);
    }

//    update by, create by, update by, last by

    // tambah satu data
    @PostMapping("/addCustomer")
    public Customers addCustomer(@RequestBody Customers customers) {
        return service.addCustomer(customers);
    }

    // tambah beberapa data
    @PostMapping("/addCustomers")
    public List<Customers> addCustomers(@RequestBody List<Customers> customers) {
        return service.addCustomers(customers);
    }

    // mendapatkan data
    @GetMapping("/all")
    public Iterator<Customers> getAll() {
        return service.getAll();
    }

    // mendapatkan data dari nama depan
    @GetMapping("/findByFirstName")
    public List<Customers> findByFirstName(@RequestBody Customers customers) {
        return service.findByFirstName(customers.getFirstName());
    }

    // menghapus data dari ID
    @DeleteMapping("/delete")
    public String deleteById(@RequestBody int id) {
        return service.deleteById(id);
    }

    // memperbarui data
    @PutMapping("/update")
    public String updateCustomer(@RequestBody Customers customers) throws ParseException {
        return service.updateCustomer(customers);
    }
}
