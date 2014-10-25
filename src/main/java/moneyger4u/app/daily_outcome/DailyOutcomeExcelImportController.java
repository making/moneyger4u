package moneyger4u.app.daily_outcome;

import moneyger4u.domain.model.DailyOutcome;
import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.MoneygerUserDetails;
import moneyger4u.domain.model.User;
import moneyger4u.domain.service.outcome.DailyOutcomeExcelMapper;
import moneyger4u.domain.service.outcome.DailyOutcomeService;
import moneyger4u.domain.service.user.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("dailyOutcome")
public class DailyOutcomeExcelImportController {

    @Inject
    UserService userService;

    @Inject
    DailyOutcomeExcelMapper dailyOutcomeExcelMapper;
    @Inject
    DailyOutcomeService dailyOutcomeService;

    @ModelAttribute("userNameMap")
    Map<Integer, String> getUserNameMap(@AuthenticationPrincipal MoneygerUserDetails loginUser) {
        Family family = loginUser.getUser().getFamilyId();
        return userService.findByFamilyId(family).stream()
                .collect(Collectors.toMap(User::getUserId, user -> user.getLastName() + " " + user.getFirstName()));
    }

    @ModelAttribute
    DailyOutcomeExcelImportForm setUpForm(@AuthenticationPrincipal MoneygerUserDetails loginUser) {
        DailyOutcomeExcelImportForm form = new DailyOutcomeExcelImportForm();
        form.setCreateUserId(loginUser.getUser().getUserId());
        return form;
    }

    @RequestMapping(value = "import", params = "form", method = RequestMethod.GET)
    String importForm() {
        return "dailyOutcome/importForm";
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    String importExcel(@Validated DailyOutcomeExcelImportForm form, BindingResult bindingResult, RedirectAttributes attributes) throws Exception {
        if (bindingResult.hasErrors()) {
            return importForm();
        }
        User user = new User(form.getCreateUserId());
        try (InputStream inputStream = form.getFile().getInputStream()) {
            List<DailyOutcome> outcomes = dailyOutcomeExcelMapper.map(inputStream);
            dailyOutcomeService.save(outcomes, user);
            attributes.addFlashAttribute("created", outcomes.size());
        }
        return "redirect:/dailyOutcome/import?form";
    }

    @RequestMapping(value = "template.xlsx", produces = "application/vnd.ms-excel")
    @ResponseBody
    Resource download() throws Exception {
        return new ClassPathResource("static/excel/template.xlsx");
    }
}
