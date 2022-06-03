package com.godLife.io.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Product;
import com.godLife.io.service.product.ProductService;



@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

////////////////////////신규 쿠폰 상품 등록 Get, Post 방식///////////////////////////////	
	@RequestMapping( value="addProductCoupon", method=RequestMethod.GET )
	public String addProductCoupon() throws Exception {

		System.out.println("/product/addProductCouponView : GET");
		
		return "/product/addProductCouponView.jsp";

	}

	@RequestMapping( value="addProductCoupon", method=RequestMethod.POST )
	public String addProductCoupon( @ModelAttribute("product") Product product) throws Exception {
		
		System.out.println("/product/addProductCoupon : POST");
		//Business Logic
		productService.addProductCoupon(product);

		return "forward:/product/addProductCouponView.jsp";
	}
////////////////////////신규 쿠폰 상품 등록 Get, Post 방식/////////////////////////////////
////////////////////////신규 상품권 상품 등록 Get, Post 방식/////////////////////////////////
	@RequestMapping( value="addProductVoucher", method=RequestMethod.GET )
	public String addProductVoucher() throws Exception {

		System.out.println("/product/addProductVoucherView : GET");
		
		return "/product/addProductVoucherView.jsp";

	}

	@RequestMapping( value="addProductCoupon", method=RequestMethod.POST )
	public String addProductVoucher( @ModelAttribute("product") Product product) throws Exception {
		
		System.out.println("/product/addProductVoucher : POST");
		//Business Logic
		productService.addProductCoupon(product);

		return "forward:/product/addProductVoucherView.jsp";
	}
////////////////////////신규 상품권 상품 등록 Get, Post 방식/////////////////////////////////
////////////////////////신규 포인트 상품 등록 Get, Post 방식/////////////////////////////////
	@RequestMapping( value="addProductPoint", method=RequestMethod.GET )
	public String addProductPoint() throws Exception {

		System.out.println("/product/addProductPointView : GET");
		
		return "/product/addProductPointView.jsp";

	}

	@RequestMapping( value="addProductPoint", method=RequestMethod.POST )
	public String addProductPoint( @ModelAttribute("product") Product product) throws Exception {
		
		System.out.println("/product/addProductPoint : POST");
		//Business Logic
		productService.addProductCoupon(product);

		return "forward:/product/addProductPointView.jsp";
	}
////////////////////////신규 포인트 상품 등록 Get, Post 방식/////////////////////////////////	
////////////////////////쿠폰 상세 내용 조회 Get 방식/////////////////////////////////	
	@RequestMapping( value="getProductCoupon", method=RequestMethod.GET )
	public String getProductCoupon( @RequestParam("productNo") int productNo , Model model ) throws Exception {
		
		System.out.println("/product/getProductCoupon : GET");
		
		//Business Logic
		Product product = productService.getProductCoupon(productNo);
		// Model View 
		model.addAttribute("product", product);
		
		return "forward:/product/getProductCoupon.jsp";
	}
////////////////////////쿠폰 상세 내용 조회 Get 방식/////////////////////////////////	
////////////////////////상품권 상세 내용 조회 Get 방식/////////////////////////////////	
	@RequestMapping( value="getProductVoucher", method=RequestMethod.GET )
	public String getProductVoucher( @RequestParam("productNo") int productNo , Model model ) throws Exception {
	
	System.out.println("/product/getProductVoucher : GET");
	
	//Business Logic
	Product product = productService.getProductVoucher(productNo);
	// Model View 
	model.addAttribute("product", product);
	
	return "forward:/product/getProductVoucher.jsp";
	}
////////////////////////상품권 상세 내용 조회 Get 방식/////////////////////////////////	
////////////////////////포인트 상세 내용 조회 Get 방식/////////////////////////////////	
	@RequestMapping( value="getProductPoint", method=RequestMethod.GET )
	public String getProductPoint( @RequestParam("productNo") int productNo , Model model ) throws Exception {
	
	System.out.println("/product/getProductPoint : GET");
	
	//Business Logic
	Product product = productService.getProductPoint(productNo);
	// Model View 
	model.addAttribute("product", product);
	
	return "forward:/product/getProductPoint.jsp";
	}
