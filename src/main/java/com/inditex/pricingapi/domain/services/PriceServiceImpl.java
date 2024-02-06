package com.inditex.pricingapi.domain.services;

import com.inditex.pricingapi.domain.exceptions.ApiException;
import com.inditex.pricingapi.domain.exceptions.NotFoundApiException;
import com.inditex.pricingapi.domain.models.entities.Price;
import com.inditex.pricingapi.domain.providers.IPriceRepository;
import com.inditex.pricingapi.domain.contracts.PriceSearchParam;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements IPriceService {

    private IPriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(IPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getByBrandAndProductAndDate(PriceSearchParam searchParam) throws ApiException {
        try {
            Optional<Price> price = priceRepository.findTopByBrandAndProductAndDate(searchParam);

            return price
                    .orElseThrow(() -> new NotFoundApiException("price not found"));
        } catch (Exception e) {
            if (!(e instanceof NotFoundApiException)) {
                throw new ApiException(e.getMessage());
            }
            throw e;
        }
    }
}
