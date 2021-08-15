package mytheresa.interview.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Price {

    @JsonProperty("original")
    private Integer originalPrice;

    @JsonProperty("final")
    private Integer finalPrice;

    @JsonProperty("discount_percentage")
    private String discountPercentage;

    @JsonProperty("currency")
    private String currency = "EUR";
}