////////////////////////포인트 상세 내용 조회 Get 방식/////////////////////////////////		
////////////////////////쿠폰 수정 Get, Post 방식/////////////////////////////////		
	@RequestMapping( value="updateProductCoupon", method=RequestMethod.GET )
	public String updateProductCoupon( @RequestParam("productNo") int productNo , Model model ) throws Exception{	

		System.out.println("/product/updateProductCoupon : GET");
		
		//Business Logic
		Product product = productService.getProductCoupon(productNo);
		// Model View 
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductCouponView.jsp";
	}

	@RequestMapping( value="updateProductCoupon", method=RequestMethod.POST )
	public String updateProductCoupon( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/product/updateProductCoupon : POST");
		
		//Business Logic
		productService.updateProductCoupon(product);

		return "redirect:/product/getProductCoupon?productNo="+product.getProductNo();
	}
////////////////////////쿠폰 수정 Get, Post 방식/////////////////////////////////
////////////////////////상품권 수정 Get, Post 방식/////////////////////////////////		
	@RequestMapping( value="updateProductVoucher", method=RequestMethod.GET )
	public String updateProductVoucher( @RequestParam("productNo") int productNo , Model model ) throws Exception{	
	
	System.out.println("/product/updateProductVoucher : GET");
	
	//Business Logic
	Product product = productService.getProductVoucher(productNo);
	// Model View 
	model.addAttribute("product", product);
	
	return "forward:/product/updateProductVoucherView.jsp";
	}
	
	@RequestMapping( value="updateProductVoucher", method=RequestMethod.POST )
	public String updateProductVoucher( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{
	
	System.out.println("/product/updateProductVoucher : POST");
	
	//Business Logic
	productService.updateProductVoucher(product);
	
	return "redirect:/product/getProductVoucher?productNo="+product.getProductNo();
	}
////////////////////////상품권 수정 Get, Post 방식/////////////////////////////////
////////////////////////상품권 수정 Get, Post 방식/////////////////////////////////		
	@RequestMapping( value="updateProductPoint", method=RequestMethod.GET )
	public String updateProductPoint( @RequestParam("productNo") int productNo , Model model ) throws Exception{	
	
	System.out.println("/product/updateProductPoint : GET");
	
	//Business Logic
	Product product = productService.getProductPoint(productNo);
	// Model View 
	model.addAttribute("product", product);
	
	return "forward:/product/updateProductPointView.jsp";
	}
	
	@RequestMapping( value="updateProductPoint", method=RequestMethod.POST )
	public String updateProductPoint( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{
	
	System.out.println("/product/updateProductPoint : POST");
	
	//Business Logic
	productService.updateProductPoint(product);
	
	return "redirect:/product/getProductPoint?productNo="+product.getProductNo();
	}
////////////////////////상품권 수정 Get, Post 방식/////////////////////////////////	
////////////////////////쿠폰 상품 전체 목록 조회 Get/Post 방식/////////////////////////////////		
	@RequestMapping( value="listProductCoupon" )
	public String listProductCoupon( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/listProductCoupon : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic
		Map<String , Object> map = productService.getProductCouponList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model View
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProductCoupon.jsp";
	}
////////////////////////쿠폰 상품 전체 목록 조회 Get/Post 방식/////////////////////////////////		
////////////////////////상품권 상품 전체 목록 조회 Get/Post 방식/////////////////////////////////		
	@RequestMapping( value="listProductVoucher" )
	public String listProductVoucher( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/listProductVoucher : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic
		Map<String , Object> map = productService.getProductVoucherList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model View
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProductVoucher.jsp";
	}
////////////////////////상품권 상품 전체 목록 조회 Get/Post 방식/////////////////////////////////		
////////////////////////포인트 상품 전체 목록 조회 Get/Post 방식/////////////////////////////////		
	@RequestMapping( value="listProductPoint" )
	public String listProductPoint( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/listProductPoint : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic
		Map<String , Object> map = productService.getProductPointList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model View
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/listProductPoint.jsp";
	}
////////////////////////포인트 상품 전체 목록 조회 Get/Post 방식/////////////////////////////////
////////////////////////쿠폰 상품 삭제 Post 방식/////////////////////////////////	
	/*
	@RequestMapping ( value="deleteProductCoupon")
	public String deleteProductCoupon(@RequestParam ("productNo") String productNo) {
		
		System.out.println("/product/deleteProductCoupon : POST");
		
		int r = productService.deleteProductCoupon(productNo);
		
		//실패 했을 때 결과 페이지
		if (r < 10000) {
			return "redirect:/product/getProductCoupon.jsp";
		}
		//삽입에 성공했을 때 처리 - 시작 페이지로 이동
		return "redirect:/product/listProductCoupon.jsp";
	}
	*/
	
	
	
	
	
	
	
}


