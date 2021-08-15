package mytheresa.interview.rules;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.enums.DiscountType;

public interface IDiscountRule {
    DiscountType getDiscountType(Product product);

    boolean matches(Product product);
}
