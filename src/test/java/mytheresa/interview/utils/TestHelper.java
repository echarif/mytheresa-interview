package mytheresa.interview.utils;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.valueobject.Price;
import mytheresa.interview.rest.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    public static Product generateProduct(String category, String sku, Integer price) {

        return new Product(1L, sku, "Test product", category, price);
    }

    public static ProductResponse generateExpectedProductResponse(Product product, Integer discount) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setCategory(product.getCategory());
        productResponse.setName(product.getName());
        productResponse.setSku(product.getSku());

        Price price = new Price();

        price.setOriginalPrice(product.getPrice());
        price.setFinalPrice(product.getPrice() - (product.getPrice() * discount / 100));
        price.setDiscountPercentage(discount > 0 ? discount + "%" : null);
        price.setCurrency("EUR");

        productResponse.setPrice(price);

        return productResponse;
    }

    public static List<Product> generateListOfProductEntities() {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product(1L, "000001", "BV Lean leather ankle boots", "boots", 61000));
        productList.add(new Product(2L, "000002", "BV Lean leather ankle boots", "boots", 65000));
        productList.add(new Product(3L, "000003", "Ashlington leather ankle boots", "boots", 69000));
        productList.add(new Product(4L, "000003", "Naima embellished suede sandals", "sandals", 69000));
        productList.add(new Product(5L, "000004", "Naima embellished suede sandals", "sandals", 89000));
        productList.add(new Product(6L, "000005", "Nathane leather sneakers", "sneakers", 80000));

        return productList;
    }

    public static List<ProductResponse> generateExpectedProductResponseList(List<Product> productList, String categoryFilter, int priceFilter) {

        List<ProductResponse> productResponseList = new ArrayList<>();

        productList.stream().filter(product -> product.getCategory().equals(categoryFilter) && (priceFilter != 0 && product.getPrice() <= priceFilter))
                .forEach(
                        product -> {
                            int discount = TestHelper.getDiscount(product);
                            ProductResponse productResponse = new ProductResponse();
                            productResponse.setCategory(product.getCategory());
                            productResponse.setName(product.getName());
                            productResponse.setSku(product.getSku());

                            Price price = new Price();

                            price.setOriginalPrice(product.getPrice());
                            price.setFinalPrice(product.getPrice() - (product.getPrice() * discount / 100));
                            price.setDiscountPercentage(discount > 0 ? discount + "%" : null);
                            price.setCurrency("EUR");

                            productResponse.setPrice(price);

                            productResponseList.add(productResponse);
                        }
                );

        return productResponseList;
    }

    private static int getDiscount(Product product) {
        if (product.getCategory().equals("boots"))
            return 30;
        else if (product.getSku().equals("000003"))
            return 15;
        else
            return 0;
    }
}
