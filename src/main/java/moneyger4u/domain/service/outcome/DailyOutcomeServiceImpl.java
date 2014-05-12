package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import moneyger4u.domain.model.User;
import moneyger4u.domain.repository.outcome.DailyOutcomeRepository;
import moneyger4u.domain.repository.outcome.DailyOutcomeSearchCriteria;
import moneyger4u.domain.repository.outcome.DailyOutcomeSearchResult;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DailyOutcomeServiceImpl implements DailyOutcomeService {
    private static final Logger logger = LoggerFactory
            .getLogger(DailyOutcomeServiceImpl.class);

    @Inject
    protected DailyOutcomeRepository dailyOutcomeRepository;

    @Override
    @Transactional(readOnly = true)
    public DailyOutcome findOne(Integer dailyOutcomeId) {
        return dailyOutcomeRepository.findOne(dailyOutcomeId);
    }

    @Override
    public void save(DailyOutcome outcome, User user) {
        Date now = new Date();

        if (outcome.getCreatedAt() == null) {
            outcome.setCreatedAt(now);
            outcome.setUserId(user);
        }
        outcome.setUpdatedAt(now);
        outcome.setUpdatedBy(user);
        dailyOutcomeRepository.save(outcome);
    }

    @Override
    public void delete(Integer dailyOutcomeId) {
        logger.debug("delete dailyOutcomeId={}", dailyOutcomeId);
        dailyOutcomeRepository.delete(dailyOutcomeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcome> findFamilyDailyOutcomeByUserAndDate(User user,
                                                                  Date date) {
        List<DailyOutcome> outcomes = dailyOutcomeRepository
                .findFamilyDailyOutcomeByUserAndDate(user.getFamilyId(), date);
        return outcomes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcome> findFamilyDailyOutcomeLikeOutcomeName(
            String outcomeName, User user) {
        List<DailyOutcome> outcomes = dailyOutcomeRepository
                .findFamilyDailyOutcomeLikeOutcomeName("%" + outcomeName + "%",
                        user.getFamilyId());
        return outcomes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcomeReportGroupByOutcomeDate> findFamilyReportGroupByOutcomeDate(
            User user, DateTime start, DateTime end) {
        return dailyOutcomeRepository.findFamilyReportGroupByOutcomeDate(
                user.getFamilyId(), start.toDate(), end.toDate());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcomeReportGroupByParentOutcomeCategoryId> findFamilyReportGroupByParentOutcomeCategoryId(
            User user, DateTime start, DateTime end) {
        List<DailyOutcomeReportGroupByParentOutcomeCategoryId> result = dailyOutcomeRepository
                .findFamilyReportGroupByParentOutcomeCategoryId(
                        user.getFamilyId(), start.toDate(), end.toDate());
        for (DailyOutcomeReportGroupByParentOutcomeCategoryId report : result) {
            report.getParentOutcomeCategory().getCategoryName();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Number findFamilyWasteTotal(User user, DateTime start, DateTime end) {
        return dailyOutcomeRepository.findFamilyWasteTotal(user.getFamilyId(),
                start.toDate(), end.toDate());
    }

    @Override
    @Transactional(readOnly = true)
    public DailyOutcomeSearchResult search(DailyOutcomeSearchCriteria criteria,
                                           Pageable pageable) {
        return dailyOutcomeRepository.search(criteria, pageable);
    }
}
