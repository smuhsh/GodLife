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

<script type="text/javascript">
      $(document).ready(function () {
         
           $(document).scroll(function async() {
             var maxHeight = $(document).height();
             var currentScroll = $(window).scrollTop() + $(window).height();
            
              if(maxHeight <= currentScroll + 100) {
                var page = Number($("input[name='page']").val())+1;
                dis="<div id=\"pages\"><input type=\"hidden\" name=\"page\" value=\""+page+"\"></div>";
               $("#pages").remove();
               $("#flat").html(dis);
                  var url;
                  var certiImgOpt = $("input[name='certiImgOpt']").val();
                  var orderCondition = $("select[name='orderCondition']").val();
                  var searchCondition = $("select[name='searchCondition']").val();
               if(${empty certiImgOpt}){
                 url = "/challenge/challengeRest/getChallengeCertiImgList?pageNo="+page;
               }else{
                 url = "/challenge/challengeRest/getChallengeCertiImgList?pageNo="+page+"&certiImgOpt="+certiImgOpt;
               }
               
               if(orderCondition != 0){
            	   url = url + "&orderCondition="+orderCondition;
               }
               if(searchCondition != 3){
            	   url = url + "&searchCondition="+searchCondition;
               }
               
                  $.ajax({
                     url:url,
                     method:"GET",
                     dataType:"json",
                     headers:{
                        "Accept":"application/json",
                        "content-Type":"application/json"
                     },
                     success:function(JSONData){
                           $(JSONData).each(function(){
                              var user = "${user.userEmail}";
                              console.log(this.certiImg);
                              var displayView = "<div class=\"thumbnail\">"+
                              "<img style=\"width:700px; height:700px; position:relative; top:30px;\"data-param=\""+this.user.userEmail+"\" src=\"/resources/images/uploadFiles/"+this.certiImg+
                              "\" onerror=\"this.src='https://dummyimage.com/700x700/1af0d4/000000.gif'\">"+
                              "<div class=\"caption\">"+
                              "<div id=\"user-info\">"+
                              "<img id=\"profile\" src=\"/resources/images/uploadFiles/"+this.user.profileImg+"\""+
                              "onerror=\"this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'\">"+
                              "<p id=\"user\" data-param=\""+this.user.userEmail+"\">"+this.user.nick+"</p>"+
                              "</div>"+
                              "<div class=\"info\">"+
                              "<p class=\"font-size\">관심사 : "+this.categName+"<p>"+
                              "</div>"+
                              "<div id=\"bord\"></div>"+
                              "<div class=\"like-dislike\" id=\"review"+this.certiImgNo+"\">";
                              if(this.likeAndDislikeFlag == 1){
                            	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+this.certiImgNo+"\" style=\"color:#75bdff; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
                            	  "&nbsp;"+
                            	  "<p class=\"font-size\" id=\"like\" style=\"color:#75bdff\" >"+this.like+"<p>";
                              }else if(this.likeAndDislikeFlag != 1){
                            	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+this.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
                            	  "&nbsp;"+
                            	  "<p class=\"font-size\" id=\"like\" >"+this.like+"<p>";
                              }
                              if(this.likeAndDislikeFlag == 2){
                            	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+this.certiImgNo+"\" style=\"color:#ff6e63; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
                                  "&nbsp;"+
                                  "<p class=\"font-size\" id=\"dislike\" style=\"color:#ff6e63\">"+this.dislike+"<p>";
                              }else if(this.likeAndDislikeFlag != 2){
                            	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+this.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
                                  "&nbsp;"+
                                  "<p class=\"font-size\" id=\"dislike\">"+this.dislike+"<p>";
                              }
                              displayView = displayView +"</div>";
                              if(${!empty certiImgOpt}){
                            	  displayView = displayView + "<p class=\"font-size\" id=\"cerit-img-delete\" data-param=\""+this.certiImgNo+"\" data-param2=\""+this.user.userEmail+"\" style=\"cursor: pointer;\">[삭제]</p>";
                              }
                              displayView = displayView +
                              "<center id=\"com-btn"+this.certiImgNo+"\">"+
                              "<button type=\"button\" id=\"comment-btn-open\"class=\"btn btn-default\" data-param = \""+this.certiImgNo+"\">댓글 펼치기/접기</button>"+
                              "</center>"+
                              "<div id=\"comment"+this.certiImgNo+"\">"+
                               "<div id=\"comment-list"+this.certiImgNo+"\">"+
                               "</div>"+
                                "</div>"+
                              "</div>"+
                              "</div>";
                              $("#infinit-scroll").append(displayView);
                           })
                        }
                     });
                           
                  }
               
             })
           });
         
      
      	$(function(){
      		$(document).on("click","#cerit-img-delete",function(){
      			var certiImgNo = $(this).data("param");
        		console.log(certiImgNo);
        		var currentPage = $("input[name='page']").val();
        		var certiImgOpt = "${certiImgOpt}";
        		var userEmail = $(this).data("param2");
        		console.log(certiImgOpt);
        		$.ajax({
        			url:"/challenge/challengeRest/deleteChallengeCertiImgList",
        			method:"POST",
        			dataType:"json",
        			headers:{
        				"Accept":"application/json",
						"Content-Type":"application/json"
        			},
        			data:JSON.stringify({
        				certiImgNo:certiImgNo,
        				currentPage:currentPage,
        				certiImgOpt,certiImgOpt,
        				userEmail:userEmail
        			}),
        			success:function(JSONData){
                        $(".thumbnail").remove();
                        var displayView;
        				$(JSONData).each(function(){
                            var user = "${user.userEmail}";
                            console.log(this.certiImg);
                            displayView = "<div class=\"thumbnail\">"+
                            "<img style=\"width:700px; height:700px; position:relative; top:30px;\" src=\"/resources/images/uploadFiles/"+this.certiImg+
                            "\" onerror=\"this.src='https://dummyimage.com/700x700/1af0d4/000000.gif'\">"+
                            "<div class=\"caption\">"+
                            "<div id=\"user-info\">"+
                            "<img id=\"profile\" src=\"/resources/images/uploadFiles/"+this.user.profileImg+"\""+
                            "onerror=\"this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'\">"+
                            "<p id=\"user\">"+this.user.nick+"</p>"+
                            "</div>"+
                            "<div class=\"info\">"+
                            "<p class=\"font-size\">관심사 : "+this.categName+"<p>"+
                            "</div>"+
                            "<div id=\"bord\"></div>"+
                            "<div class=\"like-dislike\" id=\"review"+this.certiImgNo+"\">";
                            if(this.likeAndDislikeFlag == 1){
                          	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+this.certiImgNo+"\" style=\"color:#75bdff; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
                          	  "&nbsp;"+
                          	  "<p class=\"font-size\" id=\"like\" style=\"color:#75bdff\" >"+this.like+"<p>";
                            }else if(this.likeAndDislikeFlag != 1){
                          	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"like-btn\" data-param=\""+this.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span></p>"+
                          	  "&nbsp;"+
                          	  "<p class=\"font-size\" id=\"like\" >"+this.like+"<p>";
                            }
                            if(this.likeAndDislikeFlag == 2){
                          	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+this.certiImgNo+"\" style=\"color:#ff6e63; cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
                                "&nbsp;"+
                                "<p class=\"font-size\" id=\"dislike\" style=\"color:#ff6e63\">"+this.dislike+"<p>";
                            }else if(this.likeAndDislikeFlag != 2){
                          	  displayView = displayView + "<p class=\"like-dislike-model\" id=\"dislike-btn\" data-param=\""+this.certiImgNo+"\" style=\"cursor:pointer;\"><span class=\"glyphicon glyphicon-thumbs-down\" aria-hidden=\"true\"></span></p>"+
                                "&nbsp;"+
                                "<p class=\"font-size\" id=\"dislike\">"+this.dislike+"<p>";
                            }
                            displayView = displayView +"</div>";
                            if(${!empty certiImgOpt}){
                          	  displayView = displayView + "<p class=\"font-size\" id=\"cerit-img-delete\" data-param=\""+this.certiImgNo+"\" data-param2=\""+this.user.userEmail+"\" style=\"cursor: pointer;\">[삭제]</p>";
                            }
                            displayView = displayView +
                            "<center id=\"com-btn"+this.certiImgNo+"\">"+
                            "<button type=\"button\" id=\"comment-btn-open\"class=\"btn btn-default\" data-param = \""+this.certiImgNo+"\">댓글 펼치기/접기</button>"+
                            "</center>"+
                            "<div id=\"comment"+this.certiImgNo+"\">"+
                             "<div id=\"comment-list"+this.certiImgNo+"\">"+
                             "</div>"+
                              "</div>"+
                            "</div>"+
                            "</div>";
                            $("#infinit-scroll").append(displayView);
        			})
        				
        			}
        		});
      		});
      	});
      
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
            
         
         $(function(){
        	$(document).on("click","#user",function(){
        		var email = $(this).data("param");
        		self.location="/user/getUserTarget?userEmail="+email;
        	});
        	
        	$(document).on("click","img#profile",function(){
        		var email = $(this).data("param");
        		self.location="/user/getUserTarget?userEmail="+email;
        	});
         
         	
        	$("button#search").on("click",function(){
	        	  var orderCondition = $("select[name='orderCondition']").val();
	              var searchCondition = $("select[name='searchCondition']").val();
        		  var url;
        		
        		  if(${empty certiImgOpt}){
                    url = "/challenge/listChallengeCertiImg";
                    url = url + "?orderCondition="+orderCondition;
      			  	url = url + "&searchCondition="+searchCondition;
                  }else{
                    url = "/challenge/listChallengeCertiImg?certiImgOpt="+certiImgOpt;
                    url = url + "&orderCondition="+orderCondition;
      			  	url = url + "&searchCondition="+searchCondition;
                  }
        		
        		self.location = url;
        	});
         
         
         });
        
 //////////////////////////////////////////////////////////////////////////////////////////
         
         
