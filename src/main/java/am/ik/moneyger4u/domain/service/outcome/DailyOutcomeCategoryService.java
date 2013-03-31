package am.ik.moneyger4u.domain.service.outcome;

import java.util.List;

import am.ik.moneyger4u.domain.model.DailyOutcomeCategory;

public interface DailyOutcomeCategoryService {
    List<DailyOutcomeCategory> findAll();
}
