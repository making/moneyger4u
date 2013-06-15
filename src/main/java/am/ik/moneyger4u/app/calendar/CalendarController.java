package am.ik.moneyger4u.app.calendar;

import java.math.BigInteger;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import am.ik.moneyger4u.domain.model.DailyOutcome;
import am.ik.moneyger4u.domain.model.Payment;
import am.ik.moneyger4u.domain.model.User;
import am.ik.moneyger4u.domain.service.calendar.CalendarService;
import am.ik.moneyger4u.domain.service.outcome.DailyOutcomeService;
import am.ik.moneyger4u.domain.service.user_details.UserDetailsUtils;

@Controller
@RequestMapping("calendar")
public class CalendarController {
    private static final Logger logger = LoggerFactory
            .getLogger(CalendarController.class);

    @Inject
    protected DailyOutcomeService dailyOutcomeService;

    @Inject
    protected CalendarService calendarService;

    @Inject
    protected CalenderHelper calenderHelper;

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

        User user = UserDetailsUtils.getUserDetails(principal).getUser();

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
        User user = UserDetailsUtils.getUserDetails(principal).getUser();
        List<DailyOutcome> outcomes = dailyOutcomeService
                .findFamilyDailyOutcomeByUserAndDate(user, today.toDate());
        BigInteger total = BigInteger.ZERO;
        BigInteger wasteTotal = BigInteger.ZERO;
        BigInteger cardTotal = BigInteger.ZERO;

        model.addAttribute("today", today);
        model.addAttribute("outcomes", outcomes);
        for (DailyOutcome outcome : outcomes) {
            BigInteger subtotal = BigInteger.valueOf(outcome.getAmount())
                    .multiply(BigInteger.valueOf(outcome.getQuantity()));
            total = total.add(subtotal);
            if (outcome.getIsWaste()) {
                wasteTotal = wasteTotal.add(subtotal);
            }
            if (Payment.CREDITCARD == outcome.getPayment()) {
                cardTotal = cardTotal.add(subtotal);
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("wasteTotal", wasteTotal);
        model.addAttribute("cardTotal", cardTotal);
        return "calendar/day";
    }
}
