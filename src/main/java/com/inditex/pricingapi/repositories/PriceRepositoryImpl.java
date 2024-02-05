package com.inditex.pricingapi.repositories;

import com.inditex.pricingapi.domain.contracts.PriceSearchParam;
import com.inditex.pricingapi.domain.models.entities.Price;
import com.inditex.pricingapi.domain.providers.IPriceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryImpl implements IPriceRepository {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Price> findTopByBrandAndProductAndDate(PriceSearchParam searchParam) {
        return entityManager
                .createQuery("FROM Price p " +
                        "WHERE p.brand.id = :brandId AND p.product.id = :productId AND :applyAt BETWEEN p.startDate AND p.endDate " +
                        "ORDER BY p.priority DESC LIMIT 1")
                .setParameter("brandId", searchParam.getBrandID())
                .setParameter("productId", searchParam.getProductID())
                .setParameter("applyAt", searchParam.getApplyAt())
                .getResultStream().findFirst();
    }
}
