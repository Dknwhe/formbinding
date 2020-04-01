package se.ecutb.cai.form_binding.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.ecutb.cai.form_binding.data.AppUserDao;
import se.ecutb.cai.form_binding.dto.AppUserFomDto;
import se.ecutb.cai.form_binding.entity.AppUser;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AppUserController {

    private AppUserDao appUserDao;

    @Autowired
    public AppUserController(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @GetMapping("users/create")

    public ModelAndView getForm() {
        AppUserFomDto formDto = new AppUserFomDto();
        ModelAndView model = new ModelAndView();
        model.addObject("form", formDto);
        model.setViewName("create-user");
        //model.addAttribute("form", formDto);
        return model;

    }


    @PostMapping("users/process")

    public String processForm(@Valid @ModelAttribute("form") AppUserFomDto formDto, BindingResult bindingResult) {
        if (appUserDao.findByEmail(formDto.getEmail()).isPresent()) {
            FieldError emailError = new FieldError("form", "email", "Email " + formDto.getEmail() + " is already defined");
            bindingResult.addError(emailError);

        }

        if (!formDto.getPassword().equals(formDto.getPasswordConfirm())) {
            FieldError confirmError = new FieldError("form", "passwordConfirm", "Your password confirmation didn't match the password");
            bindingResult.addError(confirmError);

        }

        if (bindingResult.hasErrors()) {
            return "create-user";
        }

        AppUser newAppUser = new AppUser(formDto.getEmail(), formDto.getFirstName(), formDto.getLastName(), formDto.getPassword());
        newAppUser = appUserDao.save(newAppUser);
        return "redirect:/users?type=id&value=" + newAppUser.getUserId();
    }


    @GetMapping("users")
    public String find(@RequestParam("type") String type, @RequestParam("value") String value, Model model) {
        Optional<AppUser> optionalAppUser = Optional.empty();
        switch (type) {
            case "id":
                optionalAppUser = appUserDao.findById(value);
                break;
            case "email":
                optionalAppUser = appUserDao.findAll().stream()
                        .filter(user -> user.getEmail().equalsIgnoreCase(value))
                        .findFirst();

                break;

            default:

                throw new IllegalArgumentException("Type " + type + "is unknown");

        }


        if (optionalAppUser.isPresent()) {

            model.addAttribute("user", optionalAppUser.get());

        } else {
            model.addAttribute("message", "User could not be found");
        }
        return "user-view";

    }


}
