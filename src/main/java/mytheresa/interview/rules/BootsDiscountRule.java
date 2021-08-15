package mytheresa.interview.rules;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.enums.DiscountType;

public class BootsDiscountRule implements ICategoryDiscountRule {

    private final String BOOTS_DISCOUNT_RULE = "boots";

    @Override
    public DiscountType getDiscountType(Product product) {
        if (matches(product))
            return DiscountType.BOOTS_CATEGORY_DISCOUNT;
        return DiscountType.NONE;
    }

    @Override
    public boolean matches(Product product) {
        return product.getCategory().equals(BOOTS_DISCOUNT_RULE);
    }
}
