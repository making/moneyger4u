package moneyger4u.app.daily_outcome;

import com.google.common.collect.Maps;
import moneyger4u.app.daily_outcome.DailyOutcomeForm.DailyOutcomeCreateGroup;
import moneyger4u.app.daily_outcome.DailyOutcomeForm.DailyOutcomeUpdateGroup;
import moneyger4u.domain.model.*;
import moneyger4u.domain.service.outcome.DailyOutcomeService;
import moneyger4u.domain.service.outcome.ParentOutcomeCategoryService;
import moneyger4u.domain.service.user.UserService;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("dailyOutcome")
public class DailyOutcomeController {
    private static final String LAST_PAYMENT = "lastPayment";

    private static final Logger logger = LoggerFactory
            .getLogger(DailyOutcomeController.class);

    @Inject
    Mapper beanMapper;

    @Inject
    DailyOutcomeService dailyOutcomeService;

    @Inject
    ParentOutcomeCategoryService parentOutcomeCategoryService;

    @Inject
    UserService userService;

    @ModelAttribute
    public DailyOutcomeForm setUpForm(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "copyFrom", required = false) Integer copyFrom,
            Model model) {
        DailyOutcomeForm form = new DailyOutcomeForm();
        if (copyFrom != null) {
            DailyOutcome src = dailyOutcomeService.findOne(copyFrom);
            beanMapper.map(src, form);
            form.setOutcomeDate(null);
        }
        if (date != null) {
            DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(date);
            form.setOutcomeDate(dt.toDate());
            model.addAttribute("date", dt);
        }
        return form;
    }

    @ModelAttribute("dailyOutcomeCategoryMap")
    public Map<String, Map<Integer, String>> getDailyOutcomeCategories() {
        List<ParentOutcomeCategory> parents = parentOutcomeCategoryService
                .findAll();
        Map<String, Map<Integer, String>> categoryMap = Maps.newLinkedHashMap();
        for (ParentOutcomeCategory parent : parents) {
            Map<Integer, String> m = Maps.newLinkedHashMap();
            for (DailyOutcomeCategory c : parent.getDailyOutcomeCategoryList()) {
                m.put(c.getDailyOutcomeCategoryId(), c.getCategoryName());
            }
            categoryMap.put(parent.getCategoryName(), m);
        }
        return categoryMap;
    }

    @ModelAttribute("userNameMap")
    public Map<Integer, String> getUserNameMap(@AuthenticationPrincipal MoneygerUserDetails loginUser) {
        Family family = loginUser.getUser().getFamilyId();
        return userService.findByFamilyId(family).stream()
                .collect(Collectors.toMap(User::getUserId, user -> user.getLastName() + " " + user.getFirstName()));
    }

    @ModelAttribute("payments")
    public List<Payment> getPayments() {
        return Arrays.asList(Payment.values());
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public String createForm(
            DailyOutcomeForm form,
            Model model,
            @CookieValue(value = LAST_PAYMENT, required = false) String lastPayment,
            @AuthenticationPrincipal MoneygerUserDetails loginUser) {
        if (form.getOutcomeDate() == null) {
            form.setOutcomeDate(new Date());
        }
        if (form.getQuantity() == null) {
            form.setQuantity(1);
        }
        if (StringUtils.hasText(lastPayment)) {
            form.setPayment(Payment.valueOf(lastPayment));
        }
        if (form.getCreateUserId() == null) {
            form.setCreateUserId(loginUser.getUser().getUserId());
        }
        return "dailyOutcome/createForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "outcomeName")
    public String searchByOutcomeName(
            @RequestParam("outcomeName") String outcomeName, Model model,
            Principal principal) {
        User user = userService.getLoginUser(principal);
        List<DailyOutcome> outcomes = dailyOutcomeService
                .findFamilyDailyOutcomeLikeOutcomeName(outcomeName, user);
        model.addAttribute("outcomes", outcomes);
        model.addAttribute("outcomeName", outcomeName);
        return "dailyOutcome/searchList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Validated({Default.class,
            DailyOutcomeCreateGroup.class}) DailyOutcomeForm form,
                         BindingResult result, RedirectAttributes attributes,
                         HttpServletResponse response) {
        logger.debug("register {}", form);
        if (result.hasErrors()) {
            return "dailyOutcome/createForm";
        }
        Cookie cookie = new Cookie(LAST_PAYMENT, form.getPayment().name());
        response.addCookie(cookie);

        User user = new User(form.getCreateUserId());
        DailyOutcome dailyOutcome = beanMapper.map(form,
                DailyOutcome.class);
        dailyOutcome.setIsWaste(form.isWaste()); // TODO
        dailyOutcomeService.save(dailyOutcome, user);
        attributes.addFlashAttribute("created", dailyOutcome.getOutcomeName())
                .addAttribute("r", new Random(System.nanoTime()).nextInt(Integer.MAX_VALUE))
                .addAttribute("date",
                        new DateTime(dailyOutcome.getOutcomeDate())
                                .toString("yyyy-MM-dd")
                );
        return "redirect:/dailyOutcome?form";
    }

    @RequestMapping(value = "{dailyOutcomeId}", method = RequestMethod.GET)
    public String read(@PathVariable("dailyOutcomeId") Integer dailyOutcomeId,
                       Model model) {
        DailyOutcome dailyOutcome = dailyOutcomeService.findOne(dailyOutcomeId);
        model.addAttribute(dailyOutcome);
        model.addAttribute("date", new DateTime(dailyOutcome.getOutcomeDate()));
        return "dailyOutcome/read";
    }

    @RequestMapping(value = "{dailyOutcomeId}", method = RequestMethod.GET, params = "form")
    public String updateForm(
            @PathVariable("dailyOutcomeId") Integer dailyOutcomeId,
            DailyOutcomeForm form, Model model) {
        DailyOutcome dailyOutcome = dailyOutcomeService.findOne(dailyOutcomeId);
        //beanConverter.convert(dailyOutcome, form, IgnoreOption.NOT_NULL_TARGET);
        beanMapper.map(dailyOutcome, form);
        form.setWaste(dailyOutcome.getIsWaste());
        return "dailyOutcome/updateForm";
    }

    @RequestMapping(value = "{dailyOutcomeId}", method = RequestMethod.PUT)
    public String update(
            @PathVariable("dailyOutcomeId") Integer dailyOutcomeId,
            @Validated({Default.class, DailyOutcomeUpdateGroup.class}) DailyOutcomeForm form,
            BindingResult result, RedirectAttributes attributes,
            @AuthenticationPrincipal MoneygerUserDetails userDetails) {
        logger.debug("update {}", form);
        if (result.hasErrors()) {
            return "dailyOutcome/updateForm";
        }

        User user = userDetails.getUser();
        DailyOutcome dailyOutcome = dailyOutcomeService.findOne(dailyOutcomeId);
        //beanConverter.convert(form, dailyOutcome, IgnoreOption.NULL_SOURCE);
        beanMapper.map(form, dailyOutcome);
        dailyOutcome.setIsWaste(form.isWaste()); // TODO

        dailyOutcomeService.save(dailyOutcome, user);
        attributes.addFlashAttribute("updated", dailyOutcome.getOutcomeName())
                .addAttribute("r", new Random(System.nanoTime()).nextInt(Integer.MAX_VALUE))
                .addAttribute("dailyOutcomeId",
                        dailyOutcome.getDailyOutcomeId());
        return "redirect:/dailyOutcome/{dailyOutcomeId}";
    }

    @RequestMapping(value = "{dailyOutcomeId}", method = RequestMethod.DELETE)
    public String delete(
            @PathVariable("dailyOutcomeId") Integer dailyOutcomeId,
            RedirectAttributes attributes) {
        DailyOutcome dailyOutcome = dailyOutcomeService.findOne(dailyOutcomeId);
        DateTime date = new DateTime(dailyOutcome.getOutcomeDate());
        dailyOutcomeService.delete(dailyOutcomeId);
        attributes.addAttribute("year", date.getYear())
                .addAttribute("month", date.toString("MM"))
                .addAttribute("day", date.toString("dd"))
                .addAttribute("r", new Random(System.nanoTime()).nextInt(Integer.MAX_VALUE));
        return "redirect:/calendar/{year}/{month}/{day}";
    }
}
