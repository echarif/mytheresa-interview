package mytheresa.interview.rules;

import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.enums.DiscountType;
import java.util.ArrayList;
import java.util.List;

public class DiscountRulesEngine {
    private final List<IDiscountRule> discountRules;

    public DiscountRulesEngine() {
        discountRules = new ArrayList<>();
    }

    public DiscountRulesEngine registerNewRule(IDiscountRule discountRule) {
        discountRules.add(discountRule);
        return this;
    }

    public List<Integer> processRules(Product product) {
        List<Integer> discounts = new ArrayList<>();
        discountRules.forEach(discountRule -> {
            DiscountType discountType = discountRule.getDiscountType(product);
            if (discountType.getDiscountAmount() > 0) {
                discounts.add(discountType.getDiscountAmount());
            }
        });

        return discounts;
    }
}
