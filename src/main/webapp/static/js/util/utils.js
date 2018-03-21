/**
 * Created by xlch on 2016/4/14.
 */

var utils = {};
utils.quick = {
    click: function (method, node) {
        var list = null;
        if (node) {
            list = $(node).find("[data-click]");
        } else {
            list = $("[data-click]");
        }
        list.on("click", function (eventObject) {
            var click = $(this).data("click");
            if (click && method[click]) {        //获取附加click元素
                return method[click].apply(this, [eventObject]);
            }
        });
    }
}
utils.uuid= {
    uuid:function(){
        var d = new Date().getTime();
        var uuid = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,function(c){
            var r = (d+Math.random()*16)%16 |0;    // Math.random()*16 随机产生0-16之间的小数 % 模运算即取余
            d = Math.floor(d/16);                 //round()四舍五入;floor()向上取整;ceil()向下取整；
            return (c = 'x'?r:(r &0x7 |0x8)).toString(16);
        });
        return uuid;
    }
};
utils.modal = {
    /**
     * myAlert
     * @param title 标题 不填则默认
     * @param body 内容
     * @param callback 回调函数
     */
    myAlert: function () {
        var settings = resolveModal.apply({}, arguments);
        myAlert.open(settings);
    },
    /**
     * myConfirm
     * @param title 标题
     * @param body 内容
     * @param callback 回调函数
     */
    myConfirm: function () {
        var settings = resolveModal.apply({}, arguments);
        myConfirm.open(settings);
    }
};
var resolveModal = function () {//解析调用时的参数，并返回
    var settings = null;
    if (arguments.length == 1) {//仅一个参数
        if ($.type(arguments[0]) == 'string') {
            settings = {
                body: arguments[0]
            }
        } else if ($.type(arguments[0]) == 'object') {
            settings = arguments[0];
        } else if ($.type(arguments[0]) == 'function') {
            settings = {
                callback: arguments[0]
            }
        }
    } else if (arguments.length == 2) {//两个参数
        if ($.type(arguments[1]) == 'function') {
            settings = {
                body: arguments[0],
                callback: arguments[1]
            }
        } else {
            settings = {
                title: arguments[0],
                body: arguments[1]
            }
        }
    } else if (arguments.length == 3) {//三个参数
        settings = {
            title: arguments[0],
            body: arguments[1],
            callback: arguments[2]
        }
    }
    return settings;
};
var myAlert = {
    id: null,
    template: '<div class="modal fade" id="{id}" role="dialog" aria-hidden="true" style="background-color: inherit"><div class="modal-dialog" style="width: 360px;"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h4 class="modal-title"></h4></div><div class="modal-body" style="font-size:16px;"></div><div class="modal-footer"><button type="button" class="btn btn-primary">确定</button></div></div></div></div>',
    settings: {
        title: '消息提示',
        body: '消息提示'
    },
    init: function () {
        $('#' + this.id).on('hide.bs.modal', function () {
            myAlert.callback();
        });
        this.init = null;
    },
    callback: $.noop,
    close: function () {
        $('#' + myAlert.id).modal('hide');
    },
    open: function (options) {
        if (!this.id) {
            this.id = utils.uuid.uuid();
            $('body').append(utils.template.nano(this.template, {id: this.id}));
            $('#' + this.id + ' button.btn-primary').on('click', myAlert.close);
        }
        var dom = $('#' + this.id), setting = $.extend({}, this.settings, options);
        this.callback = setting.callback ? setting.callback : $.noop;
        $('h4.modal-title', dom).text(setting.title);
        $('div.modal-body', dom).html(setting.body);
        document.activeElement && $(document.activeElement).blur();
        dom.modal({backdrop: 'static', show: true});
        this.init && this.init();
    }
}
var myConfirm = {
    id: null,
    template: '<div class="modal fade" id="{id}" role="dialog" aria-hidden="true"><div class="modal-dialog" style="width: 360px;"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button><h4 class="modal-title"></h4></div><div class="modal-body" style="font-size:16px;"></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">取消</button><button type="button" class="btn btn-primary">确定</button></div></div></div></div>',
    settings: {
        title: '消息提示',
        body: '确定继续'
    },
    callback: $.noop,
    result: false,
    init: function () {
        $('#' + this.id).on('hide.bs.modal', function () {
            myConfirm.callback(myConfirm.result);
        });
        this.init = null;
    },
    close: function () {
        $('#' + myConfirm.id).modal('hide');
    },
    open: function (options) {
        if (!this.id) {
            this.id = utils.uuid.uuid();
            $('body').append(utils.template.nano(this.template, {id: this.id}));
            $('#' + this.id + ' button.btn-primary').on('click', function () {
                myConfirm.result = true;
                myConfirm.close();
            });
        }
        var dom = $('#' + this.id), setting = $.extend({}, this.settings, options);
        $('h4.modal-title', dom).text(setting.title);
        $('div.modal-body', dom).html(setting.body);
        this.callback = setting.callback ? setting.callback : $.noop;
        this.result = false;
        document.activeElement && $(document.activeElement).blur();
        dom.modal({backdrop: 'static', show: true});
        this.init && this.init();
    }
}
utils.template = {
    // JS模板工具 https://github.com/trix/nano
    /**
     * @param template  静态的模板，模型使用{object.name}的形式
     * @param data      填充的数据对象
     * @returns {XML|string|void}
     */
    nano: function (template, data) {
        return template.replace(/\{([\w\.]*)\}/g, function (str, key) {
            var keys = key.split("."), v = data[keys.shift()];
            for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
            return (typeof v !== "undefined" && v !== null) ? v : "";
        });
    }
};
utils.myAjax ={
    post: function () {
        var list = resolveAjax(arguments);
        var settings = {
            url:  list[0],
            dataType: 'json',
            type: 'POST',
            data: list[1],
            cache: false
        };
        $.ajax(settings).done(list[2]).fail(function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 200 && XMLHttpRequest.responseText.indexOf('<title>CAS – Central Authentication Service</title>') > 0) {
                utils.modal.alert('登录超时', function () {
                    location.reload();
                });
            } else {
                list[3](XMLHttpRequest, textStatus, errorThrown)
            }
        });
    },
    get: function () {
        var list = resolveAjax(arguments);
        var settings = {
            url: list[0],
            dataType: 'json',
            type: 'GET',
            data: list[1],
            cache: false
        };
        $.ajax(settings).done(list[2]).fail(function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 401) {
                location.reload();
            }
            else {
                list[3](XMLHttpRequest, textStatus, errorThrown)
            }
        });
    }
};
var resolveAjax = function (arg) {
    if (!arg.length) {
        throw 'URL不能为空'
    }
    var result = [];
    for (var i = 0; i < arg.length; i++) {
        result.push(arg[i]);
    }
    if ($.type(result[1]) == 'function') {
        result.splice(1, 0, '');
    }
    if (!result[3]) {
        result.push($.noop);
    }
    return result;
};

utils.uuid= {
    uuid:function(){
        var d = new Date().getTime();
        var uuid = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,function(c){
            var r = (d+Math.random()*16)%16 |0;    // Math.random()*16 随机产生0-16之间的小数 % 模运算即取余
            d = Math.floor(d/16);                 //round()四舍五入;floor()向上取整;ceil()向下取整；
            return (c = 'x'?r:(r &0x7 |0x8)).toString(16);
        });
        return uuid;
    }
};

// $.extend(true, $.fn.dataTable.defaults, {
//     dom: "t<'row-fluid'<'span12'p>>",
//     pageLength: 20,
//     language: {
//         // "sZeroRecords": "没有相关数据",
//         sEmptyTable: "没有相关数据",
//         sInfo: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
//         sInfoEmpty: "没有数据",
//         sInfoFiltered: "(从 _MAX_ 条数据中检索)",
//         sZeroRecords: "没有检索到数据",
//         sLengthMenu: "_MENU_ 条/页",
//         sSearch: "过滤：",
//         oPaginate: {
//             "sFirst": "首页",
//             "sLast": "末页",
//             "sNext": "后页",
//             "sPrevious": "前页"
//         }
//     }
// });
