package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class RoomDaoImpl implements RoomDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public RoomEntity findById(Long id) {
        if (em == null) {
            throw new IllegalStateException("EntityManager is not injected. Check your configuration.");
        }

        String jpql = "SELECT r FROM RoomEntity r WHERE r.id = :id";
        return em.createQuery(jpql, RoomEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
