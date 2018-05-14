<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>
	<div class="center-content">

		<!-- 메인해더 -->
		<a href="${pageContext.request.contextPath}/"> <img class="logo"
			src="${pageContext.request.contextPath}/assets/images/logo.jpg">
		</a>
		<ul class="menu">
		<c:choose>
	        <c:when test="${empty authUser}" >
				<!-- 로그인 전 메뉴 -->
		 		<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
	        </c:when>
		    <c:otherwise>
				<!-- 로그인 후 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="">내블로그</a></li> 
			</c:otherwise>
	    </c:choose>
		</ul>

		<form class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label> <input type="text"
			 id="userName"	name="userName" value="" /> <label class="block-label" for="id">아이디</label>
			<input type="text" name="id" id="id" value="" /> <input
				id="btn-checkid" type="button" value="id 중복체크">
            <input type="hidden" id="isIdCheck" value="false">
			<p id="checkid-msg" class="form-error">&nbsp;</p>

			<label class="block-label" for="password">패스워드</label> <input
				id="password" type="password" name="password" value="" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>

</body>

<script>
  $("#btn-checkid").on("click", function(){
	  console.log("id체크");
	  var $id=$("#id").val();
	  console.log(id);
	  
	  $.ajax({
			url : "${pageContext.request.contextPath}/user/idCheck",		
			type : "post",
			data : { id: $id},

			dataType : "json",
			success : function(isExist){
				if(isExist==true){
					$("#checkid-msg").html("사용할 수 있는 아이디 입니다.");
					$("#checkid-msg").css("color","blue");
					$("#isIdCheck").val("true");
					
				}else{
					$("#checkid-msg").html("다른 아이디로 가입해 주세요.");
					$("#checkid-msg").css("color","red");
					return false;
				}				
				console.log(isExist);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
  });
  
  $("#join-form").on("submit", function(){
	   var status = finalCheck();//입력 폼에 입력한 상황체크
	   if(status == true){
		   return true;
	   }else{
		   return false;
	   }
   });   

  function finalCheck(){
	  if(!$("#userName").val()){ //name을 입력하지 않으면 수행
		  alert("이름을 입력해주세요");
          $("#userName").focus(); //입력 포커스 이동
          return false; //함수종료
       }
	  
	  if(!$("#id").val()){ //name을 입력하지 않으면 수행
		   alert("아이디를 입력해주세요");
         $("#id").focus(); //입력 포커스 이동
         return false; //함수종료
      }
	  
	  if($("#isIdCheck").val() != "true"){
		  alert("아이디 중복체크를 해주세요.");
		  $("#id").focus();
		  return false;
	  }
	  
	  if(!$("#password").val()){ //password을 입력하지 않으면 수행
		   alert("패스워드를 입력해주세요");
          $("#password").focus(); //입력 포커스 이동
          return false; //함수종료
       }
	  
	  if($("#agree-prov:checked").length<1){ //agree-prov을 입력하지 않으면 수행
		   alert("약관에 동의해 주세요");
          $("#agree-prov").focus(); //입력 포커스 이동
          return false; //함수종료
       }
	   return true;
  }

</script>
</html>


