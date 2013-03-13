package am.ik.moneyger4u.domain.repository.outcome;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.MonthlyOutcomeCategory;

public interface MonthlyOutcomeCategoryRepository
                                               extends
                                                 JpaRepository<MonthlyOutcomeCategory, Integer> {

}
