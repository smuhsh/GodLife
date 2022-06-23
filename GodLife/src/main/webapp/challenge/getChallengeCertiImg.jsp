<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" 
integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css" 
integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js" 
integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" 
crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<link href="/resources/css/listCertiImg.css" rel="stylesheet">
<style>
#deleteButton{
     position: relative;
    left: 550px;
    height: 30px;
}
.container{
	padding-top: 200px;
}
body {
	overflow-x: hidden;
}
</style>

<script type="text/javascript">
     
$(function(){
	$(document).on("click","#dislike-btn",function(){
		var certiImgNo = $(this).data("param");
		console.log(certiImgNo);
		
		$.ajax({
			url:"/challenge/challengeRest/getChallengeCertiImg?certiImgNo="+certiImgNo,
			method:"GET",
			dataType:"json",
			headers:{
				"Accept":"application/json",
				"content-Type":"application/json"
			},
			success:function(JSONData){
				if(JSONData.likeAndDislikeFlag == 1){
					alert("이미 좋아요를 하셨습니다.");
					return;
				}else if(JSONData.likeAndDislikeFlag == 2){
					
					$.ajax({
						url:"/challenge/challengeRest/deleteChallengeReviewLike",
						method:"POST",
						dataType:"json",
						headers:{
							"Accept":"application/json",
							"Content-Type":"application/json"
						},
						data:JSON.stringify({
							certiImgNo:certiImgNo,
							status:"4"
						}),
						success:function(JSONData){
							$("#review"+certiImgNo+" > p").remove();
	        				var displayView = "";
	        				console.log(JSONData.like);
	        				if(JSONData.likeAndDislikeFlag == 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#75bdff; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" style=\"color:#75bdff\" >"+JSONData.like+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" >"+JSONData.like+"<p>";
	                        }
	                        if(JSONData.likeAndDislikeFlag == 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#ff6e63; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\" style=\"color:#ff6e63\">"+JSONData.dislike+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\">"+JSONData.dislike+"<p>";
	                        }
	                        
	                        $("#review"+certiImgNo).html(displayView);
						}
					});
					
				}else if(JSONData.likeAndDislikeFlag == null){
					$.ajax({
	        			url:"/challenge/challengeRest/addChallengeReviewDislike",
	        			method:"POST",
	        			dataType:"json",
	        			headers:{
	        				"Accept":"application/json",
	        				"content-Type":"application/json"
	        			},
	        			data:JSON.stringify({
	        				certiImgNo:certiImgNo,
	        				status:"2"
	        			}),
	        			success:function(JSONData){
	        				$("#review"+certiImgNo+" > p").remove();
	        				var displayView = "";
	        				console.log(JSONData.like);
	        				if(JSONData.likeAndDislikeFlag == 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#75bdff; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" style=\"color:#75bdff\" >"+JSONData.like+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" >"+JSONData.like+"<p>";
	                        }
	                        if(JSONData.likeAndDislikeFlag == 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#ff6e63; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\" style=\"color:#ff6e63\">"+JSONData.dislike+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\">"+JSONData.dislike+"<p>";
	                        }
	                        
	                        $("#review"+certiImgNo).html(displayView);
	        			}
	        		});
				}
			}
		});
	});
});




