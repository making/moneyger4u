package am.ik.moneyger4u.domain.service.outcome;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import am.ik.moneyger4u.domain.model.DailyOutcome;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import am.ik.moneyger4u.domain.model.User;

public interface DailyOutcomeService {
    DailyOutcome findOne(Integer dailyOutcomeId);

    void save(DailyOutcome outcome, User user);

    void delete(Integer dailyOutcomeId);

    List<DailyOutcome> findFamilyDailyOutcomeByUserAndDate(User user, Date date);

    List<DailyOutcome> findFamilyDailyOutcomeLikeOutcomeName(
            String outcomeName, User user);

    List<DailyOutcomeReportGroupByOutcomeDate> findFamilyReportGroupByOutcomeDate(
            User user, DateTime start, DateTime end);

    List<DailyOutcomeReportGroupByParentOutcomeCategoryId> findFamilyReportGroupByParentOutcomeCategoryId(
            User user, DateTime start, DateTime end);

    Number findFamilyWasteTotal(User user, DateTime start, DateTime end);
}
