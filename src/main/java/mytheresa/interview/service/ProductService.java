package mytheresa.interview.service;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.valueobject.Price;
import mytheresa.interview.repository.ProductRepository;
import mytheresa.interview.rest.response.ProductResponse;
import mytheresa.interview.rules.BootsDiscountRule;
import mytheresa.interview.rules.DiscountRulesEngine;
import mytheresa.interview.rules.Sku000003DiscountRule;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> findByCategoryAndPriceLessThanEqual(String category, Integer priceFilter) {
        List<Product> productList = productRepository.findByCategoryAndPrice(category, priceFilter);
        return mapProductsToResponse(productList);
    }

    private List<ProductResponse> mapProductsToResponse(List<Product> productList) {
        return productList.stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setSku(product.getSku());
            productResponse.setName(product.getName());
            productResponse.setCategory(product.getCategory());
            productResponse.setPrice(applyDiscounts(product));
            return productResponse;
        }).collect(Collectors.toList());
    }

    private Price applyDiscounts(Product product) {
        Price priceWithDiscount = null;
        try {
            var discounts = new DiscountRulesEngine()
                    .registerNewRule(new BootsDiscountRule())
                    .registerNewRule(new Sku000003DiscountRule())
                    .processRules(product);

            var originalPrice = product.getPrice();
            var discountToApply = discounts.size() > 0 ? Collections.max(discounts) : 0;

            priceWithDiscount = new Price();
            priceWithDiscount.setOriginalPrice(originalPrice);
            priceWithDiscount.setFinalPrice(computeFinalPrice(originalPrice, discountToApply));
            priceWithDiscount.setDiscountPercentage(discountToApply > 0 ? discountToApply + "%" : null);

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        return priceWithDiscount;
    }

    private Integer computeFinalPrice(Integer originalPrice, Integer discountToApply) {
        return originalPrice - (originalPrice * discountToApply / 100);
    }

}
