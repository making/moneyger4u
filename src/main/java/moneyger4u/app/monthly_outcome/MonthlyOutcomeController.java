package moneyger4u.app.monthly_outcome;

import com.google.common.collect.Maps;
import moneyger4u.domain.model.MonthlyOutcome;
import moneyger4u.domain.model.MonthlyOutcomeCategory;
import moneyger4u.domain.model.User;
import moneyger4u.domain.service.outcome.MonthlyOutcomeCategoryService;
import moneyger4u.domain.service.outcome.MonthlyOutcomeService;
import moneyger4u.domain.service.user_details.UserDetailsUtils;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("monthlyOutcome")
public class MonthlyOutcomeController {
    @Inject
    MonthlyOutcomeService monthlyOutcomeService;

    @Inject
    MonthlyOutcomeCategoryService monthlyOutcomeCategoryService;

    @Inject
    Mapper beanMapper;

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
        MonthlyOutcome monthlyOutcome = beanMapper.map(form,
                MonthlyOutcome.class);
        monthlyOutcomeService.save(monthlyOutcome, user);
        return "redirect:/monthlyOutcome/?form";
    }

}
