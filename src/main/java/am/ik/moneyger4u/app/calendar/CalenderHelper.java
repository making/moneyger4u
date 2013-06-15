package am.ik.moneyger4u.app.calendar;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByOutcomeDate;
import am.ik.moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import am.ik.moneyger4u.domain.model.MonthlyOutcome;
import am.ik.moneyger4u.domain.model.User;
import am.ik.moneyger4u.domain.service.outcome.DailyOutcomeService;
import am.ik.moneyger4u.domain.service.outcome.MonthlyOutcomeService;

@Component
public class CalenderHelper {

    @Inject
    protected DailyOutcomeService dailyOutcomeService;

    @Inject
    protected MonthlyOutcomeService monthlyOutcomeService;

    public void addDailyOutcomeToModelForMonth(DateTime today,
            DateTime firstDayOfMonth, DateTime lastDayOfMonth, User user,
            Model model) {
        List<DailyOutcomeReportGroupByOutcomeDate> reportsByOutcomeDate = dailyOutcomeService
                .findFamilyReportGroupByOutcomeDate(user, firstDayOfMonth,
                        lastDayOfMonth);
        BigDecimal total = BigDecimal.ZERO;
        for (DailyOutcomeReportGroupByOutcomeDate report : reportsByOutcomeDate) {
            total = total.add(new BigDecimal(report.getAmount().toString()));
        }

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
