package com.example.ecommerce_rookies.Repository;

import com.example.ecommerce_rookies.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
