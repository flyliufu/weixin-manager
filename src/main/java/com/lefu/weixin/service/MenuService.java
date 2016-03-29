package com.lefu.weixin.service;

import java.util.List;

import com.lefu.weixin.entity.Menu;

public interface MenuService {

	/**
	 * 菜单列表
	 * @return
	 */
	List<Menu> showMenuList();
	/**
	 * 添加菜单
	 * @param menu
	 */
	void addMenu(Menu menu);
	/**
	 * 删除menu
	 * 
	 * @param menu
	 */
	void delMenu(Menu menu);

	/**
	 * 获取menu
	 * 
	 * @param menu
	 * @return
	 */
	Menu getMenuById(Menu menu);

	/**
	 * 修改menu
	 * 
	 * @param menu
	 */
	void updateMenu(Menu menu);
}
