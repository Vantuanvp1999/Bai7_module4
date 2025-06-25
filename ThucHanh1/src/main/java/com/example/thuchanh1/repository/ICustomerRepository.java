package com.example.thuchanh1.repository;

import com.example.thuchanh1.model.Customer;
import com.example.thuchanh1.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAllByProvince(Province province);
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);
}
