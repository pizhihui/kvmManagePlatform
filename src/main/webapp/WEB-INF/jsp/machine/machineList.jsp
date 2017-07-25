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
        <h3>虚拟机列表</h3>


        <div class="row">

        </div>

        <div class="row">
            <br>
            <div class="col-md-12">
                <table  class="table table-striped table-hover" style="margin-top: 0px">
                    <thead>
                    <tr>
                        <td>虚拟机名称</td>
                        <td>IP</td>
                        <td>cpu(核数)</td>
                        <td>内存(G)</td>
                        <td>所属物理机IP</td>
                        <td>运行状态</td>
                        <td>备注</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${virtMachines}" var="machine">
                        <tr>
                            <td>${machine.name}</td>
                            <td>${machine.ip}</td>
                            <td>${machine.cpu}</td>
                            <td>${machine.mem}</td>
                            <td>${machine.hostIp}</td>
                            <td>${machine.state}</td>
                            <td>${machine.description}</td>
                            <td>
                                <a href="#">修改配置</a>
                                <a href="#">静态迁移</a>
                                <c:choose>
                                    <c:when test="${machine.state == 'shut'}">
                                        <a href="#">启动</a>
                                    </c:when>
                                    <c:when test="${machine.state == 'running'}">
                                        <a href="#">关闭</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="#">未知</a>
                                    </c:otherwise>
                                </c:choose>
                                <a href="#" onclick="removeMachine('${machine.id}', '${machine.ip}')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

