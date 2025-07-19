package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, U> extends JpaRepository<T, U> {
    @Override
    @Query("select t from #{#entityName} t where t.deletedAt is null")
    List<T> findAll();

    @Override
    @Query("select t from #{#entityName} t where t.deletedAt is null and t.id = :id")
    Optional<T> findById(@Param("id") U id);
}
