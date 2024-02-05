package com.inditex.pricingapi.domain.contracts;

import java.time.LocalDateTime;

public class PriceSearchParam {

    private LocalDateTime applyAt;
    private Long productID;
    private Long brandID;

    private PriceSearchParam(Builder builder) {
        this.applyAt = builder.applyAt;
        this.productID = builder.productID;
        this.brandID = builder.brandID;
    }

    public LocalDateTime getApplyAt() {
        return applyAt;
    }

    public void setApplyAt(LocalDateTime applyAt) {
        this.applyAt = applyAt;
    }

    public Long getProductID() {
        return productID;
    }

    public Long getBrandID() {
        return brandID;
    }

    public static Builder Builder() {
        return new Builder();
    }

    public static class Builder {

        private LocalDateTime applyAt;
        private Long productID;
        private Long brandID;

        public Builder applyAt(LocalDateTime date) {
            this.applyAt = date;
            return this;
        }

        public Builder productID(Long productID) {
            this.productID = productID;
            return this;
        }

        public Builder brandID(Long brandID) {
            this.brandID = brandID;
            return this;
        }

        public PriceSearchParam build() {
            return new PriceSearchParam(this);
        }
    }
}
