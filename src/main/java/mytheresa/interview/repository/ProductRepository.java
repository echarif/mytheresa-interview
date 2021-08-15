package mytheresa.interview.repository;

import lombok.extern.slf4j.Slf4j;
import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.enums.SearchOperation;
import mytheresa.interview.specification.ProductSearchCriteria;
import mytheresa.interview.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ProductRepository {

    private final IProductRepository productRepository;

    @Value("${spring.application.query.max-rows}")
    private String maxRowsPerQuery;

    public ProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByCategoryAndPrice(String category, Integer priceFilter) {

        List<Product> productList = new ArrayList<>();
        try {
            String CATEGORY_FIELD_PATH = "category";
            String PRICE_FIELD_PATH = "price";

            ProductSpecification productSpecification = new ProductSpecification();

            productSpecification.getSearchCriteriaList().add(new ProductSearchCriteria(CATEGORY_FIELD_PATH, category,
                    SearchOperation.EQUAL));

            if (priceFilter != null) {
                productSpecification.getSearchCriteriaList().add(new ProductSearchCriteria(PRICE_FIELD_PATH, priceFilter,
                        SearchOperation.LESS_THAN_EQUAL));
            }

            productList = productRepository.findAll(productSpecification, PageRequest.of(0,
                    Integer.parseInt(maxRowsPerQuery)))
                    .getContent();
        } catch (Exception ex) {
            log.error("Error while searching products: " + ex.getLocalizedMessage());
        }
        return productList;
    }
}
