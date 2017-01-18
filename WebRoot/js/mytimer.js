var autoajax = function(inputobj, funcajax) {
    var timeout = new mytimeout();
    timeout.ini(1000, function() {
        funcajax();
    });
    inputobj.on('keyup', function(e) {
        var keycode = e.which || event.keyCode;
        if (keycode == 13)
            timeout.finish();
        else
            timeout.again();
    });
};

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

function t(a, b, c) {

    this.pname = a;
    this.v = b;
    this.compare = c;
};
var arr = new Array();
arr.push(new t('sub_no', '0015', '1'));
arr.push(new t('a', 1));

$.ajax({
    url: 'Search?action=agreeSearch',
    type: 'post',
    data:

    {
        'sc': JSON.stringify(arr),
        'page': 1,
        'count': 10,
        'subno': '0015'
    },
    success: function(res) {

    }
});