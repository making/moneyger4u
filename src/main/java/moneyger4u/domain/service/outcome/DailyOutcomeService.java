package moneyger4u.domain.service.outcome;

import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import moneyger4u.domain.model.User;
import moneyger4u.domain.repository.outcome.DailyOutcomeSearchCriteria;
import moneyger4u.domain.repository.outcome.DailyOutcomeSearchResult;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface DailyOutcomeService {
    DailyOutcome findOne(Integer dailyOutcomeId);

    void save(DailyOutcome outcome, User user);

    void save(List<DailyOutcome> outcomes, User user);

    void delete(Integer dailyOutcomeId);

    List<DailyOutcome> findFamilyDailyOutcomeByUserAndDate(User user, Date date);

    List<DailyOutcome> findFamilyDailyOutcomeLikeOutcomeName(
            String outcomeName, User user);

    List<DailyOutcomeReportGroupByOutcomeDate> findFamilyReportGroupByOutcomeDate(
            User user, DateTime start, DateTime end);

    List<DailyOutcomeReportGroupByParentOutcomeCategoryId> findFamilyReportGroupByParentOutcomeCategoryId(
            User user, DateTime start, DateTime end);

    Number findFamilyWasteTotal(User user, DateTime start, DateTime end);

    DailyOutcomeSearchResult search(DailyOutcomeSearchCriteria criteria,
                                    Pageable pageable);
}
