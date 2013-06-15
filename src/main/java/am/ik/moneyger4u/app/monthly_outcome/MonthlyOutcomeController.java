package am.ik.moneyger4u.app.monthly_outcome;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;

import am.ik.moneyger4u.common.bean.BeanConverter;
import am.ik.moneyger4u.domain.model.MonthlyOutcome;
import am.ik.moneyger4u.domain.model.MonthlyOutcomeCategory;
import am.ik.moneyger4u.domain.model.User;
import am.ik.moneyger4u.domain.service.outcome.MonthlyOutcomeCategoryService;
import am.ik.moneyger4u.domain.service.outcome.MonthlyOutcomeService;
import am.ik.moneyger4u.domain.service.user_details.UserDetailsUtils;

@Controller
@RequestMapping("monthlyOutcome")
public class MonthlyOutcomeController {
    @Inject
    protected MonthlyOutcomeService monthlyOutcomeService;

    @Inject
    protected MonthlyOutcomeCategoryService monthlyOutcomeCategoryService;

    private BeanConverter beanConverter;

    public MonthlyOutcomeController() {
        beanConverter = new BeanConverter(new Converter<Integer, MonthlyOutcomeCategory>() {
            @Override
            public MonthlyOutcomeCategory convert(
                    Integer monthlyOutcomeCategoryId) {
                return new MonthlyOutcomeCategory(monthlyOutcomeCategoryId);
            }
        }, new Converter<MonthlyOutcomeCategory, Integer>() {
            @Override
            public Integer convert(MonthlyOutcomeCategory monthlyOutcomeCategory) {
                return monthlyOutcomeCategory.getMonthlyOutcomeCategoryId();
            }
        });
    }

    @ModelAttribute
    public MonthlyOutcomeForm setUpForm() {
        MonthlyOutcomeForm form = new MonthlyOutcomeForm();
        return form;
    }

    @ModelAttribute("monthlyOutcomeCategoryMap")
    public Map<Integer, String> getDailyOutcomeCategories() {
        List<MonthlyOutcomeCategory> parents = monthlyOutcomeCategoryService
                .findAll();
        Map<Integer, String> categoryMap = Maps.newLinkedHashMap();
        for (MonthlyOutcomeCategory parent : parents) {
            categoryMap.put(parent.getMonthlyOutcomeCategoryId(), parent
                    .getCategoryName());
        }
        return categoryMap;
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public String createForm(MonthlyOutcomeForm form, Model model) {
        return "monthlyOutcome/createForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(MonthlyOutcomeForm form, BindingResult result,
            RedirectAttributes attributes, Principal principal) {

        if (result.hasErrors()) {
            return "monthlyOutcome/createForm";
        }
        User user = UserDetailsUtils.getUserDetails(principal).getUser();
        MonthlyOutcome monthlyOutcome = beanConverter.populate(form,
                MonthlyOutcome.class);
        monthlyOutcomeService.save(monthlyOutcome, user);
        return "redirect:/monthlyOutcome/?form";
    }

}
