package moneyger4u.domain.repository.outcome;

import moneyger4u.domain.model.ParentOutcomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentOutcomeCategoryRepository
        extends
        JpaRepository<ParentOutcomeCategory, Integer> {

}
