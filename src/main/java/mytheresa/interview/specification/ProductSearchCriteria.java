package mytheresa.interview.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import mytheresa.interview.domain.enums.SearchOperation;

@AllArgsConstructor
@Data
public class ProductSearchCriteria {

    private final String key;
    private final Object value;
    private final SearchOperation searchOperation;
}
