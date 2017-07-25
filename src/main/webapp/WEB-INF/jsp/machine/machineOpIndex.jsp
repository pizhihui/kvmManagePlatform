<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>KVM管理平台</title>
    <jsp:include page="/WEB-INF/include/head.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/include/headAdmin.jsp"/>

        <div class="tabbable-custom">
            <ul class="nav nav-tabs" id="app_tabs">

                <li class="active"><a href="#machine_list"
                                      data-url="/machine/list"
                                      data-toggle="tab">虚拟机统计信息</a></li>

                <li><a href="#machine_snapshot" data-url="/machine/machineSnapshot" data-toggle="tab">快照管理</a></li>

                <li><a href="#machine_install" data-url="/machine/installVirtMachine" data-toggle="tab">安装虚拟机</a></li>

                <li><a href="#machine_host_add" data-url="/machine/addHostMachine" data-toggle="tab">添加物理机</a></li>

            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="machine_list"></div>

                <div class="tab-pane" id="machine_snapshot"></div>

                <div class="tab-pane" id="machine_install"></div>

                <div class="tab-pane" id="machine_host_add"></div>

            </div>
        </div>

    </div>
    <jsp:include page="/WEB-INF/include/foot.jsp"/>
    <script type="text/javascript">
        $('#app_tabs a').click(function (e) {
            e.preventDefault();

            var url = $(this).attr("data-url");
            var href = this.hash;
            var pane = $(this);
            var id = $(href).attr("id");
            // ajax load from data-url
            $(href).load(url, function (result) {
                pane.tab('show');
                //initChart(id);
            });
        });

        var tabTag = "${tabTag}";
        //tabTag = "";
        if (tabTag.length > 0 && $('#' + tabTag).length > 0) {
            var tabId = '#' + tabTag;
            $("a[href=" + tabId + "]").click();
        } else {
            $("a[href=#machine_list]").click();
        }

    </script>
   <%-- <script type="text/javascript" src="/resources/js/mem-cloud.js"></script>--%>
    <script type="text/javascript" src="/resources/js/docs.min.js"></script>
</body>

</html>
