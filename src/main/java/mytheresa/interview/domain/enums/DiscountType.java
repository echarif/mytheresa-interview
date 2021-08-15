package mytheresa.interview.domain.enums;

import lombok.Getter;

@Getter
public enum DiscountType {
    BOOTS_CATEGORY_DISCOUNT("boots_discount", 30),
    SKU_00003_DISCOUNT("sku_discount", 15),
    NONE("", 0);

    private final String discountName;
    private final Integer discountAmount;

    DiscountType(String discountName, Integer discountAmount) {
        this.discountName = discountName;
        this.discountAmount = discountAmount;
    }
}
