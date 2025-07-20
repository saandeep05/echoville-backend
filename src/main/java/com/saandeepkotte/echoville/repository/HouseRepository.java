package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.House;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends BaseRepository<House, Long> {
    List<House> findByNumber(String number);
}
