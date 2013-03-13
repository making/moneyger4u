package am.ik.moneyger4u.domain.repository.outcome;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.DailyOutcomeCategory;

public interface DailyOutcomeCategoryRepository
                                               extends
                                               JpaRepository<DailyOutcomeCategory, Integer> {

}
