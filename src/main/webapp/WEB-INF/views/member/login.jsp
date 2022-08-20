<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope.loginMember}">
            <form action="login" method="POST" onsubmit="return loginValidate()">
                <div>
                    <input type="email" name="memberEmail" placeholder="이메일을 입력하세요." value="${cookie.saveId.value}" autocomplete="off">
                </div>
                <div>
                    <input type="password" name="memberPw" placeholder="비밀번호를 입력하세요." autocomplete="off">
                </div>
                <div>
                    <button type="submit">로그인</button>
                </div>
                <label>
                    <!-- checked 속성 : radio/checkbox를 체크하는 속성 -->
                    <input type="checkbox" name="saveId" ${chk}  id="saveId"> 아이디 저장
                </label>
            </form>
        </c:when>
        <c:otherwise>
            <a href="${contextPath}/member/myPage/info">마이페이지</a>
            <a href="${contextPath}/member/myPage/info">${loginMember.memberName}</a>
        </c:otherwise>
  </c:choose>
</body>
</html>