package com.eteration.simplebanking.repository;


import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

Optional<Account> findByAccountNumber(String accountNumber);

    
}
