package am.ik.moneyger4u.domain.repository.income;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
