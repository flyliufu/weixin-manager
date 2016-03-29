package com.lefu.weixin.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lefu.weixin.controller.IndexController;

@Controller(value = "indexController")
@RequestMapping(value = "/index")
public class IndexControllerImpl implements IndexController {

	@RequestMapping(value = "/main.do")
	public ModelAndView main(ModelAndView model) {
		model.setViewName("index");
		return model;
	}
}
