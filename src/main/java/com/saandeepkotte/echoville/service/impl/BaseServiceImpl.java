package com.saandeepkotte.echoville.service.impl;

import com.saandeepkotte.echoville.model.BaseEntity;
import com.saandeepkotte.echoville.repository.BaseRepository;
import com.saandeepkotte.echoville.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<T extends BaseEntity, U> implements BaseService<T> {
    @Autowired
    private BaseRepository<T, U> baseRepository;

    @Override
    public List<T> getAll() {
        return baseRepository.findAll();
    }
}
