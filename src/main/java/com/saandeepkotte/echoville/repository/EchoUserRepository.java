package com.saandeepkotte.echoville.repository;

import com.saandeepkotte.echoville.model.EchoUser;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchoUserRepository extends BaseRepository<EchoUser, Long> {
    @NativeQuery("select count(*) from echo_user eu where eu.email = :email and eu.role = 0 and eu.company_id = :companyId")
    Integer getAdminCount(@Param("email") String email, @Param("companyId") String companyId);

    @NativeQuery("select count(*) from echo_user eu where eu.email = :email and eu.role = 1 and eu.community_id = :communityId")
    Integer getAdminCount(@Param("email") String email, @Param("communityId") Long communityId);

    List<EchoUser> findByEmailAndCommunityIdAndCompanyId(String email, Long communityId, String companyId);
}
