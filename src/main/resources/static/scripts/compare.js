$(document).ready(function () {
    var testArray = ["project", "sampleperiod", "standard", "year", "testmodule", "direction", "workingcondition", "testtype", "datatype", "project", "sampleperiod", "standard", "year", "testmodule", "direction", "workingcondition", "testtype", "datatype"];
    var testHtmlArray = ["testFileProject", "testFileSampleperiod", "testFileStandard", "testFileYear", "testFileTestmodule", "testFileDirection", "testFileWorkingcondition", "testFileTesttype", "testFileDatatype", "testFileProjectRight", "testFileSampleperiodRight", "testFileStandardRight", "testFileYearRight", "testFileTestmoduleRight", "testFileDirectionRight", "testFileWorkingconditionRight", "testFileTesttypeRight", "testFileDatatypeRight"];
    for (var i = 0; i < testArray.length; i++) {
        var arg = testArray[i];
        $.ajax({
            url: "/getTestSelectData/" + arg,
            //访问后台去数据库查询select的选项
            type: "post",
            async: false,
            success: function (testFileList) {
                for (var m = 0; m < testFileList.length; m++) {
                    document.getElementById(testHtmlArray[i]).options.add(new Option(testFileList[m], testFileList[m]));
                }
            },
            error: function () {
                alert('Error');
            }
        });

    }

    var selArray = ["standard", "year", "testmodule", "level", "datatype", "standard", "year", "testmodule", "level", "datatype"];
    var selHtmlArray = ["standardFileStandard", "standardFileYear", "standardFileTestmodule", "standardFileLevel", "standardFileDatatype", "standardFileStandardRight", "standardFileYearRight", "standardFileTestmoduleRight", "standardFileLevelRight", "standardFileDatatypeRight"];
    for (var i = 0; i < selArray.length; i++) {
        var arg = selArray[i];
        $.ajax({
            url: "/getStandardSelectData/" + arg,
            //访问后台去数据库查询select的选项
            type: "post",
            async: false,
            success: function (standardFileList) {
                for (var m = 0; m < standardFileList.length; m++) {
                    document.getElementById(selHtmlArray[i]).options.add(new Option(standardFileList[m], standardFileList[m]));
                }
            },
            error: function () {
                alert('Error');
            }
        });

    }

    showDiv();

    showDivRight();

    $("input[name=optionsRadios]").change(function () {
        showDiv();
    });

    $("input[name=optionsRadiosRight]").change(function () {
        showDivRight();
    });

});

function showDiv() {
    switch ($("input[name=optionsRadios]:checked").attr("id")) {
        case "optionsRadios1":
            $("#standardFileSelect").hide();
            $("#testFileSelect").show();
            break;
        case "optionsRadios2":
            $("#testFileSelect").hide();
            $("#standardFileSelect").show();
            break;
        default:
            break;
    };
};

function showDivRight() {
    switch ($("input[name=optionsRadiosRight]:checked").attr("id")) {
        case "optionsRadios3":
            $("#standardFileSelectRight").hide();
            $("#testFileSelectRight").show();
            break;
        case "optionsRadios4":
            $("#testFileSelectRight").hide();
            $("#standardFileSelectRight").show();
            break;
        default:
            break;
    };
};

function btn1() {
    var project = $("#testFileProject").val();
    var sampleperiod = $("#testFileSampleperiod").val();
    var standard = $("#testFileStandard").val();
    if (project.length == 0 || sampleperiod.length == 0 || standard.length == 0) {
        layer.alert('必选项不能为空!!!', {
            skin: 'layui-layer-lan',
            closeBtn: 0,
            anim: 6 //动画类型
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/findTestFiles",
            data: {
                project: project,
                sampleperiod: sampleperiod,
                standard: standard,
                year: $("#testFileYear").val(),
                testmodule: $("#testFileTestmodule").val(),
                direction: $("#testFileDirection").val(),
                workingcondition: $("#testFileWorkingcondition").val(),
                testtype: $("#testFileTesttype").val(),
                datatype: $("#testFileDatatype").val(),
            },
            success: function (data) {
                createShowingTable(data);
            }
        });
    }
};

