package moneyger4u.domain.repository.outcome;

import moneyger4u.domain.model.MonthlyOutcomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyOutcomeCategoryRepository
        extends
        JpaRepository<MonthlyOutcomeCategory, Integer> {

}
