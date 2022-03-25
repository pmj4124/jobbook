<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<header class = "bg-secondary d-flex align-items-center justify-content-between">
    
	<!-- 검색창 -->
	<div class="search-mode">
	    <input type="text" id="searchTag">
	
	    <div class="icon">
	      <div class="search">
	      
	      </div>
		
		<!-- 폼을 활용하면 페이지 이동하면서 parameter전달/ -->
	
		<div class="plus-option searchBtn">
	          <i class="bi bi-search"></i>
	      </div>
	     
	    </div>
	
	</div>
	

	<!-- 오른쪽 프로필 -->
	<div class="profile">
	 <c:if test="${sessionConfigVO ne null}"> 
	    <ul style="height:30px;float:right;margin-bottom:20px;" class="fn-font">
	  	 	 <li><a style="color:#f97088;text-decoration:none;" class="" >${sessionConfigVO.profile_nickname}' s come in</a></li>
		</ul>
	    <div class="profile_div"><img src="<c:out value='${sessionConfigVO.profile_image}'/>" width=30 height=30/></div>
	    <div><a href = "/login/logout_proc">로그아웃 </a></div>
	</c:if>
	
	<c:if test='${sessionConfigVO eq null}'>
	 <ul style="height:30px;float:right;margin-bottom:20px;" class="fn-font">
		 <li><a style="color:blue;" class="forget_login" onclick="fn_forgetID()">Forget ID/PASSWORD</a></li>
	</ul>    
	</c:if>
	
	</div>
                
</header>
    