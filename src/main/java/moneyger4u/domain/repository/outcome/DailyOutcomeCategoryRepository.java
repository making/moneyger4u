package moneyger4u.domain.repository.outcome;

import moneyger4u.domain.model.DailyOutcomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyOutcomeCategoryRepository
        extends
        JpaRepository<DailyOutcomeCategory, Integer> {

}
