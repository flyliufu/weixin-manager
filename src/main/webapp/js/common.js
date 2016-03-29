/**
 * AJAX封装方法
 * 
 * @param url
 *            路径
 * @param data
 *            参数
 * @param succeed
 *            成功回调
 * @param failed
 *            失败回调
 */
function ajax(url, data, succeed, failed) {
	dojo.xhrPost({
		"url" : path + url,
		content : data || {},
		handleAs : "json",
		preventCache : true,
		error : function(error) {
			if (failed)
				failed(error);
		},
		load : function(data) {
			if (succeed)
				succeed(data);
		}
	});
}
/**
 * dijit.byId
 * 
 * @param id
 * @returns
 */
function diId(id) {
	return dijit.byId(id);
}
/**
 * dojo.byId
 * 
 * @param id
 * @returns
 */
function doId(id) {
	return dojo.byId(id);
}