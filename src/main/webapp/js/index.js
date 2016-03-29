dojo.require("dijit.dijit");
var tabCache = {};
var container = {};
require(["dojo/ready"], function (ready) {
    ready(function () {
        require(["dojo/parser"], function (parser) {
            parser.parse();
        });
        // tab 窗口
        container = diId("mainContent");
        loadMainMenu();
        dojo.connect(diId("style_choice"), "onChange", function (value) {
            require(["dojo/dom-class"], function (domClass) {
                domClass.remove(dojo.body());
                domClass.add(dojo.body(), value);
            });
        });
    });
});

/**
 * 刷新主菜单
 */
function loadMainMenu() {
    require(["dojo/store/Memory", // basic dojo/store
        // "cbtree/Tree", // Checkbox tree
        "dijit/Tree", // normal tree
        "cbtree/model/TreeStoreModel", // ObjectStoreModel
        "dojo/dom-construct"], function (Memory, Tree, TreeStoreModel,
                                         domConstruct) {
        var tree = diId("tree_one");
        if (tree) {
            // 如果树存在，清除
            tree.destroy()
        }
        dojo.xhrPost({
            url: path + "/system/menu.do",
            handleAs: "json",
            load: function (data) {
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
                diId("sysMenu").domNode.appendChild(n);
                // 生成树对象
                tree = new Tree({
                    showRoot: false,
                    model: model,
                    id: "tree_one"
                }, n);
                tree.startup();
                tree.expandAll();
                dojo.connect(tree, "onClick", function (item, node, evt) {
                    // 0:没有界面，1:有界面
                    if (parseInt(item.hasPage)) {
                        require(["dojox/layout/ContentPane"], function (ContentPane) {
                            var cp = tabCache["id_" + item.id];
                            if (!cp) {
                                cp = new ContentPane({
                                    id: item.id,
                                    title: item.name,
                                    closable: true,
                                    href: item.url
                                });
                                cp.startup();
                                dojo.connect(cp, "onClose", function () {
                                    delete tabCache["id_" + item.id];
                                });
                                container.addChild(cp);
                                tabCache["id_" + item.id] = cp;
                            }
                            container.selectChild(cp);
                        });
                    }
                });
            }
        });
    });
}