package mytheresa.interview.service;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.repository.ProductRepository;
import mytheresa.interview.rest.response.ProductResponse;
import mytheresa.interview.utils.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void apply_30_per_cent_discount_when_category_is_boots() {

        Product product = TestHelper.generateProduct("boots", "000005", 100000);

        ProductResponse expectedResponse = TestHelper.generateExpectedProductResponse(product, 30);

        when(productRepository.findByCategoryAndPrice("boots", 100000)).thenReturn(List.of(product));
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual("boots", 100000);
        assertEquals(List.of(expectedResponse), productList);
    }

    @Test
    void apply_15_per_cent_discount_when_sku_is_000003() {

        Product product = TestHelper.generateProduct("sneakers", "000003", 100000);

        ProductResponse expectedResponse = TestHelper.generateExpectedProductResponse(product, 15);

        when(productRepository.findByCategoryAndPrice("sneakers", 100000)).thenReturn(List.of(product));
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual("sneakers", 100000);
        assertEquals(List.of(expectedResponse), productList);
    }

    @Test
    void apply_0_per_cent_when_no_discount_is_applicable() {

        Product product = TestHelper.generateProduct("sneakers", "000004", 100000);

        ProductResponse expectedResponse = TestHelper.generateExpectedProductResponse(product, 0);

        when(productRepository.findByCategoryAndPrice("sneakers", 100000)).thenReturn(List.of(product));
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual("sneakers", 100000);
        assertEquals(List.of(expectedResponse), productList);
    }

    @Test
    void apply_highest_discount_when_multiple_are_applicable() {
        Product product = TestHelper.generateProduct("boots", "000003", 100000);

        ProductResponse expectedResponse = TestHelper.generateExpectedProductResponse(product, 30);

        when(productRepository.findByCategoryAndPrice("boots", 100000)).thenReturn(List.of(product));
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual("boots", 100000);
        assertEquals(List.of(expectedResponse), productList);
    }

    @Test
    void find_all_products_when_price_filter_is_not_applied() {
        Product product1 = TestHelper.generateProduct("boots", "000003", 100000);
        Product product2 = TestHelper.generateProduct("sneakers", "000003", 120000);

        List<ProductResponse> expectedResponseList = new ArrayList<>();
        expectedResponseList.add(TestHelper.generateExpectedProductResponse(product1, 30));
        expectedResponseList.add(TestHelper.generateExpectedProductResponse(product2, 15));

        when(productRepository.findByCategoryAndPrice("boots", null)).thenReturn(List.of(product1, product2));
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual("boots", null);
        assertEquals(expectedResponseList, productList);
    }

    @Test
    void find_only_products_match_price_filter_when_price_filter_is_applied() {
        Product product1 = TestHelper.generateProduct("boots", "000003", 100000);
        Product product2 = TestHelper.generateProduct("sneakers", "000003", 120000);

        List<ProductResponse> expectedResponseList = new ArrayList<>();
        expectedResponseList.add(TestHelper.generateExpectedProductResponse(product1, 30));

        when(productRepository.findByCategoryAndPrice("boots", 110000)).thenReturn(List.of(product1));
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual("boots", 110000);
        assertEquals(expectedResponseList, productList);
    }
}
