package mytheresa.interview.rest.controller;

import mytheresa.interview.rest.response.ProductResponse;
import mytheresa.interview.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(@Param("category") String category, @Param("priceFilter") Integer priceFilter) {
        List<ProductResponse> productList = productService.findByCategoryAndPriceLessThanEqual(category, priceFilter);
        return ResponseEntity.ok(productList);
    }
}
