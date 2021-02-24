<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>뽀기홈</title>
<style type="text/css">
    #Header {
        position: fixed;
        width: 100%;
        background: white;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
        border-bottom: 2px solid hotpink;
    }
    #Wrapper {
        height: 4rem;
        display: flex;
        align-items: center;
        justify-content: space-evenly; /* 자식 엘리먼트 사이의 여백을 최대로 설정 */
    }
    #Wrapper .logo {
        font-size: 1.125rem;
        font-weight: 800;
        letter-spacing: 2px;
    }
    #Wrapper .right {
        display: flex;
        align-items: center;
        justify-content: space-evenly;
    }
    #Wrapper .right h4 {
        display: inline-block;
    }
    button {
        border: none;
        border-radius: 4px;
        font-weight: bold;
        color: white;
        background-color: #495057;
        outline: none;
        cursor: pointer;
        padding-top: 0.75rem;
        padding-bottom: 0.75rem;
        width: 100%;
        font-size: 1rem;
    }
    button.hover {
        background-color: #868e96;
    }
    #Spacer {
        height: 4rem;
    }
    #Content_Wrapper {
        margin-top: 3rem;
        padding-top: 3rem;
        padding-bottom: 3rem;
    }
    #Status {
        display: flex;
        justify-content: flex-end;
        margin-bottom: 3rem;
    }
    #Content {
        display: flex;
        align-items: center;
        flex-direction: column;
    }
</style>
<script type="text/javascript">
    // Memo DOM 시작할 때 최초로 실행됨
    window.addEventListener('DOMContentLoaded', function() { // CSS, 이미지, 프레임은 무시해서 더 빠름
        const button = document.getElementById("login_button");
        button.addEventListener('mouseover', () => {
            button.setAttribute('class','hover');
        });
        button.addEventListener('mouseout', () => {
            button.removeAttribute('class');
        });
    });
</script>
</head>
<body>
    <div id="Header">
        <div id="Wrapper">
            <div class="logo">
                보기's 홈
            </div>
            <div class="right">
                <div id="Session">
                    <c:if test="${not empty user}">
                        <h4>${user.id}님 방문을 환영합니다^^</h4>
                    </c:if>
                </div>
                <c:choose>
                <c:when test="${empty user}">
                    <button id="login_button" onclick="location.href='/login'">로그인</button>
                </c:when>
                <c:otherwise>
                    <button id="login_button" onclick="location.href='/logout'">로그아웃</button>
                </c:otherwise>
                </c:choose>
                    <%--                <a href="/login">로그인</a>--%>
            </div>
        </div>
    </div>
    <div id="Spacer"></div>
    <div id="Content_Wrapper">
        <div id="Status">
            <p>상태표시 위치</p>
        </div>
        <div id="Content">
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
            <h1>컨텐츠입니다</h1>
        </div>
    </div>
</body>
</html>
