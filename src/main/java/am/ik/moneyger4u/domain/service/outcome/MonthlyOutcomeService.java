package am.ik.moneyger4u.domain.service.outcome;

import java.util.List;

import org.joda.time.DateTime;

import am.ik.moneyger4u.domain.model.Family;
import am.ik.moneyger4u.domain.model.MonthlyOutcome;
import am.ik.moneyger4u.domain.model.User;

public interface MonthlyOutcomeService {
    MonthlyOutcome findOne(Integer monthlyOutcomeId);

    void save(MonthlyOutcome monthlyOutcome, User user);

    List<MonthlyOutcome> findFamilyMonthlyOutcomeByFamilyAndMonth(
            Family family, DateTime month);
}
