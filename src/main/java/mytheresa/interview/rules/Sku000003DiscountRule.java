package mytheresa.interview.rules;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.enums.DiscountType;

public class Sku000003DiscountRule implements ISkuDiscountRule {

    private final String SKU_RULE = "000003";

    @Override
    public DiscountType getDiscountType(Product product) {
        if (matches(product))
            return DiscountType.SKU_00003_DISCOUNT;
        return DiscountType.NONE;
    }

    @Override
    public boolean matches(Product product) {
        return product.getSku().equals(SKU_RULE);
    }
}