$(function(){
	$(document).on("click","#like-btn",function(){
		var certiImgNo = $(this).data("param");
		console.log(certiImgNo);
		
		
		$.ajax({
			url:"/challenge/challengeRest/getChallengeCertiImg?certiImgNo="+certiImgNo,
			method:"GET",
			dataType:"json",
			headers:{
				"Accept":"application/json",
				"content-Type":"application/json"
			},
			success:function(JSONData){
				if(JSONData.likeAndDislikeFlag == 1){
					$.ajax({
						url:"/challenge/challengeRest/deleteChallengeReviewLike",
						method:"POST",
						dataType:"json",
						headers:{
							"Accept":"application/json",
							"Content-Type":"application/json"
						},
						data:JSON.stringify({
							certiImgNo:certiImgNo,
							status:"3"
						}),
						success:function(JSONData){
							$("#review"+certiImgNo+" > p").remove();
	        				var displayView = "";
	        				console.log(JSONData.like);
	        				if(JSONData.likeAndDislikeFlag == 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#75bdff; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" style=\"color:#75bdff\" >"+JSONData.like+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" >"+JSONData.like+"<p>";
	                        }
	                        if(JSONData.likeAndDislikeFlag == 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#ff6e63; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\" style=\"color:#ff6e63\">"+JSONData.dislike+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\">"+JSONData.dislike+"<p>";
	                        }
	                        
	                        $("#review"+certiImgNo).html(displayView);
						}
					});
					
					
				}else if(JSONData.likeAndDislikeFlag == 2){
					alert("이미 싫어요를 하셨습니다.");
					return;
				}else if(JSONData.likeAndDislikeFlag == null){
					
					$.ajax({
	        			url:"/challenge/challengeRest/addChallengeReviewLike",
	        			method:"POST",
	        			dataType:"json",
	        			headers:{
	        				"Accept":"application/json",
	        				"content-Type":"application/json"
	        			},
	        			data:JSON.stringify({
	        				certiImgNo:certiImgNo,
	        				status:"1"
	        			}),
	        			success:function(JSONData){
	        				$("#review"+certiImgNo+" > p").remove();
	        				var displayView = "";
	        				console.log(JSONData.like);
	        				if(JSONData.likeAndDislikeFlag == 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#75bdff; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" style=\"color:#75bdff\" >"+JSONData.like+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 1){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
	                      	  "&nbsp;"+
	                      	  "<p class=\"font-size\" id=\"like\" >"+JSONData.like+"<p>";
	                        }
	                        if(JSONData.likeAndDislikeFlag == 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"color:#ff6e63; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\" style=\"color:#ff6e63\">"+JSONData.dislike+"<p>";
	                        }else if(JSONData.likeAndDislikeFlag != 2){
	                      	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+JSONData.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
	                            "&nbsp;"+
	                            "<p class=\"font-size\" id=\"dislike\">"+JSONData.dislike+"<p>";
	                        }
	                        
	                        $("#review"+certiImgNo).html(displayView);
	        			}
	        		});
				}
			}
		});
		
		
		
		
	});
});
	

 $(function(){
    
    $(document).on("click","#comment-btn-close",function(){
       
       var certiImgNo = $(this).data("param");
       var newButton = "<button type=\"button\" id=\"comment-btn-open\" class=\"btn btn-default\" data-param = \""+certiImgNo+"\">댓글 펼치기/접기</button>"
       
       
       $("#comment"+certiImgNo+" > .comt").remove();
       $("center#com-btn"+certiImgNo).html(newButton);
       
    });
    
    
    
    $(document).on("click","#comment-btn-open",function(){
       var certiImgNo = $(this).data("param");
       var user = "${user.userEmail}";
       $.ajax({
          url:"/challenge/challengeRest/getChallengeCommentList?certiImgNo="+certiImgNo+"&currentPage=1",
          method:"GET",
          dataType:"json",
          headers:{
             "accept":"application/json",
             "content-Type":"application/json"   
             },
             success:function(JSONData){
                console.log($("center#com-btn"+certiImgNo+" > #comment-btn-open"));
                $("center#com-btn"+certiImgNo+" > #comment-btn-open").remove();
                newButton = "<button type=\"button\" id=\"comment-btn-close\"class=\"btn btn-default\" data-param = \""+certiImgNo+"\">댓글 펼치기/접기</button>"
                $("center#com-btn"+certiImgNo).html(newButton);
                displayView = "<div id=\"comment-list"+certiImgNo+"\" class=\"comt\">"+
                "<div id=\"input-comment\">"+
                "<textarea name=\"comment\" style=\"width:640px; resize: none;\" id=\"input-comt"+certiImgNo+"\"></textarea>"+
                "<button type=\"button\" id=\"comment-input-btn\" class=\"btn btn-default\" data-param=\""+certiImgNo+"\">입력</button>"+
                "</div>"+
                "<input type=\"hidden\" name=\"currnetPage"+certiImgNo+"\" value=\"1\">"+
                "<table id=\"comments\" class=\"table table-striped\">";
                $(JSONData).each(function(){
                   console.log(this);
                   displayView = displayView + "<tr>";
                   displayView = displayView + "<td class=\"nick-comment\">"+this.nick+" : <textarea class=\"text-comment\" readonly>"+this.comment+"</textarea></td>";
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-update\" id=\"comment-update-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[수정]</a></td>";   
                   }
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   ;   
                   } 
                   if(user === "admin@io.com"){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   ;   
                   }
                   displayView = displayView + "</tr>";
                   displayView = displayView + "<tr id=\"update-row"+this.reviewNo+"\">";
                   displayView = displayView + "</tr>";
                })
                displayView = displayView + "<tr id=\"more-view"+certiImgNo+"\">";
                displayView = displayView + "<td class=\"mo\"><a class=\"comment-update\" id=\"more-comment\" href=\"javascript:;\" data-certiimgno=\""+certiImgNo+"\">댓글 더 보기</a></td>";
                displayView = displayView + "</tr>";
                displayView = displayView + "</table>";
                displayView = displayView + "</div>";
                $("#comment"+certiImgNo).html(displayView);
             }
          });
          
       });
    
       $(document).on("click","#more-comment",function(){
          var certiImgNo = $(this).data("certiimgno");
          var currentPage = Number($("input[name='currnetPage"+certiImgNo+"']").val())+1;
          $("input[name='currnetPage"+certiImgNo+"']").remove();
          var newPage = "<input type=\"hidden\" name=\"currnetPage"+certiImgNo+"\" value=\""+currentPage+"\">";
          $("#comment-list"+certiImgNo).append(newPage);
          var user = "${user.userEmail}";
          console.log(currentPage);
          
          
          $.ajax({
             url:"/challenge/challengeRest/getChallengeCommentList?certiImgNo="+certiImgNo+"&currentPage="+currentPage,
             method:"GET",
             dataType:"json",
             headers:{
                "Accept":"application/json",
                "content-Type":"application/json"
             },
             success:function(JSONData){
                $("#more-view"+certiImgNo+" > td.mo").remove();
                displayView = "<div id=\"comment-list"+certiImgNo+"\" class=\"comt\">"+
                "<input type=\"hidden\" name=\"currnetPage"+certiImgNo+"\" value=\""+currentPage+"\">"+
                "<table id=\"comments\" class=\"table table-striped\">";
                $(JSONData).each(function(){
                   console.log(this);
                   displayView = displayView + "<tr>";
                   displayView = displayView + "<td class=\"nick-comment\">"+this.nick+" : <textarea class=\"text-comment\" readonly>"+this.comment+"</textarea></td>";
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-update\" id=\"comment-update-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[수정]</a></td>";   
                   }
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   ;   
                   } 
                   if(user === "admin@io.com"){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   ;   
                   }
                   displayView = displayView + "</tr>";
                   displayView = displayView + "<tr id=\"update-row"+this.reviewNo+"\">";
                   displayView = displayView + "</tr>";
                })
                displayView = displayView + "<tr id=\"more-view"+certiImgNo+"\">";
                displayView = displayView + "<td class=\"mo\"><a class=\"comment-update\" id=\"more-comment\" href=\"javascript:;\" data-certiimgno=\""+certiImgNo+"\">댓글 더 보기</a></td>";
                displayView = displayView + "</tr>";
                displayView = displayView + "</table>";
                displayView = displayView + "</div>";
                $("#comment"+certiImgNo).append(displayView);
             }
             
          });
          
          
          
       });
    
       $(document).on("click","#comment-input-btn",function(){ //ajax처리해야됨.
          var certiImgNo = $(this).data("param");
          var commentDetail = $("#input-comt"+certiImgNo).val();
          var status = "0";
          var user = "${user.userEmail}";
          var currentPage = Number($("input[name='currnetPage"+certiImgNo+"']").val());
          $.ajax({
             url:"/challenge/challengeRest/addChallengeReview",
             method:"POST",
             data:JSON.stringify({
                certiImgNo:certiImgNo,
                comment:commentDetail,
                currentPage:currentPage,
                status:status
             }),
             dataType:"json",
                headers:{
                   "Accept":"application/json",
                   "content-Type":"application/json"
                },
             success:function(JSONData){
                $("#comment-list"+certiImgNo).remove();
                displayView = "<div id=\"comment-list"+certiImgNo+"\" class=\"comt\">"+
                "<div id=\"input-comment\">"+
                "<textarea name=\"comment\" style=\"width:640px; resize: none;\" id=\"input-comt"+certiImgNo+"\"></textarea>"+
                "<button type=\"button\" id=\"comment-input-btn\" class=\"btn btn-default\" data-param=\""+certiImgNo+"\">입력</button>"+
                "</div>"+
                "<input type=\"hidden\" name=\"currnetPage"+certiImgNo+"\" value=\""+currentPage+"\">"+
                "<table id=\"comments\" class=\"table table-striped\">";
                $(JSONData).each(function(){
                   displayView = displayView + "<tr>";
                   displayView = displayView + "<td class=\"nick-comment\">"+this.nick+" : <textarea class=\"text-comment\" readonly>"+this.comment+"</textarea></td>";
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-update\" id=\"comment-update-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[수정]</a></td>";      
                   }
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   
                   }

                   if(user === "admin@io.com"){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   
                   }
                   displayView = displayView + "</tr>";
                   displayView = displayView + "<tr id=\"update-row"+this.reviewNo+"\">";
                   displayView = displayView + "</tr>";
                })
                displayView = displayView + "<tr id=\"more-view"+certiImgNo+"\">";
                displayView = displayView + "<td class=\"mo\"><a class=\"comment-update\" id=\"more-comment\" href=\"javascript:;\" data-certiimgno=\""+certiImgNo+"\">댓글 더 보기</a></td>";
                displayView = displayView + "</tr>";
                displayView = displayView + "</table>";
                displayView = displayView + "</div>";
                $("#comment"+certiImgNo).html(displayView);

             }
          });
          
       });
       
       $(document).on("click","#comment-update-id",function(){
          
          $("td.update-class").remove();
          var reviewNo = $(this).data("param");
          var certiImgNo = $(this).data("param2");
          displayView = "<td class=\"update-class\" id=\"update-input"+reviewNo+"\">"+
          "<textarea name=\"comment\" id=\"update-comt\"></textarea>"+
          "<button type=\"button\" id=\"comment-update-btn\" class=\"btn btn-default\" data-param=\""+reviewNo+"\" data-param2=\""+certiImgNo+"\">수정</button>"+
          "<button type=\"button\" id=\"update-cancel\" class=\"btn btn-default\">취소</button>"+
          "</td>";
          $("tr#update-row"+reviewNo).append(displayView);
       });
       
       
       $(document).on("click","#comment-update-btn",function(){
          var reviewNo = $(this).data("param");
          var certiImgNo = $(this).data("param2");
          var updateComment = $("#update-comt").val();
          var user = "${user.userEmail}";
          var currentPage = Number($("input[name='currnetPage"+certiImgNo+"']").val());
          
          $.ajax({
             url:"/challenge/challengeRest/updateChallengeReview",
             method:"POST",
             dataType:"json",
             headers:{
                "Accept":"application/json",
                "content-Type":"application/json"
             },
             data:JSON.stringify({
                reviewNo:reviewNo,
                comment:updateComment,
                currentPage:currentPage,
                certiImgNo:certiImgNo
             }),
             success:function(JSONData){
                $("#comment-list"+certiImgNo).remove();
                displayView = "<div id=\"comment-list"+certiImgNo+"\" class=\"comt\">"+
                "<div id=\"input-comment\">"+
                "<textarea name=\"comment\" style=\"width:640px; resize: none;\" id=\"input-comt"+certiImgNo+"\"></textarea>"+
                "<button type=\"button\" id=\"comment-input-btn\" class=\"btn btn-default\" data-param=\""+certiImgNo+"\">입력</button>"+
                "</div>"+
                "<input type=\"hidden\" name=\"currnetPage"+certiImgNo+"\" value=\""+currentPage+"\">"+
                "<table id=\"comments\" class=\"table table-striped\">";
                $(JSONData).each(function(){
                   displayView = displayView + "<tr>";
                   displayView = displayView + "<td class=\"nick-comment\">"+this.nick+" : <textarea class=\"text-comment\" readonly>"+this.comment+"</textarea></td>";
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-update\" id=\"comment-update-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[수정]</a></td>";      
                   }
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   
                   }

                   if(user === "admin@io.com"){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   
                   }
                   displayView = displayView + "</tr>";
                   displayView = displayView + "<tr id=\"update-row"+this.reviewNo+"\">";
                   displayView = displayView + "</tr>";
                })
                displayView = displayView + "<tr id=\"more-view"+certiImgNo+"\">";
                displayView = displayView + "<td class=\"mo\"><a class=\"comment-update\" id=\"more-comment\" href=\"javascript:;\" data-certiimgno=\""+certiImgNo+"\">댓글 더 보기</a></td>";
                displayView = displayView + "</tr>";
                displayView = displayView + "</table>";
                displayView = displayView + "</div>";
                $("#comment"+certiImgNo).html(displayView);

             }
          });
          
          
       });
       
       
       $(document).on("click","#comment-delete-id",function(){
          
          var reviewNo = $(this).data("param");
          var certiImgNo = $(this).data("param2");
          var user = "${user.userEmail}";
          var currentPage = Number($("input[name='currnetPage"+certiImgNo+"']").val());
          $.ajax({
             
             url:"/challenge/challengeRest/deleteChallengeReview",
             method:"POST",
             dataType:"json",
             headers:{
                "Accept":"application/json",
                "content-Type":"application/json"
             },
             data:JSON.stringify({
                reviewNo:reviewNo,
                certiImgNo:certiImgNo,
                currentPage:currentPage
             }),
             success:function(JSONData){
                $("#comment-list"+certiImgNo).remove();
                displayView = "<div id=\"comment-list"+certiImgNo+"\ class=\"comt\">"+
                "<div id=\"input-comment\">"+
                "<textarea name=\"comment\" style=\"width:640px; resize: none;\" id=\"input-comt"+certiImgNo+"\"></textarea>"+
                "<button type=\"button\" id=\"comment-input-btn\" class=\"btn btn-default\" data-param=\""+certiImgNo+"\">입력</button>"+
                "</div>"+
                "<input type=\"hidden\" name=\"currnetPage"+certiImgNo+"\" value=\""+currentPage+"\">"+
                "<table id=\"comments\" class=\"table table-striped\">";
                $(JSONData).each(function(){
                   displayView = displayView + "<tr>";
                   displayView = displayView + "<td class=\"nick-comment\">"+this.nick+" : <textarea class=\"text-comment\" readonly>"+this.comment+"</textarea></td>";
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-update\" id=\"comment-update-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[수정]</a></td>";      
                   }
                   if(this.email != user){
                      displayView = displayView + "<td></td>";
                   }else if(this.email == user){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   
                   }

                   if(user === "admin@io.com"){
                      displayView = displayView + "<td><a href =\"javascript:;\" class=\"comment-delete\" id=\"comment-delete-id\" data-param=\""+this.reviewNo+"\" data-param2=\""+certiImgNo+"\">[삭제]</a></td>";   
                   }
                   displayView = displayView + "</tr>";
                   displayView = displayView + "<tr id=\"update-row"+this.reviewNo+"\">";
                   displayView = displayView + "</tr>";
                })
                displayView = displayView + "<tr id=\"more-view"+certiImgNo+"\">";
                displayView = displayView + "<td class=\"mo\"><a class=\"comment-update\" id=\"more-comment\" href=\"javascript:;\" data-certiimgno=\""+certiImgNo+"\">댓글 더 보기</a></td>";
                displayView = displayView + "</tr>";
                displayView = displayView + "</table>";
                displayView = displayView + "</div>";
                $("#comment"+certiImgNo).html(displayView);
             }
             
          });
          
       });
       
       
       
       
       $(document).on("click","#update-cancel",function(){
          
          $("td.update-class").remove();
          
       });
       
    });
     
     
     
     
     
     
