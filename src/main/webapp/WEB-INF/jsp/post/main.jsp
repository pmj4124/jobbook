<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 여러페이지 불러와서 한 페이지 구성하려면 jstl core library 필요  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JOBBOOK</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel = "stylesheet" href = "/css/style.css" type = "text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

</head>
<body>
<div id = "wrap">
<c:import url = "/WEB-INF/jsp/include/header.jsp" />
<section class="content d-flex justify-content-center align-items-center">

<!-- 왼쪽 메뉴 파일 -->
<c:import url = "/WEB-INF/jsp/post/menu.jsp" />

<div class="center content bg-danger w-75 h-100 content  justify-content-center align-items-center">
	
	<!-- 글쓰기 -->
	<div class="join-box">
	<div class="card m-3 p-3" style="width: 37rem;">
	<form method="post" action="/post/create" id="addForm"> 
		<!-- 동영상주소 -->
		<div class="input-group mb-3">
  			<input type="text" class="form-control" id="movieURL" placeholder="동영상 주소를 입력하세요" aria-label="movieURL" aria-describedby="basic-addon2"> 
			<div class="input-group-append"><span class="input-group-text addPostBtn" id="basic-addon2">게시</span> </div>
		</div>
		<!-- 태그 -->		
		<div class="input-group mb-3">
  		<input type="text" class="form-control" id="tag" placeholder="태그를 입력하세요" aria-label="tag" aria-describedby="basic-addon2">
		</div>	
	</form>
	</div>
	</div>
	
	<!-- 글보기-->
	<c:forEach var = "postDetail" items = "${postList}" varStatus ="status">
	<c:set var = "movieURL" value = "https://www.youtube.com/embed/${postDetail.post.movieURL}" ></c:set>
	
	
	<div class="card m-3 p-3" style="width: 37rem;">
		<!-- 맨위 작성자명과 오른쪽 버튼 -->
		<div class="top d-flex m-1" style="justify-content: space-between;">
		<div>${postDetail.post.userName }</div>
		<div><a href="#"><i class="bi bi-three-dots useDeleteBtn" data-toggle="modal" data-target="#exampleModalCenter" data-post-id="${postDetail.post.id}"></i></a></div>
		</div>
		
		<!-- 가운데 영상 -->
		<iframe width="560" height="315" src="${movieURL }" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		
		<!-- 영상 좋아요, 태그, 댓글, 댓글달기 -->
		<div class="card-body">
			<!-- 좋아요 -->
			<h5 class="card-title">
				<span class="likeBtn" id="${postDetail.post.id}" data-post-id="${postDetail.post.id}">
					<c:choose>
						<c:when test = "${postDetail.like }"><i class="bi bi-heart-fill heart-icon text-danger"></i></c:when>
						<c:otherwise><i class="bi bi-heart heart-icon text-danger"></i></c:otherwise>			
					</c:choose>
				</span>
			좋아요 ${postDetail.likeCount}개</h5>
			
			
			<!-- 태그 -->
			<p class="card-text">${postDetail.post.tag}</p>
			
			<!-- 등록된 댓글 -->
			 <c:forEach var="reply" items ="${ postDetail.replyList}">
			 <div class="reply d-flex" style="justify-content: space-between;">
			 	 <!-- 아이디와 댓글 -->
			 	 <div class="mt-1 replyContent">
   			 		<b>${reply.userName }</b>${reply.replyContent }
   			 	</div>
   			 	<!--  댓글 지우기 -->
   			 	<div class="replyDelete"><i class="bi bi-x deleteReplyBtn" data-reply-id="${reply.id}"></i></div>	
			 </div>
			 </c:forEach>
			 
			<!-- 댓글달기 -->
			<div class="input-group mb-3 align-items-center">
				<input type="text" class="form-control" id="replyContent${postDetail.post.id}" placeholder="댓글을 입력하세요." aria-label="Recipient's username" aria-describedby="basic-addon2">
  				<div class="input-group-append"><span class="input-group-text replyBtn" id="basic-addon2" data-post-id="${postDetail.post.id}">게시</span></div>
			</div>
			
			</div><!-- div class card-body -->
		</div><!--  card -->
		</c:forEach>


</div><!-- 가운데 -->
</section><!-- 섹션 -->
<c:import url = "/WEB-INF/jsp/include/footer.jsp" /><!-- 푸터 -->
</div><!--  wrap -->



<script>

$(document).ready(function(){
	
//검색은 ajax 쓰지않음. ajax는 페이지 이동 없을때 데이터만.


//검색
	$(".searchBtn").on("click",function(){
		
	var searchTag = $("#searchTag").val();
	location.replace("http://localhost:8080/post/main_view?searchTag="+searchTag);
	});
//글쓰기
	$(".addPostBtn").on("click", function(){
			alert("클릭");
			var movieURL = $("#movieURL").val();
			var tag = $("#tag").val();
		
			$.ajax({
				type : "post",
				url : "/post/create",
				data : {"movieURL": movieURL, "tag": tag},
				success : function(data){
					if(data.result == "success"){
						alert("업로드 성공");
						location.reload();
					}else{
						alert("업로드 실패");
					}
				},
				error : function(){
					alert("에러 발생");
				}
			
			});//게시 ajax
				
	});//게시 클릭
		
	
	
	$(".likeBtn").on("click", function(e){
		e.preventDefault(); 
		var postId = $(this).attr("id");	
		
		$.ajax({
			type : "post",
			url : "/like/onLike",
			data : {"postId" : postId},
			success : function(data){
				location.reload();
				},
			
			error : function(){
				alert("에러 발생");
			}		
		})//좋아요있는지확인 ajax	
	})//좋아요클릭
		
		
	$(".replyBtn").on("click", function(){
		let postId = $(this).data("post-id");	
		let replyContent = $("#replyContent" +postId).val();
		
		$.ajax({
			type : "post",
			url : "/reply/add",
			data : {"postId" : postId, "replyContent": replyContent},
			success : function(data){
				if(data.result == "success"){
					alert("댓글 작성 성공");
					location.reload();
				}else{
					alert("에러 발생");
				}
			},
			error : function(){
				alert("에러 발생");
			}		
		});//댓글 ajax	
	});//댓글작성클릭
	
		

});//도큐먼트레디

</script>
</body>
</html>