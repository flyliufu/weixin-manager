package com.lefu.weixin.entity;

/**
 * 系统菜单
 * 
 * @author liufu
 *
 */
public class Menu implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 770775889734106790L;

	private Integer id;
	private Integer parent;
	private String url;
	private String name;
	private Integer hasPage;

	private Menu parentMenu;
	
	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHasPage() {
		return hasPage;
	}

	public void setHasPage(Integer hasPage) {
		this.hasPage = hasPage;
	}

}
