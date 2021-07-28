<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Forum</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="form-row">
                    <div class="col-md-8 my-auto">
                        <h4 style="margin-bottom: 0">Форум</h4>
                    </div>
                    <div class="col-md-4 my-auto">
                        <div class="float-right">
                            <ul class="nav" id="auth">
                                <c:choose>
                                    <c:when test="${not empty user}">
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/logout'/>">
                                                <i class="fa fa-user"></i>&nbsp; ${user.username} | Выйти
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/login'/>" id="a1">
                                                Войти
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="<c:url value='/reg'/>" id="a2">
                                                Регистрация
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body pt-0 pb-0">
                <table id="table" class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Тема</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Автор</th>
                        <th scope="col">Дата создания</th>
                        <th scope="col">Сообщений</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td>
                                <a class="nav-link" href="<c:url value='/post?postId=${post.id}'/>"
                                   style="padding-left: 0">
                                    <i class="fa fa-envelope"></i>&nbsp; ${post.name}</a>
                            </td>
                            <td style="vertical-align: middle">${post.description}</td>
                            <td style="vertical-align: middle">${post.author.username}</td>
                            <td style="vertical-align: middle">${post.created.time}</td>
                            <td style="vertical-align: middle">${post.messages.size()}</td>
                            <td style="vertical-align: middle">
                                <c:if test="${post.author.username == user.username}">
                                    <a href='<c:url value="/post/edit?postId=${post.id}"/>'>
                                        <i class="fa fa-edit mr-3"></i>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <c:if test="${not empty user}">
        <div class="container pt-3">
            <div class="row">
                <form action="<c:url value='/post/edit'/>">
                    <button type="submit" class="btn btn-primary">Создать новую тему</button>
                </form>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>