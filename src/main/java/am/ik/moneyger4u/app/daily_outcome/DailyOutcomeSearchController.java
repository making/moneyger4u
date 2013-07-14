package am.ik.moneyger4u.app.daily_outcome;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;

import am.ik.moneyger4u.domain.model.DailyOutcomeCategory;
import am.ik.moneyger4u.domain.model.Family;
import am.ik.moneyger4u.domain.model.MoneygerUserDetails;
import am.ik.moneyger4u.domain.model.ParentOutcomeCategory;
import am.ik.moneyger4u.domain.model.Payment;
import am.ik.moneyger4u.domain.model.User;
import am.ik.moneyger4u.domain.repository.outcome.DailyOutcomeSearchCriteria;
import am.ik.moneyger4u.domain.repository.outcome.DailyOutcomeSearchResult;
import am.ik.moneyger4u.domain.service.outcome.DailyOutcomeService;
import am.ik.moneyger4u.domain.service.outcome.ParentOutcomeCategoryService;
import am.ik.moneyger4u.domain.service.user.UserService;
import am.ik.moneyger4u.domain.service.user_details.UserDetailsUtils;

@Controller
@RequestMapping("dailyOutcome")
public class DailyOutcomeSearchController {
	private static final Logger logger = LoggerFactory
			.getLogger(DailyOutcomeSearchController.class);

	@Inject
	protected DailyOutcomeService dailyOutcomeService;

	@Inject
	protected ParentOutcomeCategoryService parentOutcomeCategoryService;

	@Inject
	protected UserService userService;

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
			@PageableDefaults Pageable pageable, Model model) {
		logger.info("search={}", criteria);

		DailyOutcomeSearchResult result = dailyOutcomeService.search(criteria,
				pageable);

		model.addAttribute("result", result);
		return "dailyOutcome/searchForm";
	}
}
