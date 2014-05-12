package moneyger4u.app.daily_outcome;

import com.google.common.collect.Maps;
import moneyger4u.domain.model.*;
import moneyger4u.domain.repository.outcome.DailyOutcomeSearchCriteria;
import moneyger4u.domain.repository.outcome.DailyOutcomeSearchResult;
import moneyger4u.domain.service.outcome.DailyOutcomeService;
import moneyger4u.domain.service.outcome.ParentOutcomeCategoryService;
import moneyger4u.domain.service.user.UserService;
import moneyger4u.domain.service.user_details.UserDetailsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dailyOutcome")
public class DailyOutcomeSearchController {
    private static final Logger logger = LoggerFactory
            .getLogger(DailyOutcomeSearchController.class);

    @Inject
    DailyOutcomeService dailyOutcomeService;

    @Inject
    ParentOutcomeCategoryService parentOutcomeCategoryService;

    @Inject
    UserService userService;

    @ModelAttribute
    public DailyOutcomeSearchCriteria setUpForm() {
        DailyOutcomeSearchCriteria criteria = new DailyOutcomeSearchCriteria();
        return criteria;
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

    @ModelAttribute("userIdMap")
    public Map<Integer, String> getUserIdMap(Principal principal) {
        Map<Integer, String> userIdMap = Maps.newLinkedHashMap();
        MoneygerUserDetails userDetails = UserDetailsUtils
                .getUserDetails(principal);
        Family familyId = userDetails.getUser().getFamilyId();
        List<User> users = userService.findByFamilyId(familyId);
        for (User user : users) {
            userIdMap.put(user.getUserId(),
                    user.getLastName() + " " + user.getFirstName());
        }
        return userIdMap;
    }

    @ModelAttribute("payments")
    public List<Payment> getPayments() {
        return Arrays.asList(Payment.values());
    }

    @RequestMapping(value = "search", params = "form")
    public String searchForm(DailyOutcomeSearchCriteria criteria, Model model) {
        logger.info("search={}", criteria);
        return "dailyOutcome/searchForm";
    }

    @RequestMapping(value = "search")
    public String search(DailyOutcomeSearchCriteria criteria,
                         @PageableDefault Pageable pageable, Model model) {
        logger.info("search={}", criteria);

        DailyOutcomeSearchResult result = dailyOutcomeService.search(criteria,
                pageable);

        model.addAttribute("result", result);
        return "dailyOutcome/searchForm";
    }
}
