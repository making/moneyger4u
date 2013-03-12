package am.ik.moneyger4u.app.template_user.controller;

import javax.inject.Inject;
import javax.validation.groups.Default;

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

import am.ik.moneyger4u.app.template_user.model.TemplateUserForm;
import am.ik.moneyger4u.app.template_user.model.TemplateUserForm.UserCreateGroup;
import am.ik.moneyger4u.app.template_user.model.TemplateUserForm.UserDeleteGroup;
import am.ik.moneyger4u.app.template_user.model.TemplateUserForm.UserUpdateGroup;
import am.ik.moneyger4u.domain.model.TemplateUser;
import am.ik.moneyger4u.domain.service.template_user.UserService;

@Controller
@RequestMapping("user")
public class TemplateUserController {
    @Inject
    protected UserService userService;

    @ModelAttribute
    public TemplateUserForm setUpUserForm() {
        return new TemplateUserForm();
    }

    @RequestMapping(value = "create", params = "form")
    public String createForm(TemplateUserForm form) {
        return "user/createForm";
    }

    @RequestMapping(value = "create", params = "confirm", method = RequestMethod.POST)
    public String createConfirm(@Validated({ Default.class,
            UserCreateGroup.class }) TemplateUserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "user/createForm";
        }
        return "user/createConfirm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(
            @Validated({ Default.class, UserCreateGroup.class }) TemplateUserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "user/createForm";
        }

        TemplateUser user = new TemplateUser();
        BeanUtils.copyProperties(form, user);
        userService.save(user, form.getPassword());

        return "redirect:/user/create?complete";
    }

    @RequestMapping(value = "create", params = "complete")
    public String createComplete() {
        return "user/createComplete";
    }

    @RequestMapping(value = "update", params = "form")
    public String updateForm(@RequestParam("id") Integer id, TemplateUserForm form,
            Model model) {

        if (form.getVersion() == null) {
            TemplateUser user = userService.findOne(id);
            BeanUtils.copyProperties(user, form, new String[] { "password" });
            model.addAttribute(user);
        }

        return "user/updateForm";
    }

    @RequestMapping(value = "update", params = "confirm", method = RequestMethod.POST)
    public String updateConfirm(@Validated({ Default.class,
            UserUpdateGroup.class }) TemplateUserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "user/updateForm";
        }
        return "user/updateConfirm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
            @Validated({ Default.class, UserUpdateGroup.class }) TemplateUserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "user/updateForm";
        }

        TemplateUser user = userService.findOne(form.getId());
        BeanUtils.copyProperties(form, user);
        userService.save(user, form.getPassword());

        return "redirect:/user/update?complete";
    }

    @RequestMapping(value = "update", params = "complete")
    public String updateComplete() {
        return "user/updateComplete";
    }

    @RequestMapping(value = "delete", params = "confirm")
    public String deleteConfirm(@RequestParam("id") Integer id, TemplateUserForm form,
            Model model) {

        TemplateUser user = userService.findOne(id);
        BeanUtils.copyProperties(user, form);

        model.addAttribute(user);
        return "user/deleteConfirm";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(
            @Validated({ Default.class, UserDeleteGroup.class }) TemplateUserForm form,
            BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("errorMessage", "Illegal Access!");
            return "redirect:/user/list";
        }

        TemplateUser user = userService.findOne(form.getId());
        BeanUtils.copyProperties(form, user);

        userService.delete(user);
        return "redirect:/user/delete?complete";
    }

    @RequestMapping(value = "delete", params = "complete")
    public String deleteComplete() {
        return "user/deleteComplete";
    }

    @RequestMapping(value = { "cretate", "update", "delete" }, params = "redirectToList")
    public String redirectToList() {
        return "redirect:/user/list";
    }
}
