package moneyger4u.domain.repository.income;

import moneyger4u.domain.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeCategoryRepository extends
        JpaRepository<IncomeCategory, Integer> {

}
