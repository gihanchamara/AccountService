package com.mq.persistance.repository;

import com.mq.dto.AccountDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<AccountDTO> getAccountsByUserIdManual(Long userId) {
        return entityManager.createQuery("SELECT a FROM AccountDTO a WHERE a.userId = :userId", AccountDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
