package com.inditex.pricingapi.domain.providers;

import com.inditex.pricingapi.domain.contracts.PriceSearchParam;
import com.inditex.pricingapi.domain.models.entities.Price;

import java.util.Optional;

public interface IPriceRepository {

    Optional<Price> findByBrandAndProduct(PriceSearchParam searchParam);
}
