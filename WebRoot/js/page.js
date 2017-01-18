/*
                count 总页数，n单页显示几条，url:ajax路径，data：ajax数据
            */
var page = function(jqueryobj, count, num, url, datajson, add) {
    var pageno = 1;
    var pagecount = count;
    var pagenum = num;
    var div = jqueryobj.find('ul');
    var funcdata = datajson;

    var dom = new function() {
        var t = $('<li class="paginate_button " aria-controls="example-2" tabindex="0"><a href="#"></a></li>');
        this.active = function(o) {
            if (o != null) {
                if (o.hasClass('disabled'))
                    o.removeClass('disabled');
                return o.addClass('active');
            } else {
                return t.clone().addClass('active');
            }
        };
        this.disabled = function(o) {
            if (o != null) {
                if (o.hasClass('active'))
                    o.removeClass('active');
                return o.addClass('disabled');
            } else {
                return t.clone().addClass('disabled');
            }
        };
        this.ini = function(n, du) {
            //省略号生成
            if (n == '...') {
                var cpyt = t.clone();
                cpyt.find('a').html(n);
                return this.disabled(cpyt);
            }
            //普通页码生成
            else if (n != null) {
                var cpyt = t.clone().on('click', function(n) {
                    var theEvent = window.event || arguments.callee.caller.arguments[0];
                    var theObj = theEvent.target || theEvent.srcElement;
                    var v = $(theObj).find('a').html() || $(theObj).html();
                    if (v <= pagecount && v >= 0) {
                        pageno = parseInt(v);
                        update();
                    }
                });
                if (pageno == n)
                    cpyt = this.active(cpyt);
                cpyt.find('a').html(n);
                return cpyt;
            }
            //n=null
            else {
                //上一页标签生成
                if (du == 1) {
                    var cpyt = t.clone().on('click', function() {
                        if (pageno > 1) {
                            pageno--;
                            update();
                        }
                    });
                    cpyt.find('a').html('上一页');
                    if (pageno == 1)
                        cpyt = this.disabled(cpyt);
                    return cpyt;
                }
                //下一页标签生成
                else {
                    var cpyt = t.clone().on('click', function() {
                        if (pageno < pagecount) {
                            pageno++;
                            update();
                        }
                    });
                    cpyt.find('a').html('下一页');
                    if (pageno == pagecount)
                        cpyt = this.disabled(cpyt);
                    return cpyt;
                }
            }
        };
        return this;
    };

    //生成页码
    var show = function() {
        div.html('');
        div.append(dom.ini(null, 1));
        div.append(dom.ini(1));

        //1 ... 5 6 7 ... 10
        if (pagecount > 7) {
            //当前页大于3
            if (pageno > 4) {
                //前省略号
                div.append(dom.ini('...'));

                //所剩页是否大于3
                if (pagecount - pageno > 3) {
                    div.append(dom.ini(pageno - 1));
                    div.append(dom.ini(pageno));
                    div.append(dom.ini(pageno + 1));
                    div.append(dom.ini('...'));
                    div.append(dom.ini(pagecount));
                } else {
                    for (var i = pagecount - 4; i <= pagecount; i++) {
                        div.append(dom.ini(i));
                    }
                }
            } else {
                div.append(dom.ini(2));
                div.append(dom.ini(3));
                div.append(dom.ini(4));
                div.append(dom.ini(5));
                div.append(dom.ini('...'));
                div.append(dom.ini(pagecount));
            }
        }
        ///小于7，页码全出
        else {
            for (var i = 2; i <= pagecount; i++) {
                div.append(dom.ini(i));
            }
        }

        div.append(dom.ini(null, 2));
    };

    //数据更新
    var update = function() {
        show();
        $.ajax({
            url: url,
            data: new funcdata(pageno),
            method: 'POST',
            success: function(res) {
                add(res);
            }
        });
    };

    update();
};