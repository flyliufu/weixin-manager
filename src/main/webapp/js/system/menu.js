/**
 * 表单操作类型
 * @type {string}
 */
var action = "";
require(["dojo/ready"], function (ready) {
    ready(function () {
        var menuForm = {};
        loadMenuTree();
        require(["dijit/form/Form"], function (Form) {
            menuForm = new Form({
                title: "菜单信息",
            }, "menu_form");
            menuForm.startup();
        })
        require(["dijit/form/Select"], function (Select) {
            var select = new Select({
                style: "width:100%;height:25px;",
                options: [{
                    "label": "有界面",
                    "value": "1"
                }, {
                    "label": "无界面",
                    "value": "0"
                }]
            }, "sel_has_page");
            dojo.connect(select, "onChange", function (value) {
                doId("ipt_has_page").value = value;
                if (!parseInt(value)) {
                    diId("menu_url").setDisabled(true)
                } else {
                    diId("menu_url").setDisabled(false)
                }
            });
        })
        require(["dijit/form/Button"], function (Button) {
            var save = new Button({
                iconClass: "dijitIconSave"
            }, "btn_menu_submit");
            var edit = new Button({
                iconClass: "dijitIconEdit"
            }, "btn_menu_update");
            var del = new Button({
                iconClass: "dijitIconDelete"
            }, "btn_menu_delete");
            var add = new Button({
                iconClass: "dijitIconNewTask"
            }, "btn_menu_add");
            var undo = new Button({
                iconClass: "dijitIconUndo",
                onClick: function () {
                    menuForm.reset();
                }
            }, "btn_menu_undo");

            dojo.connect(save, "onClick", function () {
                if (menuForm.validate())
                    if (action && "add" == action) {
                        var data = menuForm.getValues();
                        ajax("/system/addMenu.do", data, function (data) {
                            if (data.status == "success") {
                                loadMenuTree();
                                // loadMainMenu();
                                menuForm.reset();
                                action = "";
                            }
                        });
                    } else if (action && "update" == action) {
                        action = "";
                    }
            });

            dojo.connect(add, "onClick", function () {
                // 设置表单操作为添加
                action = "add";
                var node = diId("tree_sys_menu").attr("selectedItem");
                diId("menu_parent_name").setValue(node.name);
                diId("menu_parent").setValue(node.id);
            });

            dojo.connect(edit, "onClick", function () {
                var node = diId("tree_sys_menu").attr("selectedItem");
                if (node && node.id) {
                    if (node.id == 1) {
                        alert("根节点不可修改!");
                    } else {
                        // 设置表单操作为修改
                        action = "update";
                        ajax("/system/toUpdate.do", {"id": node.id}, function (data) {
                            if (data.status == "success" && data.content) {
                                // 设置表单数据
                                menuForm.setValues(data.content);
                                diId("menu_parent_name").setValue(data.content.parentMenu.name);
                            }
                        });
                    }
                } else {
                    alert("请选择一个菜单");
                }
            });
            dojo.connect(del, "onClick", function () {
                var node = diId("tree_sys_menu").attr("selectedItem");
                if (node && node.id) {
                    if (node.id == 1) {
                        alert("根节点不可删除!");
                    } else {
                        ajax("/system/delMenu.do", {
                            "id": node.id
                        }, function (data) {
                            if (data.status == "success") {
                                loadMenuTree();
                                // loadMainMenu();
                                menuForm.reset();
                            }
                        });
                    }
                } else {
                    alert("请选择一个菜单");
                }
            });
        });
    });
});

/**
 * 加载菜单树
 */
function loadMenuTree() {
    require(["dojo/store/Memory", // basic dojo/store
        // "cbtree/Tree", // Checkbox tree
        "dijit/Tree", // normal tree
        "cbtree/model/TreeStoreModel", // ObjectStoreModel
        "dojo/dom-construct"], function (Memory, Tree, TreeStoreModel,
                                         domConstruct) {
        var tree = diId("tree_sys_menu");
        if (tree) {
            // 如果树存在，清除
            tree.destroy()
        }
        ajax("/system/menu.do", {}, function (data) {
            var store = new Memory({
                data: data
            });
            var model = new TreeStoreModel({
                store: store,
                rootLabel: rootName,
                query: {
                    id: "1"
                }
            });
            // 设置一个节点作为树的容器
            var n = domConstruct.create("div");
            // 把树的容器放入侧栏中
            diId("sys_menu_container").domNode.appendChild(n);
            // 生成树对象
            tree = new Tree({
                model: model,
                id: "tree_sys_menu"
            }, n);
            tree.startup();
            tree.expandAll();
        });
    });
}
