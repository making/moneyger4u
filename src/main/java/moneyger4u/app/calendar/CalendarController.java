package moneyger4u.app.calendar;

import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.DailyOutcomeReportGroupByParentOutcomeCategoryId;
import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.User;
import moneyger4u.domain.service.calendar.CalendarService;
import moneyger4u.domain.service.outcome.DailyOutcomeService;
import moneyger4u.domain.service.user.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("calendar")
public class CalendarController {
    private static final Logger logger = LoggerFactory
            .getLogger(CalendarController.class);

    @Inject
    DailyOutcomeService dailyOutcomeService;

    @Inject
    CalendarService calendarService;

    @Inject
    CalenderHelper calenderHelper;

    @Inject
    UserService userService;

    @ModelAttribute("weekArray")
    public String[] getWeekArray(Locale locale) {
        return calendarService.getWeekArray(locale);
    }

    @RequestMapping
    public String thisMonth(Model model, Principal principal) {
        DateTime today = new DateTime();
        logger.debug("today is {}", today);
        return month(today.getYear(), today.getMonthOfYear(), model, principal);
    }

    @RequestMapping("{year}/{month}")
    public String month(@PathVariable("year") int year,
                        @PathVariable("month") int month, Model model, Principal principal) {
        DateTime today = new DateTime(year, month, 1, 0, 0);
        DateTime firstDayOfMonth = today.dayOfMonth().withMinimumValue();
        DateTime lastDayOfMonth = firstDayOfMonth.dayOfMonth()
                .withMaximumValue();

        User user = userService.getLoginUser(principal);

        model.addAttribute("firstDayOfMonth", firstDayOfMonth);
        model.addAttribute("lastDayOfMonth", lastDayOfMonth);
        model.addAttribute("today", today);
        // dailyOutcome
        calenderHelper.addDailyOutcomeToModelForMonth(today, firstDayOfMonth,
                lastDayOfMonth, user, model);
        // montlyOutcome
        calenderHelper.addMonthlyOutcomeToModelForMonth(today, user, model);
        return "calendar/month";
    }

    @RequestMapping("{year}/{month}/{day}")
    public String day(@PathVariable("year") int year,
                      @PathVariable("month") int month, @PathVariable("day") int day,
                      Model model, Principal principal) {
        DateTime today = new DateTime(year, month, day, 0, 0);
        //User user = UserDetailsUtils.getUserDetails(principal).getUser();
        User user = new User();
        user.setUserId(1);
        user.setFamilyId(new Family(1));

        List<DailyOutcome> outcomes = dailyOutcomeService
                .findFamilyDailyOutcomeByUserAndDate(user, today.toDate());

        model.addAttribute("today", today);
        model.addAttribute("outcomes", outcomes);

        DailyOutcome.Total total = DailyOutcome.calcTotal(outcomes);

        model.addAttribute("total", total.getAll());
        model.addAttribute("wasteTotal", total.getWaste());
        model.addAttribute("cardTotal", total.getCard());
        return "calendar/day";
    }

    @RequestMapping(headers = "Accept=" + MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GraphData> thisMonthJson(Principal principal) {
        DateTime today = new DateTime();
        return monthJson(today.getYear(), today.getMonthOfYear(), principal);
    }

    @RequestMapping("{year}/{month}.json")
    @ResponseBody
    public List<GraphData>
    monthJson(@PathVariable("year") int year,
              @PathVariable("month") int month, Principal principal) {
        DateTime today = new DateTime(year, month, 1, 0, 0);
        DateTime firstDayOfMonth = today.dayOfMonth().withMinimumValue();
        DateTime lastDayOfMonth = firstDayOfMonth.dayOfMonth()
                .withMaximumValue();

        User user = userService.getLoginUser(principal);

        List<DailyOutcomeReportGroupByParentOutcomeCategoryId> reportsByParentOutcomeCategoryId = dailyOutcomeService
                .findFamilyReportGroupByParentOutcomeCategoryId(user,
                        firstDayOfMonth, lastDayOfMonth);

        return reportsByParentOutcomeCategoryId.stream()
                .map(x -> new GraphData(x.getParentOutcomeCategory().getCategoryName(), x.getAmount()))
                .collect(Collectors.toList());
    }
}
