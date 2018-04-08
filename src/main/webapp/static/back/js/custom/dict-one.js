$(function () {
    if ($.validator) {
        $.validator.prototype.elements = function () {
            var validator = this,
                rulesCache = {};

            // select all valid inputs inside the form (no submit or reset buttons)
            return $(this.currentForm)
                .find("input, select, textarea")
                .not(":submit, :reset, :image, [disabled]")
                .not(this.settings.ignore)
                .filter(function () {
                    if (!this.name && validator.settings.debug && window.console) {
                        console.error("%o has no name assigned", this);
                    }
                    //注释这行代码
                    // select only the first element for each name, and only those with rules specified
                    //if ( this.name in rulesCache || !validator.objectLength($(this).rules()) ) {
                    //    return false;
                    //}
                    rulesCache[this.name] = true;
                    return true;
                });
        }
    };
    $("#dict-form").validate({
        rules:{
            name:{
                required:true
            }
        },messages:{
            name:{
                required:"字典名称不能为空"
            }
        }
    });

    /**
     * 事件处理
     */
    utils.quick.click({
        new:function () {
            $("#dict-table tbody tr.center").remove();
            var html = "<tr><td><input type='text' name='name'></td>" +
                "<td><a class='delete-action'><i class='fa fa-x fa-trash-o'></i></a></td></tr>";
            $("#dict-table tbody").append(html);
        },
        save:function () {
            var size = $("#dict-table tbody tr.center").length;
            if (size === 1) {
                utils.modal.myAlert("至少包含一个字典数据");
                return;
            }
            var pass = $("#dict-form").valid();
            if (!pass) {
                return;
            }
            utils.modal.myConfirm("提示", "确认修改字典表数据吗？", function (sure) {
                if (sure) {
                    $("#dict-form").submit();
                } else {
                    return;
                }
            });
        }
    });
    /**
     * 行删除
     */
    $("#dict-table").on("click", ".delete-action", function () {
        $(this).parents("tr").remove();
        var size = $("#dict-table tbody tr").length;
        if (size < 1) {
            var html = "<tr class='center'><td colspan='2'>暂无字典数据</td></tr>";
            $("#dict-table tbody").append(html);
        }
    });
});