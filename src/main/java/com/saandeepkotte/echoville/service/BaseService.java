package com.saandeepkotte.echoville.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();
}
