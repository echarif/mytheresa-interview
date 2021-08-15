package mytheresa.interview.specification;

import lombok.Data;
import mytheresa.interview.domain.entity.Product;
import mytheresa.interview.domain.enums.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductSpecification implements Specification<Product> {

    List<ProductSearchCriteria> searchCriteriaList;

    public ProductSpecification(){
        searchCriteriaList = new ArrayList<>();
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        searchCriteriaList.forEach(searchCriteria -> {
            if (searchCriteria.getSearchOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(criteriaBuilder.equal(root.get(searchCriteria.getKey()),
                        searchCriteria.getValue().toString()));
            } else if (searchCriteria.getSearchOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()
                ), (Integer) searchCriteria.getValue()));
            }
        });
        return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    }
}
