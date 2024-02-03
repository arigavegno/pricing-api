package com.inditex.pricingapi.domain.models.entities;

import com.inditex.pricingapi.domain.models.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {

    private Long brandID;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Long priceListID;
    private Long productID;
    private Integer priority;
    private BigDecimal price;
    private Currency currency;

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandID) {
        this.brandID = brandID;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Long getPriceListID() {
        return priceListID;
    }

    public void setPriceListID(Long priceListID) {
        this.priceListID = priceListID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
