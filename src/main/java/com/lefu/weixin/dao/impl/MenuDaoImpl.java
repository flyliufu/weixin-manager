package com.lefu.weixin.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.lefu.weixin.dao.MenuDao;
import com.lefu.weixin.entity.Menu;

@Component
public class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao {

    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public List<Menu> showMenuList() {
        return getSqlSession().selectList("com.lefu.weixin.entity.Menu.findMenuList");
    }

    public void addMenu(Menu menu) {
        getSqlSession().insert("com.lefu.weixin.entity.Menu.addMenu", menu);
    }

    public void delMenu(Menu menu) {
        getSqlSession().delete("com.lefu.weixin.entity.Menu.delMenu", menu);
    }

    public Menu getMenuById(Menu menu) {
        return getSqlSession().selectOne("com.lefu.weixin.entity.Menu.getMenu", menu);
    }

    public void updateMenu(Menu menu) {
        getSqlSession().update("com.lefu.weixin.entity.Menu.updateMenu", menu);
    }

}
