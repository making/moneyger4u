package am.ik.moneyger4u.domain.service.outcome;

import java.util.List;

import am.ik.moneyger4u.domain.model.MonthlyOutcomeCategory;

public interface MonthlyOutcomeCategoryService {
    List<MonthlyOutcomeCategory> findAll();
}
