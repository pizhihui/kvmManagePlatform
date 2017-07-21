<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pizhihui
  Date: 18/07/2017
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="container">
        <jsp:include page="/WEB-INF/include/headAdmin.jsp"/>
        <h3>安装虚拟机页面</h3>

        <form class="form-horizontal form-bordered form-row-stripped" id="addHostMachineForm">
            <div class="row">
                <!-- 控件开始 -->
                <div class="col-md-8">
                    <!-- form-body开始 -->
                    <div class="form-body">
                        <div class="form-group">
                            <label class="control-label col-md-3">虚拟机名称:</label>
                            <div class="col-md-7">
                                <input type="text" name="machineName" value="" id="machineName" class="form-control" placeholder="名称">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">虚拟机IP:</label>
                            <div class="col-md-7">
                                <input type="text" name="machineIp" value="" id="machineIp" class="form-control" placeholder="IP">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">所在物理机IP:</label>
                            <div class="col-md-7">
                                <input type="text" name="hostIp" value="" id="hostIp" class="form-control" placeholder="IP">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">虚拟机CPU核数:</label>
                            <div class="col-md-7">
                                <input type="text" name="machineCpu" value="" id="machineCpu" class="form-control" placeholder="CPU核数">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">虚拟机内存(G):</label>
                            <div class="col-md-7">
                                <input type="text" name="machineMem" value="" id="machineMem" class="form-control" placeholder="内存">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">虚拟机备注:</label>
                            <div class="col-md-7">
                                <textarea class="form-control" name="machineDesc" rows="3" id="machineDesc" placeholder="应用描述"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-7" style="margin-left: 200px">
                                <button type="button" data-dismiss="modal" class="btn" onclick="resetForm()">取消</button>
                                <button type="button" id="installVirtMachineBtn" class="btn red" onclick="installVirtMachine()">开始安装</button>
                            </div>
                        </div>
                    </div>
                    <!-- form-body 结束 -->
                </div>
                <div id="updateAppDetailInfo"></div>

            </div>
        </form>


    </div>

