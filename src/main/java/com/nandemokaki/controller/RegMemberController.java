package com.nandemokaki.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nandemokaki.code.EmailChk;

@Controller
public class RegMemberController {

	@RequestMapping("/regMember")
	public String main(Model model) {
		model.addAttribute("ww", "World");
		return "regMember";
	}

	@RequestMapping(value = "/regMember/chkEmail", method = RequestMethod.GET )
	@ResponseBody
	public int chkEmail(@RequestParam("eMail") String eMail, HttpServletResponse response) {

		return EmailChk.ok.ordinal();
	}
}
