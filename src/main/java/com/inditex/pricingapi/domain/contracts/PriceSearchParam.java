package com.inditex.pricingapi.domain.contracts;

import java.time.LocalDateTime;

public class PriceSearchParam {

    private LocalDateTime applyingDate;
    private Long productID;
    private Long brandID;

    private PriceSearchParam(Builder builder) {
        this.applyingDate = builder.applyingDate;
        this.productID = builder.productID;
        this.brandID = builder.brandID;
    }

    public LocalDateTime getAppliedDate() {
        return applyingDate;
    }

    public Long getProductID() {
        return productID;
    }

    public Long getBrandID() {
        return brandID;
    }

    public static class Builder {

        private LocalDateTime applyingDate;
        private Long productID;
        private Long brandID;

        public Builder applyingDate(LocalDateTime date) {
            this.applyingDate = date;
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
