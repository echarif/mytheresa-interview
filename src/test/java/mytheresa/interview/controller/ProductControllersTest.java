package mytheresa.interview.controller;

import mytheresa.interview.MyTheresaCodeTestApplication;
import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.repository.IProductRepository;
import mytheresa.interview.utils.TestHelper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MyTheresaCodeTestApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductRepository productRepository;

    private List<Product> productList;

    @BeforeAll
    private void setUp() {
        productList = TestHelper.generateListOfProductEntities();
        productRepository.saveAll(productList);
    }

    @Test
    void get_all_products_when_search_without_price_filter() throws Exception {
        mockMvc.perform(get("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("category", "boots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andDo(print());
    }

    @Test
    void get_products_match_filter_when_search_with_price_filter() throws Exception {
        mockMvc.perform(get("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("category", "boots")
                .param("priceFilter", "61000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andDo(print());
    }

    @Test
    void get_products_with_category_discount_when_search_with_boots_category() throws Exception {
        mockMvc.perform(get("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("category", "boots")
                .param("priceFilter", "61000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].price.final", Matchers.equalTo(42700)))
                .andExpect(jsonPath("$.[0].price.discount_percentage", Matchers.equalTo("30%")))
                .andDo(print());
    }

    @Test
    void get_products_with_sku_discount_when_search_with_sku000003_product() throws Exception {
        mockMvc.perform(get("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("category", "sandals")
                .param("priceFilter", "69000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].price.final", Matchers.equalTo(58650)))
                .andExpect(jsonPath("$.[0].price.discount_percentage", Matchers.equalTo("15%")))
                .andDo(print());
    }

    @Test
    void get_products_without_discount_when_no_discount_matches() throws Exception {
        mockMvc.perform(get("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .param("category", "sneakers")
                .param("priceFilter", "91000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].price.final", Matchers.equalTo(80000)))
                .andExpect(jsonPath("$.[0].price.discount_percentage", Matchers.equalTo(null)))
                .andDo(print());
    }
}
