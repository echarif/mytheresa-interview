package mytheresa.interview.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ProductRepositoryTests {

    private String maxRowsPerQuery = "5";

    @Mock
    protected EntityManagerFactory entityManager;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery criteriaQuery;

    @InjectMocks
    @Spy
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        //entityManager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
        ReflectionTestUtils.setField(productRepository, "maxRowsPerQuery", maxRowsPerQuery);
    }

    @Test
    void filter_products_by_an_existing_category() {
        var category = "boots";

    }

    @Test
    void filter_products_by_non_existing_category() {

    }

    @Test
    void filter_products_by_price_less_than() {

    }

    @Test
    void filter_products_by_price_greater_than() {

    }

    @Test
    void size_of_product_list_greater_than_5() {

    }

    @Test
    void size_of_product_list_less_than_5() {

    }

    @Test
    void size_of_product_list_less_than_0() {

    }
}
