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
        <div class="col-md-6 my-auto">
            <div class="float-left">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/index'/>">
                            <i class="fa fa-arrow-left"></i>&nbsp; Назад</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-md-6 my-auto">
            <div class="float-right">
                <ul class="nav">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/logout'/>">
                                    <i class="fa fa-user"></i>&nbsp; ${user.name} | Выйти
                                </a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/login'/>">Войти</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/reg'/>">Регистрация</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                ${post.name}
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-2 my-auto">
                        <i class="fa fa-user"></i>&nbsp; ${post.author.name}
                    </div>
                    <div class="col-md-10 my-auto">
                        <div class="row">
                            <small class="text-muted">${post.created.time}</small>
                        </div>
                        <div class="row">${post.description}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:forEach items="${post.messages}" var="message">
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-2 my-auto">
                            <i class="fa fa-user"></i>&nbsp; ${message.author.name}
                        </div>
                        <div class="col-md-10 my-auto">
                            <div class="row">
                                <small class="text-muted">${message.created.time}</small>
                            </div>
                            <div class="row">${message.text}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<c:if test="${not empty user}">
    <div class="container pt-3">
        <div class="row">
            <div class="card" style="width: 100%">
                <div class="card-body">
                    <form action="<c:url value='/message/create'/>" method='post'>
                        <div class="form-group">
                            <label for="text">Новое сообщение:</label>
                            <input type="text" class="form-control" name="text" id="text" required>
                        </div>
                        <input type="hidden" name="postId" value="${post.id}"/>
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:if>

</body>
</html>