function btn2() {
    var standard = $("#standardFileStandard").val();
    if (standard.length == 0) {
        layer.alert('必选项不能为空!!!', {
            skin: 'layui-layer-lan',
            closeBtn: 0,
            anim: 6 //动画类型
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/findStandardFiles",
            data: {
                standard: standard,
                year: $("#standardFileYear").val(),
                testmodule: $("#standardFileTestmodule").val(),
                level: $("#standardFileLevel").val(),
                datatype: $("#standardFileDatatype").val(),
            },
            success: function (data) {
                createShowingTable(data);
            }
        });
    }
};

function btn3() {
    var project = $("#testFileProjectRight").val();
    var sampleperiod = $("#testFileSampleperiodRight").val();
    var standard = $("#testFileStandardRight").val();
    if (project.length == 0 || sampleperiod.length == 0 || standard.length == 0) {
        layer.alert('必选项不能为空!!!', {
            skin: 'layui-layer-lan',
            closeBtn: 0,
            anim: 6 //动画类型
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/findTestFiles",
            data: {
                project: project,
                sampleperiod: sampleperiod,
                standard: standard,
                year: $("#testFileYearRight").val(),
                testmodule: $("#testFileTestmoduleRight").val(),
                direction: $("#testFileDirectionRight").val(),
                workingcondition: $("#testFileWorkingconditionRight").val(),
                testtype: $("#testFileTesttypeRight").val(),
                datatype: $("#testFileDatatypeRight").val(),
            },
            success: function (data) {
                createShowingTableRight(data);
            }
        });
    }
};

function btn4() {
    var standard = $("#standardFileStandardRight").val();
    if (standard.length == 0) {
        layer.alert('必选项不能为空!!!', {
            skin: 'layui-layer-lan',
            closeBtn: 0,
            anim: 6 //动画类型
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/findStandardFiles",
            data: {
                standard: standard,
                year: $("#standardFileYearRight").val(),
                testmodule: $("#standardFileTestmoduleRight").val(),
                level: $("#standardFileLevelRight").val(),
                datatype: $("#standardFileDatatypeRight").val(),
            },
            success: function (data) {
                createShowingTableRight(data);
            }
        });
    }
};

function createShowingTable(data) {
    var tableStr = "<table class='table table-hover' id='tableleftfile'>";
    var len = data.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + data[i].filename + "</td>" + "<td hidden='hidden' name='filepath'>" + data[i].filepath + "</td>" + "</tr>";
    }
    tableStr = tableStr + "</table>";
    //添加到div中
    $("#tableLeft").html(tableStr);
};

function createShowingTableRight(data) {
    var tableStr = "<table class='table table-hover' id='tablerightfile'>";
    var len = data.length;
    for (var i = 0; i < len; i++) {
        tableStr = tableStr + "<tr>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + "</td>" + "<td>" + data[i].filename + "</td>" + "<td hidden='hidden' >" + data[i].filepath + "</td>" + "</tr>";
    }
    tableStr = tableStr + "</table>";
    //添加到div中
    $("#tableRight").html(tableStr);
};

function btn5() {
    removeAllChild(); //先移除echarts图div下的所有子元素
    var filepathsleft = '';
    var filepathsright = '';
    $("#tableleftfile").find("tr").each(function () {
        var tdArr = $(this).children();
        var filepathleft = tdArr.eq(8).text();
        filepathsleft += filepathleft + ",";
    });

    $("#tablerightfile").find("tr").each(function () {
        var tdArr = $(this).children();
        var filepathright = tdArr.eq(8).text();
        filepathsright += filepathright + ",";
    });
    var arrleft = filepathsleft.split(",");
    var arrright = filepathsright.split(",");
    if (arrleft.length > 1 && arrright.length > 1) {
        getEcharts(filepathsleft, filepathsright);
    } else {
        layer.alert('两边至少各一个文件才能比较!!!', {
            skin: 'layui-layer-lan',
            closeBtn: 0,
            anim: 6 //动画类型
        });
    }

};

