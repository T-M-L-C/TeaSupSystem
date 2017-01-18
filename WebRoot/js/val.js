function number(str)
{
    var fixedNumber = /^[0][1-9]{2,4}-[0-9]{7,7}$/;
    var mobile = /^[1-9]{1}[0-9]{10}$/;
    return fixedNumber.test(str) || mobile.test(str);
}

function onlynumber(str)
{
    var on = /^[0-9]{5}$/;
    return on.test(str);
}
function isname(str)
{
    var ch = /[\u4e00-\u9fa5]+[¡¤\u4e00-\u9fa5]{0}$/;
    return ch.test(str);
}