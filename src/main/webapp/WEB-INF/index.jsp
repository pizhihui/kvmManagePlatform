<%
    response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    String newLocation = "admin/list";
    response.setHeader("Location",newLocation);
%>
