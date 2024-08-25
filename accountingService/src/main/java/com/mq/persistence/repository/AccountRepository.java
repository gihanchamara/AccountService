package com.mq.persistence.repository;

import com.mq.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO, Long> {
    List<AccountDTO> getAccountsByUserId(Long userId);
    AccountDTO findByAccountId(Long accountId);
}
