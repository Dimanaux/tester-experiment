package com.example.app.tester.db.repositories;

import com.example.app.tester.db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
