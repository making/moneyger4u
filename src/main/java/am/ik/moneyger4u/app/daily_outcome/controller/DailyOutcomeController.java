package am.ik.moneyger4u.app.daily_outcome.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;

import am.ik.moneyger4u.app.daily_outcome.model.DailyOutcomeForm;
import am.ik.moneyger4u.app.daily_outcome.model.DailyOutcomeForm.DailyOutcomeCreateGroup;
import am.ik.moneyger4u.app.daily_outcome.model.DailyOutcomeForm.DailyOutcomeUpdateGroup;
import am.ik.moneyger4u.common.bean.BeanConverter;
import am.ik.moneyger4u.common.bean.IgnoreOption;
import am.ik.moneyger4u.domain.model.DailyOutcome;
import am.ik.moneyger4u.domain.model.DailyOutcomeCategory;
import am.ik.moneyger4u.domain.model.ParentOutcomeCategory;
import am.ik.moneyger4u.domain.model.Payment;
import am.ik.moneyger4u.domain.service.outcome.DailyOutcomeService;
import am.ik.moneyger4u.domain.service.outcome.ParentOutcomeCategoryService;

@Controller
@RequestMapping("dailyOutcome")
public class DailyOutcomeController {
    private static final Logger logger = LoggerFactory
            .getLogger(DailyOutcomeController.class);

    private BeanConverter beanConverter;

    @Inject
    protected DailyOutcomeService dailyOutcomeService;

    @Inject
    protected ParentOutcomeCategoryService parentOutcomeCategoryService;

    public DailyOutcomeController() {
        beanConverter = new BeanConverter(new Converter<Integer, DailyOutcomeCategory>() {
            @Override
            public DailyOutcomeCategory convert(Integer source) {
                return new DailyOutcomeCategory(source);
            }
        }, new Converter<DailyOutcomeCategory, Integer>() {
            @Override
            public Integer convert(DailyOutcomeCategory source) {
                return source.getDailyOutcomeCategoryId();
            }
        });
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    @ModelAttribute
    public DailyOutcomeForm setUpForm(
            @RequestParam(value = "date", required = false) Date date,
            Model model) {
        DailyOutcomeForm form = new DailyOutcomeForm();
        if (date != null) {
            form.setOutcomeDate(date);
            model.addAttribute("date", new DateTime(date));
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

    @ModelAttribute("payments")
    public List<Payment> getPayments() {
        return Arrays.asList(Payment.values());
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public String createForm(DailyOutcomeForm form, Model model) {
        if (form.getOutcomeDate() == null) {
            form.setOutcomeDate(new Date());
        }
        if (form.getQuantity() == null) {
            form.setQuantity(1);
        }
        return "dailyOutcome/createForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Validated({ Default.class,
            DailyOutcomeCreateGroup.class }) DailyOutcomeForm form,
            BindingResult result, RedirectAttributes attributes) {
        logger.debug("register {}", form);
        if (result.hasErrors()) {
            return "dailyOutcome/createForm";
        }
        DailyOutcome dailyOutcome = beanConverter.populate(form,
                DailyOutcome.class);
        dailyOutcome.setIsWaste(form.isWaste()); // TODO
        dailyOutcomeService.save(dailyOutcome);
        attributes.addFlashAttribute("created", dailyOutcome.getOutcomeName());
        attributes.addAttribute("date", new DateTime(dailyOutcome
                .getOutcomeDate()).toString("yyyy-MM-dd"));
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
        beanConverter.convert(dailyOutcome, form, IgnoreOption.NOT_NULL_TARGET);
        form.setWaste(dailyOutcome.getIsWaste());
        return "dailyOutcome/updateForm";
    }

    @RequestMapping(value = "{dailyOutcomeId}", method = RequestMethod.PUT)
    public String update(
            @PathVariable("dailyOutcomeId") Integer dailyOutcomeId,
            @Validated({ Default.class, DailyOutcomeUpdateGroup.class }) DailyOutcomeForm form,
            BindingResult result, RedirectAttributes attributes) {
        logger.debug("update {}", form);
        if (result.hasErrors()) {
            return "dailyOutcome/updateForm";
        }
        DailyOutcome dailyOutcome = dailyOutcomeService.findOne(dailyOutcomeId);
        beanConverter.convert(form, dailyOutcome, IgnoreOption.NULL_SOURCE);
        dailyOutcome.setIsWaste(form.isWaste()); // TODO
        dailyOutcomeService.save(dailyOutcome);
        attributes.addFlashAttribute("updated", dailyOutcome.getOutcomeName());
        attributes.addAttribute("dailyOutcomeId", dailyOutcome
                .getDailyOutcomeId());
        return "redirect:/dailyOutcome/{dailyOutcomeId}";
    }

    @RequestMapping(value = "{dailyOutcomeId}", method = RequestMethod.DELETE)
    public String delete(
            @PathVariable("dailyOutcomeId") Integer dailyOutcomeId,
            RedirectAttributes attributes) {
        DailyOutcome dailyOutcome = dailyOutcomeService.findOne(dailyOutcomeId);
        DateTime date = new DateTime(dailyOutcome.getOutcomeDate());
        dailyOutcomeService.delete(dailyOutcomeId);
        attributes.addAttribute("year", date.getYear()).addAttribute("month",
                date.toString("MM")).addAttribute("day", date.toString("dd"));
        return "redirect:/calendar/{year}/{month}/{day}";
    }
}
