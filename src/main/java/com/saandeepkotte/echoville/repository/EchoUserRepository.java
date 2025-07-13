package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.EchoUser;
import org.springframework.stereotype.Repository;

@Repository
public interface EchoUserRepository extends BaseRepository<EchoUser, Long> {
}