</script>

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/layout/toolbar.jsp" />

  <input type="hidden" name="certiImgOpt" value="${certiImgOpt }">
   <div class="container">
   						
      <div class="page-header">
      					<div class="input-group" id="search-opt">
						  		<div class="form-group">
								    <select class="form-control" name="orderCondition" >
								    	<option value="0">관심사</option>
										<option value="1" ${search.orderCondition == 1 ? "selected" : "" }>운동</option>
										<option value="2" ${search.orderCondition == 2 ? "selected" : ""}>식습관</option>
										<option value="3" ${search.orderCondition == 3 ? "selected" : "" }>공부</option>
										<option value="4" ${search.orderCondition == 4 ? "selected" : ""}>취미</option>
										<option value="5" ${search.orderCondition == 5 ? "selected" : "" }>생활</option>
									</select>
								 </div>
								  &nbsp;
							  	<div class="form-group">
								    <select class="form-control" name="searchCondition" >
								    	<option value="3">정렬 옵션</option>
										<option value="1" ${search.searchCondition == "1" ? "selected" : "" }>신규</option>
										<option value="2" ${search.searchCondition == "2" ? "selected" : ""}>인기</option>
									</select>
								 </div>
								  &nbsp;
						  		<button class="btn btn-default" id="search">검색</button>
						 	 </div>
        <c:if test="${certiImgOpt == 'my' }">
           <p id="header">내가 인증한 이미지 목록</p>
        </c:if>
        <c:if test="${empty certiImgOpt }">
            <p id="header">인증 이미지 목록</p>
        </c:if>
        <c:if test="${certiImgOpt != 'my' }">
            <c:if test="${!empty certiImgOpt }">
           		 <p id="header">${certiImgOpt}님의 인증이미지</p>
        	</c:if>
        </c:if>
      </div>
   
      <div id="infinit-scroll">
         <div id="flat">
            <div id="pages">
               <input type="hidden" name="page" value="${page }">
            </div>
         </div>
      <c:forEach var="certiImg" items="${certiImgList }">
            
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
                      	 <c:if test="${certiImgOpt == 'my'}">
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

      </c:forEach>
      </div>   
   </div>
   
</body>
</html>