package mytheresa.interview.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import mytheresa.interview.domain.valueobject.Price;

@Data
public class ProductResponse {

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private String category;

    @JsonProperty("price")
    private Price price;
}
