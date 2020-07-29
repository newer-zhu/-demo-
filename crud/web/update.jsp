<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>

        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>

        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <input type="hidden" name="id" value="${user.id}">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" value="${user.name}" id="name" name="name"   placeholder="请输入姓名" />
          </div>

          <div class="form-group">
            <label>性别：</label>
              <c:if test="${user.gender == 'man'}" >
                  <input type="radio" name="gender" value="man" checked />男
                  <input type="radio" name="gender" value="woman"  />女
              </c:if>
              <c:if test="${user.gender == 'woman'}" >
                  <input type="radio" name="gender" value="man"  />男
                  <input type="radio" name="gender" value="woman" checked />女
              </c:if>
          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${user.age}" placeholder="请输入年龄" />
          </div>

          <div class="form-group">
            <label for="address">籍贯：</label>
              <c:if test="${user.address == '广东'}">
                  <select name="address" class="form-control" id="address">
                      <option value="广东" selected>广东</option>
                      <option value="广西">广西</option>
                      <option value="湖南">湖南</option>
                  </select>
              </c:if>
              <c:if test="${user.address == '广西'}">
                  <select name="address" class="form-control" id="address">
                      <option value="广东" >广东</option>
                      <option value="广西" selected>广西</option>
                      <option value="湖南">湖南</option>
                  </select>
              </c:if>
              <c:if test="${user.address == '湖南'}">
                  <select name="address" class="form-control" id="address">
                      <option value="广东">广东</option>
                      <option value="广西">广西</option>
                      <option value="湖南" selected>湖南</option>
                  </select>
              </c:if>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" value="${user.qq}" name="qq" placeholder="请输入QQ号码" id="qq"/>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input id="email" type="text" class="form-control" value="${user.email}" name="email" placeholder="请输入邮箱地址"/>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" value="返回"/>
             </div>
        </form>
        </div>
    </body>
</html>