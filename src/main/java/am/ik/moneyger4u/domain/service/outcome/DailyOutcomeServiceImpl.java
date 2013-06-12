package am.ik.moneyger4u.domain.service.outcome;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.DailyOutcome;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import am.ik.moneyger4u.domain.model.User;
import am.ik.moneyger4u.domain.repository.outcome.DailyOutcomeRepository;

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
        if (logger.isDebugEnabled()) {
            logger.debug("save {}", ToStringBuilder.reflectionToString(outcome,
                    ToStringStyle.SHORT_PREFIX_STYLE));
        }
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
        return dailyOutcomeRepository.findFamilyReportGroupByOutcomeDate(user
                .getFamilyId(), start.toDate(), end.toDate());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyOutcomeReportGroupByParentOutcomeCategoryId> findFamilyReportGroupByParentOutcomeCategoryId(
            User user, DateTime start, DateTime end) {
        List<DailyOutcomeReportGroupByParentOutcomeCategoryId> result = dailyOutcomeRepository
                .findFamilyReportGroupByParentOutcomeCategoryId(user
                        .getFamilyId(), start.toDate(), end.toDate());
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
}
