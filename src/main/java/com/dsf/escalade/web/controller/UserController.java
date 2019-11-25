package com.dsf.escalade.web.controller;

import com.dsf.escalade.repository.global.RoleRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.service.SecurityServiceImpl;
import com.dsf.escalade.service.UserServiceImpl;
import com.dsf.escalade.validator.UserValidator;
import com.dsf.escalade.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class UserController {

   private final UserServiceImpl userService;
   private final  SecurityServiceImpl securityService;
   private final  RoleRepository roleRepository;
   private final  UserRepository userRepository;
   private final  UserValidator userValidator;

   @Autowired
   public UserController(UserServiceImpl userService, SecurityServiceImpl securityService, RoleRepository roleRepository, UserRepository userRepository, UserValidator userValidator) {
      this.userService = userService;
      this.securityService = securityService;
      this.roleRepository = roleRepository;
      this.userRepository = userRepository;
      this.userValidator = userValidator;
   }

   @GetMapping("/registration")
   public String getRegistration(Model model) {
      model.addAttribute("user", new UserDto());

      return "user/registration";
   }

   @PostMapping("/registration")
   public String postRegistration(@ModelAttribute("user") UserDto user, BindingResult bindingResult) {

      userValidator.validate(user, bindingResult);

      if (bindingResult.hasErrors()) {
         return "user/registration";
      }

      user.setRole(roleRepository.findByName("ROLE_USER"));

      userService.save(user);
      securityService.autoLogin(user.getAlias(), user.getMatchingPassword());

      return "redirect:/";
   }

   @GetMapping("/login")
   public String login(Model model, String error, String logout) {

      if (error != null)
         model.addAttribute("error", "Your username and password is invalid.");

      if (logout != null)
         model.addAttribute("message", "You have been logged out successfully.");

      return "login";
   }

   @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication!= null){
         new SecurityContextLogoutHandler().logout(request, response, authentication);
      }

      return "login";
   }

   @GetMapping({"/", "/index"})
   public String home(Model model) {
      return "index";
   }

}
