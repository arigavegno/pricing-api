package com.inditex.pricingapi.domain.models.entities.enums;

public enum Currency {
    EUR,
    GBP;

    @Override
    public String toString() {
        return this.name();
    }
}
