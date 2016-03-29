<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微信公众管理系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/dijit/themes/claro/claro.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/cbtree/themes/claro/claro.css"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/dijit/themes/nihilo/nihilo.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/cbtree/themes/nihilo/nihilo.css"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/dijit/themes/soria/soria.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/cbtree/themes/soria/soria.css"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/dijit/themes/tundra/tundra.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dojo/cbtree/themes/tundra/tundra.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css"/>
    <script type="text/javascript">
        var path = "<%=request.getContextPath()%>";
        var rootName = "根菜单";
        dojoConfig = {
            async: false
        };
    </script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dojo/dojo/dojo.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
</head>

<body class="soria">
<div data-dojo-type="dijit/layout/BorderContainer" id="mainContainer" style="width: 100%; height: 100%"
     data-dojo-props="gutters:true">
    <!-- 顶部 -->
    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'" style="height: 12%;">
        <div style="float:right;">
            <label for="style_choice" style="vertical-align: middle;">系统样式：</label>
            <select id="style_choice" data-dojo-type="dijit/form/Select" style="width: 80px;height: 20px;">
                <option value="claro">claro</option>
                <option value="nihilo">nihilo</option>
                <option value="soria" selected="selected">soria</option>
                <option value="tundra">tundra</option>
            </select>
        </div>
    </div>
    <!-- 左侧栏 -->
    <div data-dojo-type="dijit/layout/AccordionContainer" style="width: 15%" id="leftAccordion"
         data-dojo-props="region:'leading',splitter:true">
        <div id="sysMenu" data-dojo-type="dijit/layout/AccordionPane"
             title="菜单栏" style="height: 100%; width: 100%;padding-top: 5px;">
        </div>
    </div>
    <!-- 正文 -->
    <div id="mainContent" data-dojo-type="dijit/layout/TabContainer"
         data-dojo-props="liveSplitters: false,region:'center'">
        <div data-dojo-type="dojox/layout/ContentPane" title="主页" data-dojo-props="closable:false"></div>
    </div>
</div>
</body>
</html>