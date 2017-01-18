var autoajax = function(inputid, funcajax) {
    var timeout = new mytimeout();
    timeout.ini(1500, function() {
        funcajax();
    });
    var eventtype;
    if ($('#' + inputid).is('input'))
        eventtype = 'keyup';
    else
        eventtype = 'change';
    $('#' + inputid).on(eventtype, function(e) {
        var keycode = e.which || event.keyCode;
        if (keycode == 13)
            timeout.finish();
        else
            timeout.again();
    });
};

/*
    延时时长，到时触发方法
    new mytimeout().ini()
*/
var mytimeout = function() {
    ///计时时间
    var ini_time = 0;
    ///倒计时时间
    var m_time = 0;
    var settimeoutid;
    ///到时执行
    var m_callback;
    this.ini = function(timesum, callback) {
        ini_time = timesum;
        m_time = ini_time;
        m_callback = callback;
    };
    ///重新计时
    this.again = function() {
        m_time = ini_time;
        try {
            clearTimeout(settimeoutid);
        } catch (e) {} finally {
            pulse();
        }
    };
    ///脉搏，100ms执行一次
    var pulse = function() {
        if (m_time > 0) {
            m_time -= 100;
            settimeoutid = setTimeout(pulse, 100);
        } else {
            m_callback();
        }
    };
    ///直接结束
    this.finish = function() {
        try {
            clearTimeout(settimeoutid);
        } catch (e) {} finally {
            m_time = 0;
            m_callback();
        }
    };
};

/*
    jqueryobj 页码父ul对象,n单页显示几条，url:ajax路径，datajson数据生成方法：ajax数据需要有一个参数n传递页码
*/
var page = function(jqueryobj, num, url, datajson, add) {
    //当前页码
    var pageno = 1;
    //总页数
    var pagecount = 1;
    //单页条目数
    var pagenum = num;

    var div = jqueryobj.find('ul');

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
                })
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
        $.ajax({
            url: url,
            data: {
                'sc': JSON.stringify(datajson()),
                'page': pageno,
                'count': pagenum
            },
            method: 'POST',
            success: function(res) {
                res = JSON.parse(res);
                pagecount = Math.ceil(res.sum / pagenum);
                add(res.data);
                show();
            }
        });
    };

    update();
    return update;
};

var getEvent = function(){
	var theEvent = window.event || arguments.callee.caller.arguments[0];
    return theEvent.target || theEvent.srcElement;
}