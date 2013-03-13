package am.ik.moneyger4u.domain.repository.income;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.IncomeCategory;

public interface IncomeCategoryRepository extends
                                         JpaRepository<IncomeCategory, Integer> {

}
