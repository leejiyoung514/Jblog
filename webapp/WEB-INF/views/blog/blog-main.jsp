<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">

		<!-- 블로그 해더 -->
		<div id="header">
			<h1><a href="${pageContext.request.contextPath}/${blogVo.id}">${blogVo.blogTitle}</a></h1>
			<ul>
			<c:choose>
	          <c:when test="${empty authUser}" >
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			  </c:when>
		      <c:otherwise>
				<!-- 로그인 후 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<c:if test="${authUser.id == blogVo.id}">
				<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">내블로그 관리</a></li> 
			    </c:if>
			  </c:otherwise>
	        </c:choose>		
			</ul>
		</div>
		
		<div id="wrapper">
			<div id="content">
			
			 <!-- 글보기 -->
				<div class="blog-content">
				<c:choose>
						<c:when test="${empty post}">
							<!-- 등록된 글이 없는경우 -->
							<h4>등록된 글이 없습니다.</h4>
						</c:when>
						<c:otherwise>
							<!-- 등록된 글이 있는경우 -->
							<h4>${post.postTitle}</h4><br><br>
							<p>${post.postContent}</p>
						</c:otherwise>
				</c:choose>	
			    </div>

             <!--코멘트 -->
                <table id="cmts">
						<tr>
						<c:if test="${!empty authUser.id && !empty post }">
						    <td><input size="4" type="text" id="username" value="${authUser.userName}"  readonly></td>
							<td><input size="50" type="text" name="comments" id="comments" value="" placeholder="댓글내용을 입력해주세요"/></td><br/>
							<td><input id="btnAddCmts" data-userno="${authUser.userNo}" data-postno="${post.postNo}" type="submit" value="저장"></td>	
						</c:if>
						</tr>
			  	</table>
			  
				<table id="commentsList">
				 <!--  <tr>
					   <td>이지영</td><br/>
					   <td>댓글</td><br/>
					   <td>작성일</td>	
			      </tr> -->
				</table> 
		         
             <!-- 글목록 --> 			
				<ul class="blog-list">
				    <c:forEach items="${postList}" var="postList">
				    <li>
						<a href="${pageContext.request.contextPath}/${blogVo.id}?cateNo=${postList.cateNo}&postNo=${postList.postNo}">${postList.postTitle}</a>
						<span>${postList.regDate}</span>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">				
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			  <c:forEach items="${categorylist}" var="CategoryVo" >
			  <li><a href="${pageContext.request.contextPath}/${blogVo.id}?cateNo=${CategoryVo.cateNo}">${CategoryVo.cateName}</a></li>
			  </c:forEach>	
			</ul>
		</div>
		
		<!-- 푸터-->
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2018
			</p>
		</div>
		
	</div>
</body>
<script type="text/javascript">

$(document).ready(function(){
	fetchList();
}); 

//댓글 리스트 요청
var fetchList = function(){
	//리스트 요청
	$.ajax({
		url : "${pageContext.request.contextPath}/${blogVo.id}/list/${post.postNo}",	
		type : "post",
		dataType : "json",
		
		
		success : function(list){
			/*성공시 처리해야될 코드 작성*/
 			for(var i=0; i<list.length; i++){
				render(list[i],"down");			
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
} 

//render기능
function render(commentVo, updown){
	var str ="";
	    str +="<tr id='"+commentVo.cmtNo+"'>";
	    str +="     <td>"+commentVo.userName+"</td><br/>"
	    str +="     <td>"+commentVo.cmtContent+"</td><br/>"
	    str +="     <td>"+commentVo.regDate+"</td><br/>"
      str +="     <td><button style='border:0px;' id='del' data-cmtno='"+commentVo.cmtNo+"'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></button></td><br/>"
	    str +="</tr>";
	    
	    if(updown=="up"){
	    	$("#commentsList").prepend(str);
	    }else if(updown=="down"){
	    	$("#commentsList").append(str);
	    }else{
	    	console.log("오류");
	    }
}


//댓글저장버튼 클릭
$("#btnAddCmts").on("click", function(){
	console.log("저장버튼 클릭");
	var $userName = $("#username").val();
	var $comments = $("#comments").val();
	var $userNo = $(this).data("userno");
	var $postNo = $(this).data("postno");
	
	console.log($userName);
	console.log($comments);
	console.log($userNo);
    console.log($postNo);
	
	 $.ajax({
			url : "${pageContext.request.contextPath}/${authUser.id}/add",		
			type : "post",
			data : {
				  userName: $userName,  
				  cmtContent: $comments,
				  userNo: $userNo,
				  postNo: $postNo
			},
			dataType : "json",
			success : function(commentVo){
			/*성공시 처리해야될 코드 작성*/
			 if(commentVo != null){
				 render(commentVo, "up");
			    $("#comments").val("");
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


//댓글삭제버튼 클릭
$("#commentsList").on("click", "button", function(){
	console.log("삭제버튼 클릭");
	var $cmtNo = $(this).data("cmtno");
    console.log($cmtNo);
    
    $.ajax({
		url : "${pageContext.request.contextPath}/${authUser.id}/delete",		
		type : "post",

		data : {
				cmtNo: $cmtNo,  
		},
		dataType : "json",
		success : function(result){
			/*성공시 처리해야될 코드 작성*/
			if(result==0){ //실패
		      alert("삭제할 수 없습니다");
			}else if(result!=0){//성공
			  $("#"+$cmtNo).remove();
			}			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});   
	
}); 


</script>
</html>


