package com.example.thuchanh1.service.impl;

import com.example.thuchanh1.model.Province;
import com.example.thuchanh1.repository.IProvinceRepository;
import com.example.thuchanh1.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        provinceRepository.deleteById(id);
    }
}
