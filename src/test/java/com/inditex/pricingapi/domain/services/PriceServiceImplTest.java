package com.inditex.pricingapi.domain.services;

import com.inditex.pricingapi.domain.contracts.PriceSearchParam;
import com.inditex.pricingapi.domain.exceptions.ApiException;
import com.inditex.pricingapi.domain.exceptions.NotFoundApiException;
import com.inditex.pricingapi.domain.models.entities.Price;
import com.inditex.pricingapi.domain.providers.IPriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.doThrow;

class PriceServiceImplTest {

    private static IPriceRepository mockedPriceRepository;
    private static PriceServiceImpl service;
    private static Price priceItem1;
    private static Price priceItem2;

    @BeforeAll
    static void setup() {
        mockedPriceRepository = mock(IPriceRepository.class);

        priceItem1 = new Price();
        priceItem1.setBrandID(1L);
        priceItem1.setProductID(35455L);
        priceItem1.setStart_date(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        priceItem1.setEnd_date(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceItem1.setPriceListID(1L);
        priceItem1.setPrice(BigDecimal.valueOf(35.5));
        priceItem1.setPriority(0);

        priceItem2 = new Price();
        priceItem2.setBrandID(1L);
        priceItem2.setProductID(35455L);
        priceItem2.setStart_date(LocalDateTime.of(2020, 6, 14, 15, 0, 0));
        priceItem2.setEnd_date(LocalDateTime.of(2020, 6, 14, 18, 30, 0));
        priceItem2.setPriceListID(2L);
        priceItem2.setPrice(BigDecimal.valueOf(25.45));
        priceItem2.setPriority(1);

        service = new PriceServiceImpl(mockedPriceRepository);
    }

    @AfterEach
    void clean() {
        reset(mockedPriceRepository);
    }

    @ParameterizedTest(name = "applyingDate: {0} - expectedPrice: {1}")
    @MethodSource("casesOfSuccessByAppliedDate")
    void testGetByProductAndBrand_whenIsSuccess_shouldReturnPriceItem(LocalDateTime applyingDate, Price expectedPrice) throws ApiException {
        PriceSearchParam searchParam = new PriceSearchParam.Builder()
                .applyingDate(applyingDate)
                .productID(1L)
                .brandID(1L).build();

        when(mockedPriceRepository.findByBrandAndProduct(searchParam)).thenReturn(Optional.of(expectedPrice));

        Price result = service.getByBrandAndProduct(searchParam);

        assertEquals(expectedPrice.getBrandID(), result.getBrandID());
        assertEquals(expectedPrice.getProductID(), result.getProductID());
        assertEquals(expectedPrice.getStart_date(), result.getStart_date());
        assertEquals(expectedPrice.getEnd_date(), result.getEnd_date());
        assertEquals(expectedPrice.getCurrency(), result.getCurrency());
        assertEquals(expectedPrice.getPriority(), result.getPriority());
        assertEquals(expectedPrice.getPrice(), result.getPrice());
        assertEquals(expectedPrice.getPriceListID(), result.getPriceListID());
    }

    private static Collection casesOfSuccessByAppliedDate() {
        return Arrays.asList(new Object[][]{
                {LocalDateTime.of(2020, 6, 14, 15, 0, 0), priceItem2},
                {LocalDateTime.of(2020, 6, 14, 16, 30, 0), priceItem2},
                {LocalDateTime.of(2020, 6, 14, 18, 30, 0), priceItem2},
                {LocalDateTime.of(2020, 9, 5, 10, 30, 0), priceItem1},
        });
    }

    @Test
    void testGetByProductAndBrand_whenPriceNotExists_shouldReturnNotFoundApiException() {
        String errorMessage = "price not found";
        PriceSearchParam searchParam = new PriceSearchParam.Builder()
                .applyingDate(LocalDateTime.now())
                .productID(43555L)
                .brandID(1L).build();

        when(mockedPriceRepository.findByBrandAndProduct(searchParam)).thenReturn(Optional.ofNullable(null));

        ApiException exception = assertThrows(NotFoundApiException.class, () -> service.getByBrandAndProduct(searchParam));

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testGetByProductAndBrand_whenProviderFails_shouldReturnApiException() {
        String errorMessage = "any database error";

        PriceSearchParam searchParam = new PriceSearchParam.Builder()
                .applyingDate(LocalDateTime.now())
                .productID(2L)
                .brandID(1L).build();

        Exception mockedException = mock(RuntimeException.class);

        when(mockedException.getMessage()).thenReturn(errorMessage);
        doThrow(mockedException).when(mockedPriceRepository).findByBrandAndProduct(searchParam);

        ApiException exception = assertThrows(ApiException.class, () -> service.getByBrandAndProduct(searchParam));

        assertEquals(errorMessage, exception.getMessage());
    }
}
