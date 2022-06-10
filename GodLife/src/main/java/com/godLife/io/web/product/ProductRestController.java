package com.godLife.io.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Product;
import com.godLife.io.service.product.ProductService;





@RestController
//jason data를 요구하는 애들만 처리할 용도
@RequestMapping("/product/*")
public class ProductRestController {

	
///////////////////////2022.04.22 (금)//////////////////////////////////
//@RequestMapping("/addProduct.do")
//1.1 json이 붙어 있으면 Data만 요청을 하는 것 => restController에 진입을 하겠다는 말
//밑에 String은 model& View전략과 String 전략을 쓴것 중 String 전략을 쓴것이다
@RequestMapping( value="json/addProductCoupon", method=RequestMethod.POST )
public Product addProductCoupon( @RequestBody Product product) throws Exception {

//Data가 들어온지 혹인 하기 위해 json 넣어주기
System.out.println("/product/json/addProductCoupon : POST");
//Business Logic
productService.addProductCoupon(product);

return product;

}

@RequestMapping( value="json/addProductVoucher", method=RequestMethod.POST )
public Product addProductVoucher( @RequestBody Product product) throws Exception {

//Data가 들어온지 혹인 하기 위해 json 넣어주기
System.out.println("/product/json/addProductVoucher : POST");
//Business Logic
productService.addProductVoucher(product);

return product;

}

@RequestMapping( value="json/addProductPoint", method=RequestMethod.POST )
public Product addProductPoint( @RequestBody Product product) throws Exception {

//Data가 들어온지 혹인 하기 위해 json 넣어주기
System.out.println("/product/json/addProductPoint : POST");
//Business Logic
productService.addProductPoint(product);

return product;

}


////////////////////////2022.04.22 (금)/////////////////////////
//2.1 해동 센세와 함께만들어본 getProductTest_Codehaus()
///////////////////////////////////////////////////////

//F
@Autowired
@Qualifier("productServiceImpl") //package service.product.impl;를 wiring
ProductService productService;

@RequestMapping( value="json/getProductCoupon/{productNo}", method=RequestMethod.GET )
public Product getProductCoupon( @PathVariable int productNo ) throws Exception {
	
	System.out.println("/product/getProductCoupon : GET");
	
	//Business Logic
	Product product = productService.getProductCoupon(productNo);

	//밑에것은 UI를 보여줄 필요가 없다 그래서
	//return "forward:/product/getProduct.jsp"; 이것은 Navigation => 할 필요 없음
	
	//Data만 보내줌면 된다
	return product;
}


@RequestMapping( value="json/getProductVoucher/{productNo}", method=RequestMethod.GET )
public Product getProductVoucher( @PathVariable int productNo ) throws Exception {
	
	System.out.println("/product/getProductVoucher : GET");
	
	//Business Logic
	Product product = productService.getProductVoucher(productNo);

	//밑에것은 UI를 보여줄 필요가 없다 그래서
	//return "forward:/product/getProduct.jsp"; 이것은 Navigation => 할 필요 없음
	
	//Data만 보내줌면 된다
	return product;
}


@RequestMapping( value="json/getProductPoint/{productNo}", method=RequestMethod.GET )
public Product getProductPoint( @PathVariable int productNo ) throws Exception {
	
	System.out.println("/product/getProductPoint : GET");
	
	//Business Logic
	Product product = productService.getProductPoint(productNo);

	//밑에것은 UI를 보여줄 필요가 없다 그래서
	//return "forward:/product/getProduct.jsp"; 이것은 Navigation => 할 필요 없음
	
	//Data만 보내줌면 된다
	return product;
}

///////////////////////상품 수정//////////////////////////////////

	@RequestMapping( value="json/updateProductCoupon/{productNo}", method=RequestMethod.GET )
	public Product updateProductCoupon( @PathVariable int productNo ) throws Exception{	

		System.out.println("/product/json/updateProductCoupon : GET");
		//Business Logic
		Product product = productService.getProductCoupon(productNo);

		return product;
	}

	@RequestMapping( value="json/updateProductCoupon", method=RequestMethod.POST )
	public Product updateProductCoupon( @RequestBody Product product, HttpSession session) throws Exception{

	System.out.println("/product/json/updateProductCoupon : POST");
	
	//Business Logic
	productService.updateProductCoupon(product);
	
	if(session.getAttribute("product") != null) {
	
	int sessionId=((Product)session.getAttribute("product")).getProductNo();
	if(sessionId == (product.getProductNo())){
		session.setAttribute("product", product);
		}
	}
	return product;
}

	@RequestMapping( value="json/updateProductVoucher/{productNo}", method=RequestMethod.GET )
	public Product updateProductVoucher( @PathVariable int productNo ) throws Exception{	

		System.out.println("/product/json/updateProductVoucher : GET");
		//Business Logic
		Product product = productService.getProductVoucher(productNo);

		return product;
	}

	@RequestMapping( value="json/updateProductVoucher", method=RequestMethod.POST )
	public Product updateProductVoucher( @RequestBody Product product, HttpSession session) throws Exception{

	System.out.println("/product/json/updateProductVoucher : POST");
	
	//Business Logic
	productService.updateProductVoucher(product);
	
	if(session.getAttribute("product") != null) {
	
	int sessionId=((Product)session.getAttribute("product")).getProductNo();
	if(sessionId == (product.getProductNo())){
		session.setAttribute("product", product);
		}
	}
	return product;
}
	
