<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微信菜单</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/system/menu.js"></script>
</head>
<body>
<div data-dojo-type="dijit/layout/BorderContainer" id="menuContainer"
     style="width: 100%; height: 100%" data-dojo-props="gutters:true">
    <!-- 操作框 -->
    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'">
        <div id="btn_menu_add">添加</div>
        <div id="btn_menu_update">修改</div>
        <div id="btn_menu_delete">删除</div>
    </div>
    <!-- 左侧栏 -->
    <div id="menu_left" data-dojo-type="dijit/layout/AccordionContainer" style="width: 25%"
         data-dojo-props="region:'leading',splitter:true">
        <div id="sys_menu_container" data-dojo-type="dijit/layout/AccordionPane"
             title="用户菜单" style="height: 100%; width: 100%;padding-top: 5px;">
        </div>
    </div>
    <!-- 正文 -->
    <div id="menu_right" data-dojo-type="dijit/layout/ContentPane"
         data-dojo-props="liveSplitters: false,region:'center'">
        <div id="menu_form" title="菜单栏" style="padding:10px">
            <table>
                <tr>
                    <td><label for="menu_name">菜单名称：</label></td>
                    <td>
                        <input name="name" id="menu_name" required="true"
                               data-dojo-type="dijit/form/ValidationTextBox"/>
                        <input name="id" id="menu_id" data-dojo-type="dijit/form/TextBox" style="display: none;"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="menu_parent">父级菜单：</label></td>
                    <td>
                        <input name="parent" value="1" id="menu_parent" style="display: none;"
                               data-dojo-type="dijit/form/TextBox"/>
                        <input readonly="readonly" name="parent_name" id="menu_parent_name"
                               required="true" data-dojo-type="dijit/form/ValidationTextBox"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="ipt_has_page">是否有子界面：</label></td>
                    <td>
                        <input name="hasPage" id="ipt_has_page" style="display: none;" value="1"
                               data-dojo-type="dijit/form/TextBox"/>
                        <div id="sel_has_page" style="width:100%;height:25px"></div>
                    </td>
                </tr>
                <tr>
                    <td><label for="menu_url">操作路径：</label></td>
                    <td><input name="url" id="menu_url" data-dojo-type="dijit/form/ValidationTextBox"/></td>
                </tr>
                <tr>
                    <td>
                        <div id="btn_menu_submit"><label>提交</label></div>
                    </td>
                    <td>
                        <div id="btn_menu_undo"><label>重置</label></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<dir id="dialog"></dir>
</body>
</html>