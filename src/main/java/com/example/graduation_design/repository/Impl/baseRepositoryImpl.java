package com.example.graduation_design.repository.Impl;

import com.example.graduation_design.repository.baseRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class baseRepositoryImpl<T,ID> extends SimpleJpaRepository<T, ID> implements baseRepository<T,ID> {
   private EntityManager manager;

    public baseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager=entityManager;
    }

    @Override
    public void refresh(T t) {
        manager.refresh(t);
    }
}