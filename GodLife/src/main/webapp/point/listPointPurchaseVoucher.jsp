<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>상품권 구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script type="text/javascript">
	

function fncGetList(currentPage) {
	
	$("#currentPage").val(currentPage)
   
	$("form").attr("method" , "POST").attr("action" , "/purchase/listPurchase").submit();
}
$(function() {
	 
	$( ".ct_list_pop td:nth-child(1)" ).on("click" , function() {
		self.location ="/purchase/getPurchase?tranNo="+$(this).children("input").val();
	});
	
	$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
			self.location ="/user/getUser?userId="+$(this).children("input").val();
	});
	
	$( ".ct_list_pop td:nth-child(5)" ).on("click" , function() {
		var tranNo = $(this).children("input").val().trim();
		$.ajax({
			url : "/purchase/json/getPurchase/" + tranNo,
			method : "GET",
			dataType : "json",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : function(JSONData, status) {

				//alert(status);
				//alert("JSONData : \n"+JSONData.tranNo);

				var displayValue = "<h3>" + 
				"제품번호 : "+JSONData.purchaseProd.prodNo + "<br/>" +
				"구매자 이름 : "+JSONData.purchaseProd.receiverName + "<br/>" +
				"재고 : "+ JSONData.quantity + "<br/>" +
				"구매자 연락처 : "+ JSONData.receiverPhone + "<br/>" +
				"구매자 주소 : "+ JSONData.divyAddr + "<br/>" +
				"구매 요청 사항: "+ JSONData.divyRequest + "<br/>" +
				"배송희망일: "+ JSONData.divyDate + "<br/>" +
				"주문일: "+ JSONData.orderDate + "<br/>" +
				"상품이미지 : <img src=/images/uploadFiles/"+ JSONData.purchaseProd.fileName+ "/><br/>" 
				"</h3>";

				//alert(displayValue);
				$("h3").remove();
				$("#" + tranNo + "").html(displayValue);
			}
		});
	});
	
	$(".ct_list_pop td:nth-child(11):contains('물건도착')" ).on("click",function() {
		//alert( $(this).children("input").val() );
		var tranNo =$(this).children("input").val().trim();
		self.location="/purchase/updateTranCode?tranNo="+$(this).children("input").val()+"&tranCode=3";
	});
	

	
$( ".ct_list_pop td:nth-child(1)" ).css("color" , "#5F04B4");
$( ".ct_list_pop td:nth-child(3)" ).css("color" , "#4C0B5F");
$( ".ct_list_pop td:nth-child(5)" ).css("color" , "#610B5E");
$( ".ct_list_pop td:contains('물건도착')" ).css("color" , "#B4045F");
//$( ".ct_list_pop td:nth-child(9)" ).css("color" , "red");
		
$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");

});
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" >

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">상품권 구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">주문번호<br></td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">productName<br></td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">UniqueNo</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">regDate</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">msg</td>
		<td class="ct_line02"></td>
		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var = "i" value = "0"/>
	<c:forEach var ="purchase" items ="${list }">
		<c:set var="i"  value = "${i+1 }"/>
		<tr class="ct_list_pop">
		<td align="center">
		<input type="hidden" name="purchaseNo"  value="${point.purchaseNo }" />
		${point.purchaseNo}
		</td>
		<td></td>
		<td align="left">
		<input type="hidden" name="userEmail"  value="${point.userEmail}" />
		${point.userEmail}
		</td>
		<td></td>
		<td align="left">${point.productName}</td>
		<td></td>
		<td align="left">${point.voucherUniqueNo}</td>
		<td></td>
		<td align="left">${point.regDate}</td>
		<td></td>
		<td align="left" >msg</td>
	</tr>
	
	</c:forEach>
</table>	
	
<!--  페이지 Navigator 시작 -->

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
	
			<jsp:include page="../common/pageNavigator_new.jsp"/>	
			
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>

</body>
</html>