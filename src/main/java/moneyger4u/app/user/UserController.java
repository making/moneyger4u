package moneyger4u.app.user;

import com.google.common.collect.Maps;
import moneyger4u.app.user.UserForm.UserCreateGroup;
import moneyger4u.app.user.UserForm.UserDeleteGroup;
import moneyger4u.app.user.UserForm.UserUpdateGroup;
import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.User;
import moneyger4u.domain.service.user.FamilyService;
import moneyger4u.domain.service.user.UserService;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Inject
    UserService userService;

    @Inject
    FamilyService familyService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public UserForm setUpUserForm() {
        return new UserForm();
    }

    @ModelAttribute("familyMap")
    public Map<Integer, String> getFamilies() {
        List<Family> families = familyService.findAll();
        Map<Integer, String> map = Maps.newLinkedHashMap();
        for (Family f : families) {
            map.put(f.getFamilyId(), f.getFamilyName());
        }
        return map;
    }

    @RequestMapping(value = "create", params = "form")
    public String createForm(UserForm form) {
        return "user/createForm";
    }

    @RequestMapping(value = "create", params = "confirm", method = RequestMethod.POST)
    public String createConfirm(@Validated({Default.class,
            UserCreateGroup.class}) UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "user/createForm";
        }
        return "user/createConfirm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(
            @Validated({Default.class, UserCreateGroup.class}) UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "user/createForm";
        }

        User user = beanMapper.map(form, User.class);
//        User user = beanMapper.populate(form, User.class,
//                IgnoreOption.NULL_SOURCE);
        userService.save(user, form.getPassword());

        return "redirect:/user/create?complete";
    }

    @RequestMapping(value = "create", params = "complete")
    public String createComplete() {
        return "user/createComplete";
    }

    @RequestMapping(value = "update", params = "form")
    public String updateForm(@RequestParam("userId") Integer userId,
                             UserForm form, Model model) {

        User user = userService.findOne(userId);
        beanMapper.map(user, form, "user-exclude-password");
//        beanConverter.convert(user, form, IgnoreOption.NOT_NULL_TARGET,
//                new String[]{"password"});
        model.addAttribute(user);

        return "user/updateForm";
    }

    @RequestMapping(value = "update", params = "confirm", method = RequestMethod.POST)
    public String updateConfirm(@Validated({Default.class,
            UserUpdateGroup.class}) UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "user/updateForm";
        }
        return "user/updateConfirm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
            @Validated({Default.class, UserUpdateGroup.class}) UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "user/updateForm";
        }

        User user = userService.findOne(form.getUserId());
        beanMapper.map(form, user);
        // beanConverter.convert(form, user, IgnoreOption.NULL_SOURCE);
        userService.save(user, form.getPassword());

        return "redirect:/user/update?complete";
    }

    @RequestMapping(value = "update", params = "complete")
    public String updateComplete() {
        return "user/updateComplete";
    }

    @RequestMapping(value = "delete", params = "confirm")
    public String deleteConfirm(@RequestParam("userId") Integer userId,
                                UserForm form, Model model) {

        User user = userService.findOne(userId);
        beanMapper.map(user, form);
        // beanConverter.convert(form, user, IgnoreOption.NOT_NULL_TARGET);

        model.addAttribute(user);
        return "user/deleteConfirm";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(
            @Validated({Default.class, UserDeleteGroup.class}) UserForm form,
            BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("errorMessage", "Illegal Access!");
            return "redirect:/user/list";
        }

        User user = userService.findOne(form.getUserId());
        beanMapper.map(form, user);

        userService.delete(user);
        return "redirect:/user/delete?complete";
    }

    @RequestMapping(value = "delete", params = "complete")
    public String deleteComplete() {
        return "user/deleteComplete";
    }

    @RequestMapping(value = {"create", "update", "delete"}, params = "redirectToList")
    public String redirectToList() {
        return "redirect:/user/list";
    }
}
