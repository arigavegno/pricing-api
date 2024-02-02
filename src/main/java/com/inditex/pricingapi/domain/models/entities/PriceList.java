package com.inditex.pricingapi.domain.models.entities;

import java.math.BigDecimal;

public class PriceList {

    private Long id;
    private BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
