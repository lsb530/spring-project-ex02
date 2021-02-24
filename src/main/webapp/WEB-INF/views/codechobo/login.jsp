<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Boki
  Date: 2021-02-16
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<style type="text/css">
    #AuthBlock {
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
        right: 0;
        background: #e9ecef;
        /* flex로 내부 내용 중앙 정렬 */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    #WhiteBox {
        box-shadow: 0 0 8px rgba(0, 0, 0, 0.025);
        padding: 2rem;
        width: 360px;
        background: white;
        border-radius: 2px;
    }
    #WhiteBox > #logo-area {
        display: block;
        padding-bottom: 2rem;
        text-align: center;
        font-weight: bold;
        letter-spacing: 2px;
    }
    h3 {
        margin: 0 0 1rem;
        color: #343a40;
    }
    input:not([type=checkbox]) {
        font-size: 1rem;
        border: none;
        border-bottom: 1px solid #adb5bd;
        padding-bottom: 0.5rem;
        outline: none;
        width: 100%;
    }
    button {
        border: none;
        border-radius: 4px;
        font-weight: bold;
        color: white;
        background-color: #22b8cf;
        outline: none;
        cursor: pointer;
        margin-top: 1rem;
        padding-top: 0.75rem;
        padding-bottom: 0.75rem;
        width: 100%;
        font-size: 1.125rem;
    }
    button.hover {
        background-color: #c5f6fa;
    }
    #sh {
        display: inline-block;
        margin-left: 4rem;
    }
</style>
<script type="text/javascript">
    // Memo DOM 시작할 때 최초로 실행됨
    window.addEventListener('DOMContentLoaded', function() { // CSS, 이미지, 프레임은 무시해서 더 빠름
        const button_element = document.getElementById("_loginButton");
        button_element.disabled = true;
        button_element.style.backgroundColor = '#dee2e6';
        button_element.color = '#adb5bd';
        button_element.style.cursor = 'not-allowed';
        button_element.addEventListener('mouseover', () => {
            button_element.setAttribute('class','hover');
        });
        button_element.addEventListener('mouseout', () => {
            button_element.removeAttribute('class');
        });
    });
    function checkEmpty() {
        const id_element = document.getElementById("_id");
        const pw_element = document.getElementById("_pw");
        const button_element = document.getElementById("_loginButton");
        if (id_element.value !== '' && pw_element.value !== '') {
            button_element.disabled = false;
            button_element.style.backgroundColor = '#22b8cf';
            button_element.color = 'white';
            button_element.style.cursor = 'pointer';
            button_element.addEventListener('mouseover', () => {
                button_element.setAttribute('class','hover');
            });
            button_element.addEventListener('mouseout', () => {
                button_element.removeAttribute('class');
            });
        }
    }
    function showPw(a) {
        const pw_element = document.getElementById("_pw");
        if (a.checked) {
            console.log('checked!');
            pw_element.type = "text";
        } else {
            console.log('Not checked');
            pw_element.type = "password";
        }
    }
    function onSubmit() {
        const loginForm = document.getElementById("_loginForm");
        loginForm.submit();
    }
    function changeCheckValue(e) {
        if (e.checked) {
            this.value = "true";
        } else {
            this.value = "false";
        }
        console.log(this.value);
    }
</script>
</head>
<body>
<div id="AuthBlock">
    <div id="WhiteBox">
        <div id="logo-area">로그인</div>
        <form action="/login" method="post" id="_loginForm">
            <div>
                <label for="_id">아이디</label>
                <c:choose>
                    <c:when test="${empty cookie.cook}">
                        <input type="text" name="id" id="_id" onfocusout="checkEmpty();">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="id" id="_id" value="${cookie.cook.value}" onfocusout="checkEmpty();">
<%--                        <input type="text" name="id" id="_id" value="cook" onfocusout="checkEmpty();">--%>
                    </c:otherwise>
                </c:choose>

            </div>
            <div>
                <label for="_pw">패스워드</label>
                <input type="password" name="pw" id="_pw" onkeydown="checkEmpty()" onfocusout="checkEmpty();">
            </div>
            <div>
                아이디 기억
                <c:choose>
                    <c:when test="${not empty cookie.cook}">
                        <input type="checkbox" checked name="ck_cookie" id="chk" onfocus="checkEmpty()" onchange="changeCheckValue(this);">
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="ck_cookie" id="chk" onfocus="checkEmpty()" onchange="changeCheckValue(this);">
                    </c:otherwise>
                </c:choose>
                <div id="sh">
                    패스워드 보기 <input type="checkbox" name="showpw" id="sp" onfocus="checkEmpty()" onclick="showPw(this);">
                </div>
            </div>
            <button type="submit" id="_loginButton" onclick="onSubmit();">로그인</button>
        </form>
    </div>
</div>
</body>
</html>
