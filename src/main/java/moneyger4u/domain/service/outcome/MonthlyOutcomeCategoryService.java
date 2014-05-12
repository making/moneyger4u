package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.MonthlyOutcomeCategory;

import java.util.List;

public interface MonthlyOutcomeCategoryService {
    List<MonthlyOutcomeCategory> findAll();
}
