package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.MonthlyOutcome;
import moneyger4u.domain.model.User;
import moneyger4u.domain.repository.outcome.MonthlyOutcomeRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MonthlyOutcomeServiceImpl implements MonthlyOutcomeService {
    @Inject
    MonthlyOutcomeRepository monthlyOutcomeRepository;

    @Override
    @Transactional(readOnly = true)
    public MonthlyOutcome findOne(Integer monthlyOutcomeId) {
        return monthlyOutcomeRepository.findOne(monthlyOutcomeId);
    }

    @Override
    public void save(MonthlyOutcome monthlyOutcome, User user) {
        Date now = new Date();
        Family family = user.getFamilyId();
        if (monthlyOutcome.getCreatedAt() == null) {
            monthlyOutcome.setCreatedAt(now);
            monthlyOutcome.setFamilyId(family);
        }
        monthlyOutcome.setUpdatedAt(now);
        monthlyOutcome.setUpdatedBy(user);

        monthlyOutcomeRepository.save(monthlyOutcome);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonthlyOutcome> findFamilyMonthlyOutcomeByFamilyAndMonth(
            Family family, DateTime month) {
        DateTime firstDayOfMonth = month.dayOfMonth().withMinimumValue();
        DateTime lastDayOfMonth = firstDayOfMonth.dayOfMonth()
                .withMaximumValue();
        return monthlyOutcomeRepository
                .findFamilyMonthlyOutcomeByFamilyAndOutcomeDate(family,
                        firstDayOfMonth.toDate(), lastDayOfMonth.toDate());
    }

}