$(function() {
 	$("#user").on("click",function(){
 		var email = $(this).data("param");
 		self.location="/user/getUserTarget?userEmail="+email;
 	});
 	$("img#profile").on("click",function(){
 		var email = $(this).data("param");
 		self.location="/user/getUserTarget?userEmail="+email;
 	});
 	
 	$("#cerit-img-delete").on("click",function(){
 		if(window.confirm("인증 이미지 삭제시 달성률에 적용되며.\n"+
						  "인증 가능한 날짜가 아니면 재 업로드가 불가능합니다.\n"+
						  "삭제 하시겠습니까?")){
 			$("form").attr("method","POST").attr("action","/challenge/deleteChallengeCertiImg").submit();
 		}
 	});
 });
 function fncDeleteChallengeCertiImg(){
	
 };
</script>

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/layout/toolbar.jsp" />
<form>
<div class="container">

<input type="hidden" name="userEmail" value="${certiImg.user.userEmail }">
<input type="hidden" name="certiImgNo" value="${certiImg.certiImgNo }">

	<div class="page-header">
		<p id="header">인증이미지 상세 조회</p>
	</div>
  <div class="thumbnail">
                  <img style="width:700px; height:700px; position:relative; top:30px;" src="/resources/images/uploadFiles/${certiImg.certiImg }" 
                  onerror="this.src='https://dummyimage.com/700x700/1af0d4/000000.gif'">
               <div class="caption">
                  <div id="user-info">
                     <img id="profile" data-param="${certiImg.user.userEmail }"
                     	src="/resources/images/uploadFiles/${certiImt.user.profileImg }"
                          onerror="this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'">
                    <p id="user" data-param="${certiImg.user.userEmail }">${certiImg.user.nick }</p>
                 </div>
                   <div class="info">
                      <p class="font-size">관심사 : ${certiImg.categName }<p>
                   </div>
                   <div id="bord"></div>
                   <div id="review">
                      <div class="like-dislike" id="review${certiImg.certiImgNo }">
                      	 <c:if test="${certiImg.likeAndDislikeFlag == 1}">
	                      	 <p class="like-dislike-model" id="like-btn" data-param="${certiImg.certiImgNo }" style="color:#75bdff; cursor:pointer;"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></p>
	                         &nbsp;
	                      	 <p class="font-size" id="like" style="color:#75bdff;">${certiImg.like }<p>
                      	 </c:if>
                      	 
                      	 <c:if test="${certiImg.likeAndDislikeFlag != 1}">
	                      	 <p class="like-dislike-model" id="like-btn" data-param="${certiImg.certiImgNo }" style="cursor:pointer;"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></p>
	                         &nbsp;
	                      	 <p class="font-size" id="like" >${certiImg.like }<p>
                      	 </c:if>
                         
                         <c:if test="${certiImg.likeAndDislikeFlag == 2}">
	                         <p class="like-dislike-model" id="dislike-btn" data-param="${certiImg.certiImgNo }" style="color:#ff6e63; cursor:pointer;"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></p>
	                         &nbsp;
	                         <p class="font-size" id="dislike" style="color:#ff6e63;">${certiImg.dislike }<p>
                         </c:if>
                        
                         <c:if test="${certiImg.likeAndDislikeFlag != 2}">
                         	 <p class="like-dislike-model" id="dislike-btn" data-param="${certiImg.certiImgNo }" style="cursor:pointer;"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></p>
	                         &nbsp;
	                         <p class="font-size" id="dislike">${certiImg.dislike }<p>
                         </c:if>
                      </div>
                      	 <c:if test="${sessionScope.user.userEmail == certiImg.user.userEmail }">
                         	 <p class="font-size" id="cerit-img-delete" data-param="${certiImg.certiImgNo }" data-param2="${certiImg.user.userEmail }" style="cursor: pointer;">[삭제]</p>
                         </c:if>
                   </div>
                   <center id="com-btn${certiImg.certiImgNo }">
                      <button type="button" id="comment-btn-open" class="btn btn-default" 
                      data-param="${certiImg.certiImgNo }">댓글 펼치기/접기</button> 
                 </center>
                 <div id="comment${certiImg.certiImgNo }">
                   </div>
               </div>
             </div>
             </div>
   </form>
</body>
</html>