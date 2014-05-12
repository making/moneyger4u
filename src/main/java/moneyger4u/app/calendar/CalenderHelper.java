package moneyger4u.app.calendar;

import moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import moneyger4u.domain.model.MonthlyOutcome;
import moneyger4u.domain.model.User;
import moneyger4u.domain.service.outcome.DailyOutcomeService;
import moneyger4u.domain.service.outcome.MonthlyOutcomeService;
import org.joda.time.DateTime;
import org.springframework.ui.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;

@Named
public class CalenderHelper {

    @Inject
    DailyOutcomeService dailyOutcomeService;

    @Inject
    MonthlyOutcomeService monthlyOutcomeService;

    public void addDailyOutcomeToModelForMonth(DateTime today,
                                               DateTime firstDayOfMonth, DateTime lastDayOfMonth, User user,
                                               Model model) {
        List<DailyOutcomeReportGroupByOutcomeDate> reportsByOutcomeDate = dailyOutcomeService
                .findFamilyReportGroupByOutcomeDate(user, firstDayOfMonth,
                        lastDayOfMonth);
        BigDecimal total = DailyOutcomeReportGroupByOutcomeDate.calcTotal(reportsByOutcomeDate).getValue();

        List<DailyOutcomeReportGroupByParentOutcomeCategoryId> reportsByParentOutcomeCategoryId = dailyOutcomeService
                .findFamilyReportGroupByParentOutcomeCategoryId(user,
                        firstDayOfMonth, lastDayOfMonth);

        Number wasteTotal = dailyOutcomeService.findFamilyWasteTotal(user,
                firstDayOfMonth, lastDayOfMonth);

        model.addAttribute("reportsByOutcomeDate", reportsByOutcomeDate);
        model.addAttribute("reportsByParentOutcomeCategoryId",
                reportsByParentOutcomeCategoryId);
        model.addAttribute("wasteTotal", wasteTotal);
        model.addAttribute("total", total);
    }

    public void addMonthlyOutcomeToModelForMonth(DateTime today, User user,
                                                 Model model) {
        List<MonthlyOutcome> monthlyOutcomes = monthlyOutcomeService
                .findFamilyMonthlyOutcomeByFamilyAndMonth(user.getFamilyId(),
                        today);
        model.addAttribute("monthlyOutcomes", monthlyOutcomes);
    }
}
