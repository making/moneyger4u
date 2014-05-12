package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.MonthlyOutcome;
import moneyger4u.domain.model.User;
import org.joda.time.DateTime;

import java.util.List;

public interface MonthlyOutcomeService {
    MonthlyOutcome findOne(Integer monthlyOutcomeId);

    void save(MonthlyOutcome monthlyOutcome, User user);

    List<MonthlyOutcome> findFamilyMonthlyOutcomeByFamilyAndMonth(
            Family family, DateTime month);
}
