package com.itvedant.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itvedant.form.dto.AddUserDto;
import com.itvedant.form.entity.User;
import com.itvedant.form.service.UserService;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("userform",new User());
	return "index";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute AddUserDto addUserDto, Model model,@RequestParam("file") MultipartFile file) {
		model.addAttribute("userform", new User());
		model.addAttribute("message", "Registered");
		this.userService.create(addUserDto, file);
		return "index";
	}
	
	


}
