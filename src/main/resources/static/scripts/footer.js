//获取url上的参数
function getQueryString() {
    var queryString = "";
    $("input[name^='like_']").each(function(index,element){
        //console.log("----"+index+"----"+element+"----"+this.value+"----"+this.name);
        queryString += "&"+this.name+"="+this.value;
    });
    return queryString;
 }

$(document).ready(function(){
　　$("li a").click(function(){
        var queryString = getQueryString();
        var handleHref = $(this).attr("href")+queryString;
        // $("#aaaa").attr("href",handleHref);
        window.location.href = handleHref;
        return false;
　　});
});