	@RequestMapping( value="json/updateProductPoint/{productNo}", method=RequestMethod.GET )
	public Product updateProductPoint( @PathVariable int productNo ) throws Exception{	

		System.out.println("/product/json/updateProductPoint : GET");
		//Business Logic
		Product product = productService.getProductPoint(productNo);

		return product;
	}

	@RequestMapping( value="json/updateProductPoint", method=RequestMethod.POST )
	public Product updateProductPoint( @RequestBody Product product, HttpSession session) throws Exception{

	System.out.println("/product/json/updateProductPoint : POST");
	
	//Business Logic
	productService.updateProductPoint(product);
	
	if(session.getAttribute("product") != null) {
	
	int sessionId=((Product)session.getAttribute("product")).getProductNo();
	if(sessionId == (product.getProductNo())){
		session.setAttribute("product", product);
		}
	}
	return product;
}
	
	
///////////////////////2022.04.27//////////////////////////////////
//@RequestMapping("/listProduct.do")
//public String listProduct( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

@Value("#{commonProperties['pageUnit']}")
//@Value("#{commonProperties['pageUnit'] ?: 3}")
int pageUnit;

@Value("#{commonProperties['pageSize']}")
//@Value("#{commonProperties['pageSize'] ?: 2}")
int pageSize;



@RequestMapping( value="json/getProductCouponList" )
public Map getProductCouponList( @RequestBody Search search , HttpServletRequest request) throws Exception{

	System.out.println("/product/json/getProductCouponList : GET / POST");
	
	if(search.getCurrentPage() ==0 ){
		search.setCurrentPage(1);
	}
	search.setPageSize(pageSize);
	
	// Business logic 수행
	Map<String , Object> map = productService.getProductCouponList(search);
	
	Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	System.out.println(resultPage);
	
	// Model 과 View 연결
//	model.addAttribute("list", map.get("list"));
//	model.addAttribute("resultPage", resultPage);
//	model.addAttribute("search", search);
	map.put("list", map.get("list"));
	map.put("resultPage", resultPage);
	map.put("search", search);
	
//	return "forward:/product/listProduct.jsp";
	return map;
}


@RequestMapping( value="json/getProductVoucherList" )
public Map getProductVoucherList( @RequestBody Search search , HttpServletRequest request) throws Exception{

	System.out.println("/product/json/getProductVoucherList : GET / POST");
	
	if(search.getCurrentPage() ==0 ){
		search.setCurrentPage(1);
	}
	search.setPageSize(pageSize);
	
	// Business logic 수행
	Map<String , Object> map = productService.getProductVoucherList(search);
	
	Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	System.out.println(resultPage);
	
	// Model 과 View 연결
//	model.addAttribute("list", map.get("list"));
//	model.addAttribute("resultPage", resultPage);
//	model.addAttribute("search", search);
	map.put("list", map.get("list"));
	map.put("resultPage", resultPage);
	map.put("search", search);
	
//	return "forward:/product/listProduct.jsp";
	return map;
}


@RequestMapping( value="json/getProductPointList" )
public Map getProductPointList( @RequestBody Search search , HttpServletRequest request) throws Exception{

	System.out.println("/product/json/getProductPointList : GET / POST");
	
	if(search.getCurrentPage() ==0 ){
		search.setCurrentPage(1);
	}
	search.setPageSize(pageSize);
	
	// Business logic 수행
	Map<String , Object> map = productService.getProductPointList(search);
	
	Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
	System.out.println(resultPage);
	
	// Model 과 View 연결
//	model.addAttribute("list", map.get("list"));
//	model.addAttribute("resultPage", resultPage);
//	model.addAttribute("search", search);
	map.put("list", map.get("list"));
	map.put("resultPage", resultPage);
	map.put("search", search);
	
//	return "forward:/product/listProduct.jsp";
	return map;
}







/////////////////////////////////////////////////////////////////////////////	
	@RequestMapping( value="json/deleteProductCoupon", method=RequestMethod.POST )
	public void deleteProductCoupon( @PathVariable Product productNo) throws Exception {
	
	//Data가 들어온지 혹인 하기 위해 json 넣어주기
	System.out.println("/product/json/deleteProductCoupon : POST");
	//Business Logic
	productService.deleteProductCoupon(productNo);
	
	}

	@RequestMapping( value="json/deleteProductVoucher", method=RequestMethod.POST )
	public void deleteProductVoucher( @PathVariable Product productNo) throws Exception {
	
	//Data가 들어온지 혹인 하기 위해 json 넣어주기
	System.out.println("/product/json/deleteProductVoucher : POST");
	//Business Logic
	productService.deleteProductVoucher(productNo);
	
	}
	
	@RequestMapping( value="json/deleteProductPoint", method=RequestMethod.POST )
	public void deleteProductPoint( @RequestBody Product productNo, HttpSession session) throws Exception {
	
	//Data가 들어온지 혹인 하기 위해 json 넣어주기
	System.out.println("/product/json/deleteProductPoint : POST");
	//Business Logic
	productService.deleteProductPoint(productNo);
	
	}






}

