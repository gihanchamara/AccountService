package com.mq.persistance.repository;

import com.mq.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDTO, Long> {
    Page<TransactionDTO> findBySrcAccountIdOrDestAccountId(Long srcAccountId, Long destAccountId, Pageable pageable);
}
