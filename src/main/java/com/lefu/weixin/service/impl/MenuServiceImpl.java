package com.lefu.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lefu.weixin.dao.MenuDao;
import com.lefu.weixin.entity.Menu;
import com.lefu.weixin.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao menuDao;
	
	public List<Menu> showMenuList() {
		return menuDao.showMenuList();
	}

	public void addMenu(Menu menu) {
		menuDao.addMenu(menu);
	}

	public void delMenu(Menu menu) {
		menuDao.delMenu(menu);
	}

	public Menu getMenuById(Menu menu) {
		return menuDao.getMenuById(menu);
	}

	public void updateMenu(Menu menu) {
		menuDao.updateMenu(menu);
	}

}
