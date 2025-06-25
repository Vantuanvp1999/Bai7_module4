package com.example.thuchanh1.service;

import com.example.thuchanh1.model.Customer;
import com.example.thuchanh1.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGenerateService<Customer> {
    Iterable<Customer> findAllByProvice(Province province);
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);
}
