<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- ???? : http://getbootstrap.com/css/   ???? -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
      <link href="../css/kfonts2.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip ???? CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip ???? JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
	
	<!-- ???? ???̾ƿ? ????-->
		<jsp:include page="/user/mypageMain.jsp" />
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	 
        h2{
                font-size: 2.3rem;
                padding-right: 100px;
            }
            
            #head_aticle{
            padding-top : 80px;
            }
            
            .container{
            padding-left: 250px;
            padding-top : 130px;
            }
            
            #caption{
            font-size: 15px;
            }
            
            h3{
            font-size: 15px;
            }
            
    </style>
    
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//=============    ?˻? / page ?ΰ??? ???? ????  Event  ó?? =============	
		function fncGetList(currentPage) {
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/user/listFriendRequest?").submit();
		}
		
		//============= "?˻?"  Event  ó?? =============	
		 $(function() {
			 //==> DOM Object GET 3???? ???? ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-default" ).on("click" , function() {
			fncGetList(1);
			});
		 });
		
	</script>
	
</head>

<body>
	
	<!--  ȭ?鱸?? div Start /////////////////////////////////////-->

	<div class="container" >
	
		<div class="head_aticle" align="center" id = "head_aticle">
	      <h2 class="tit" style="color: #333;">ģ????û ????</h2>
	    </div>
	    
	    <br></br>
	    <!-- table ???? ?˻? Start /////////////////////////////////////-->
	    <div class="row" >
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-secondary" >
		    		??ü  ${resultPage.totalCount } ?Ǽ?, ???? ${resultPage.currentPage}  ??????
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
			    <!-- 
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>?г???</option>
					</select>
				  </div>
				  
				
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">?˻???</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="?˻???"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div> 
				  
				  <button type="button" class="btn btn-default">?˻?</button>-->
				  
				  
				  
				  
				  
				  <!-- PageNavigation ???? ?????? ???? ?????? ?κ? -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    
	    	
		</div>
		<!-- table ???? ?˻? Start /////////////////////////////////////-->
		
		<br></br>
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >?̸???</th>
            <th align="left"> ?г???</th>
              <th align="left"> ??û????</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="user" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center">${ i }</td>
			  <td align="left" > <a  href="/user/getUserTarget?userEmail=${user.userEmail}">${user.userEmail} </a></td>
			  <td align="left">${user.nick}</td>
			   <td align="left"><a href="/user/updateAccStatus?userEmail=${user.userEmail}">????    & </a>
			   <a href="/user/deleteFriendRequest?userEmail=${user.userEmail}">????</a>
			   </td>
			   
			  <td align="left">
			  	<input type="hidden" value="${user.userEmail}">
			  	<input type="hidden" value="${friendBlack.targetEmail}">
			  </td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	</div>
 	<!--  ȭ?鱸?? div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>