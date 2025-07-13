package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, U> extends JpaRepository<T, U> {

}
