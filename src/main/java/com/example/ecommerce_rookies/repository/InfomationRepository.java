package com.example.ecommerce_rookies.repository;

import com.example.ecommerce_rookies.models.Infomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface InfomationRepository extends JpaRepository<Infomation, Long> {

    Boolean existsByEmail(String email);
    @Transactional
    @Modifying
    @Query("delete from Infomation i where i.account.id = ?1")
    void deleteAllByAccount_Id(Long id);

}
