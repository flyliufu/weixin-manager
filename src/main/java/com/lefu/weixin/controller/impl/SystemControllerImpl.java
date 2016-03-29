package com.lefu.weixin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lefu.weixin.controller.SystemController;
import com.lefu.weixin.entity.Menu;
import com.lefu.weixin.entity.Result;
import com.lefu.weixin.service.MenuService;

@SuppressWarnings("all")
@Controller(value = "systemController")
@RequestMapping(value = "/system")
public class SystemControllerImpl implements SystemController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/menu.do")
	@ResponseBody
	public List<Menu> menuJson() {
		return menuService.showMenuList();
	}

	@RequestMapping(value = "/menuPage.do")
	public ModelAndView main(ModelAndView model) {
		model.setViewName("system/menu");
		return model;
	}

	@RequestMapping(value = "/addMenu.do",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> addMenu(Menu menu) {
		menuService.addMenu(menu);
		return Result.SUCCEED;
	}
	
	
	@RequestMapping(value = "/delMenu.do",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> delMenu(Menu menu) {
		menuService.delMenu(menu);
		return Result.SUCCEED;
	}
	
	@RequestMapping(value = "/toUpdate.do",method = RequestMethod.POST)
	@ResponseBody
	public Result<Menu> toUpdate(Menu menu) {
		Result<Menu> entity = new Result<Menu>();
		Menu bean = menuService.getMenuById(menu);
		entity.setContent(bean);
		return entity;
	}
	
}
