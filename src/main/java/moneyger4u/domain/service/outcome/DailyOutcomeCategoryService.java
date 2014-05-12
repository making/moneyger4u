package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.DailyOutcomeCategory;

import java.util.List;

public interface DailyOutcomeCategoryService {
    List<DailyOutcomeCategory> findAll();
}
