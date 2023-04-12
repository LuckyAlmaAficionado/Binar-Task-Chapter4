package com.binar.demo.service;

import com.binar.demo.model.Customers;
import com.binar.demo.repository.CustomersRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepostiory repostiory;

    EntityManager entityManager;


    public CustomersService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public int saveCustomer(Customers customers) {
        entityManager.persist(customers);
        return customers.getCustomerId();
    }

    public List<Customers> getCustomersByActiveDesc() {
        return repostiory.findByCustomerActiveDesc();
    }

    public Iterator<Customers> getAll() {
        Iterator<Customers> check = repostiory.findAll().iterator();
        if (check.hasNext()) return check;
        else throw new RuntimeException("data tidak tersedia");
    }

    public List<Customers> findByFirstName(String name) {
        if (repostiory.findByFirstName(name).isEmpty()) throw new RuntimeException("nama tidak ditemukan...!");
        else return repostiory.findByFirstName(name);
    }

    public String deleteById(int id) {
        repostiory.deleteById(id);
        return "Berhasil di hapus..!";
    }

    LocalDateTime localDateTime = LocalDateTime.now();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String updateCustomer(Customers customers) throws ParseException {
        if (isExist(customers)) {
            Customers update = repostiory.findById(customers.getCustomerId()).orElse(null);
            update.setStoreId(customers.getStoreId());
            update.setFirstName(customers.getFirstName());
            update.setLastName(customers.getLastName());
            update.setEmail(customers.getEmail());
            update.setAddressId(customers.getAddressId());
            update.setActive(customers.getActive());
            update.setCreateDate(customers.getCreateDate());
            update.setLastUpdate(sdf.parse(String.valueOf(localDateTime)));
            repostiory.save(update);
            return "data berhasil di perbarui..!";
        } else {
            throw new RuntimeException("data dengan id " + customers.getCustomerId() + " tidak tersedia...!");
        }
    }


    public boolean isExist(Customers customers) {
        Optional<Customers> check = repostiory.findById(customers.getCustomerId());
        return check.isPresent();
    }

    public Customers addCustomer(Customers customers) {
        if (isExist(customers)) throw new RuntimeException("data sudah tersedia...!");
        else {
            return repostiory.save(customers);
        }
    }

    public List<Customers> addCustomers(List<Customers> customers) {
        Iterator<Customers> check = customers.iterator();
        while (check.hasNext()) {
            Customers temp = check.next();
            if (isExist(temp)) throw new RuntimeException("data dengan id " + temp.getCustomerId() + " sudah tersedia...!");
        }
        return repostiory.saveAll(customers);
    }
}
