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

	<div id="container">

		<!-- 블로그 해더 -->
		<div id="header">
			<h1>
				<a href="${pageContext.request.contextPath}/${blogVo.id}">${blogVo.blogTitle}</a>
			</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser}">
						<!-- 로그인 전 메뉴 -->
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<!-- 로그인 후 메뉴 -->
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
						<c:if test="${authUser.id == blogVo.id}">
							<li><a
								href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">내블로그
									관리</a></li>
						</c:if>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>


		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a
						href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
					<li><a
						href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>

				<table class="admin-cat">
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id=cateList>
						
					</tbody>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="name" name="name" value=""></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="desc" name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>

			</div>
		</div>

		<!-- 푸터-->
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2018
			</p>
		</div>
	</div>
</body>



<script>
 $(document).ready(function(){
	fetchList();
}); 

 //카테고리 화면에 출력
var fetchList = function(){
	//리스트 요청
	$.ajax({
		url : "${pageContext.request.contextPath}/${authUser.id}/admin/list",	
		type : "post",
		dataType : "json",
		success : function(list){
			/*성공시 처리해야될 코드 작성*/
 			for(var i=0; i<list.length; i++){
 				random(list[i],"down");			
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
} 

 
function random(categoryVo, updown){
	var str = "";
		str +="<tr id='"+categoryVo.cateNo+"'>";
		str +="		<td>"+categoryVo.cateNo+"</td>";
		str +="   	<td>"+categoryVo.cateName+"</td>";
		str +="  	<td>"+categoryVo.postCount+"</td>";
		str +="  	<td>"+categoryVo.description+"</td>";
		str +="     <td><button style='border:0px;' id='del' data-cateno='"+categoryVo.cateNo+"' data-pcount='"+categoryVo.postCount+"'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></button></td>";
	    str +="</tr>";
	
	if(updown=="up"){
		$("#cateList").prepend(str);	//내용물에 위에 붙는거 
	}else if(updown=="down"){
		$("#cateList").append(str);	//내용물에 밑에 붙는거 
	}else{
		console.log("오류");
	}
}  

//삭제버튼 클릭
$("#cateList").on("click","button", function(){
	console.log("삭제버튼 클릭");
	

	var $cateNo = $(this).data("cateno"),
		$pcount = $(this).data("pcount");
	    
		console.log($cateNo);
		console.log($pcount);
	
	if($pcount == 0){    
	  $.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/delete",		
			type : "post",
	
			data : {
					cateNo: $cateNo,  
			},
			dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				if(result==0){ //실패
			      alert("삭제할 수 없습니다");
				}else if(result!=0){//성
				  $("#"+$cateNo).remove();
				}			
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});   
	}else{
		alert("삭제할 수 없습니다.");
	}
	});
 
//카테고리 저장 
 $("#btnAddCate").on("click", function(){
	var $name = $("#name").val(),
		$desc = $("#desc").val();
	
	console.log($name);
	console.log($desc);
	
    $.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/admin/add",		
			type : "post",
			data : {
				    cateName: $name,  
				    description: $desc
			},
			dataType : "json",
			success : function(categoryVo){
			/*성공시 처리해야될 코드 작성*/
			  if(categoryVo != null){
			    random(categoryVo, "up");
			    $("#name").val("");
			    $("#desc").val("");
				console.log("성공!");				  
			  }else{
				console.log("실패..");  
			  }	
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		}); 	
 });
 
 
 
</script>
</html>