function getEcharts(filepathsleft, filepathsright) {

    $.ajax({
        url: "/getEcharts",
        type: "post",
        data: {
            filepathsleft: filepathsleft,
            filepathsright: filepathsright
        },
        beforeSend: function () {
            //var loadingGif="<div align='center'><img src='/static/img/loadding.gif'/>数据加载中...</div>"
            var loadingGif = "<div id='applymask' style='left:0px;top:0px;margin:0px;padding:0px;position: absolute;z-index: 999;filter:alpha(opacity=35);-moz-opacity:0.35;opacity:0.35;background-color: gray;'></div><div id='showsubmit' style='margin:0px;padding:0px;position: absolute;z-index: 1000;top: 25%;left:45%;width: 89px;height: 90px;'><img src='/static/img/loadding.gif'><font color='red'>数据加载中...</font></div>";
            $("#loading").append(loadingGif);
            $("#applymask").css("height", document.body.scrollHeight);
            $("#applymask").css("width", document.body.scrollWidth);
        },
        success: function (data) {
            removeLoadingChild();
            var json = JSON.parse(data);
            var list = json.list;
            var divStr = '';
            var resultdata = "";
            for (var i = 0; i < list.length; i++) {
                divStr = "<div id='echart" + i + "' style='height:400px;width: 900px;float: left'>" + "</div>";
                $("#echarts").append(divStr);
                var json2 = JSON.parse(list[i]);
                var xArray = json2.X2_data;
                var y1Array = json2.Y1_data;
                var y2Array = json2.Y2_data;
                var myChart = echarts.init(document.getElementById("echart" + i));
                option = {
                    title: {
                        text: '',
                        //subtext: '纯属虚构'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: [json2.filepath1, json2.filepath2]
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            magicType: {
                                show: false,
                                type: ['line']
                            },
                            restore: {
                                show: false
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    calculable: true,
                    xAxis: [{
                        type: 'category',
                        boundaryGap: false,
                        data: xArray,
                        axisLabel: {
                            formatter: '{value}MHz'
                        }
                    }],
                    dataZoom: [{
                        type: 'slider',
                        show: true,
                        xAxisIndex: [0],
                        left: '9%',
                        bottom: -5,
                        start: 0,
                        end: 100 //初始化滚动条
                    }],
                    yAxis: [{
                        type: 'value',
                        axisLabel: {
                            formatter: '{value}dBuV/m'
                        }
                    }],
                    series: [{
                        name: json2.filepath1,
                        type: 'line',
                        data: y1Array,
                    },
                    {
                        name: json2.filepath2,
                        type: 'line',
                        data: y2Array,
                        roam: true,
                    }]
                };
                myChart.setOption(option);
                var postpre = ","
                if (i == (list.length - 1)) {
                    postpre = "";
                }
                resultdata += json2.filepath1 + "-" + json2.filepath2 + ":" + encodeURIComponent(myChart.getDataURL({
                    pixelRatio: 2,
                    backgroundColor: '#fff',
                    excludeComponents: ['toolbox']
                })) + postpre;
            }
            var str = json.map2json;

            exportWord(resultdata, str);
        },
        error: function () {
            removeLoadingChild();
            alert("查询异常！！！");
        }
    });
};

function exportWord(resultdata, map2json) {
    var data = "a=" + resultdata + "&map2json=" + map2json;
    var xmlhttp;
    if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else { // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    var protocol = window.location.protocol;
    var host = window.location.host;
    var requrl = protocol + "//" + host + "/exportWord";
    xmlhttp.open("POST", requrl, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //alert("保存成功");
            //var docname = xmlhttp.responseText;
            var downurl = protocol + "//" + host + "/download";
            window.location.href = downurl;
        }
    }
    xmlhttp.send(data);

}

function removeAllChild() {
    var div = document.getElementById("echarts");
    while (div.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        div.removeChild(div.firstChild);
    }
};

function removeLoadingChild() {
    var div = document.getElementById("loading");
    while (div.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        div.removeChild(div.firstChild);
    }
};
