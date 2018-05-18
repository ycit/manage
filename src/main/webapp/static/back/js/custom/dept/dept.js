
/**
 * 部门管理初始化
 */
var Dept = {
    id: "dept-table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dept.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', align: 'center', valign: 'middle',width:'50px'},
        {title: '部门全称', field: 'fullName', align: 'center', valign: 'middle', sortable: true},
        {title: '部门简称', field: 'nickname', align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'level', align: 'center', valign: 'middle', sortable: true}
        ];
};

$(function () {
    var defaultColumns = Dept.initColumn();
    var table = new BSTreeTable(Dept.id, "/back/dept/list", defaultColumns);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pId");
    table.setExpandAll(true);
    table.init();
    Dept.table = table;

    utils.quick.click({
        edit:function () {
            var jRadio = $("input[name=select_item]:checked");
            var id = jRadio.val();
            if (jRadio.length < 1) {
                utils.modal.myAlert("请选择需要操作的部门");
                return;
            }
            window.location.href = "/back/dept/edit/home?id=" + id;

        },
        delete:function () {
            var jRadio = $("input[name=select_item]:checked");
            var id = jRadio.val();
            if (jRadio.length < 1) {
                utils.modal.myAlert("请选择需要操作的部门");
                return;
            }
            utils.modal.myConfirm("该操作会删除该部门及子部门,确认删除吗？", function (sure) {
                if (sure) {
                    utils.myAjax.get("/back/dept/delete", {id:id}, function (data) {
                        if (data.code != 200) {
                            utils.modal.myAlert(data.message);
                        }else {
                            Dept.table.refresh();
                        }
                    })
                }
            })
        }
    })
});