package com.lefu.weixin.controller;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.lefu.weixin.entity.Menu;
import com.lefu.weixin.entity.Result;

public interface SystemController {
	/**
	 * JSON菜单列表
	 * 
	 * @return
	 */
	List<Menu> menuJson();

	/**
	 * 主界面
	 * 
	 * @param model
	 * @return
	 */
	ModelAndView main(ModelAndView model);

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @return
	 */
	Result<String> addMenu(Menu menu);

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 * @return
	 */
	Result<String> delMenu(Menu menu);
}
