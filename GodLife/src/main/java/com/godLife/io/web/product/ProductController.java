package com.godLife.io.web.product;

import java.util.HashMap;
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


//==> 상품관리 Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
////////////////////////////////////////////////////////	
	@RequestMapping( value="addProductCouponView", method=RequestMethod.GET )
	public String addProductCoupon() throws Exception {

		System.out.println("/product/addProductCouponView : GET");
		
		return "/product/addProductCouponView.jsp";
	}

	@RequestMapping( value="addProductCoupon", method=RequestMethod.POST )
	public String addProductCoupon( @ModelAttribute("product") Product product ) throws Exception {
		
		System.out.println("/product/addProductCoupon : POST");
		//Business Logic
		productService.addProductCoupon(product);

		return "forward:/product/getProductCouponList.jsp";

	}
	
	@RequestMapping( value="addProductVoucherView", method=RequestMethod.GET )
	public String addProductVoucher() throws Exception {

		System.out.println("/product/addProductVoucherView : GET");
		
		return "/product/addProductVoucherView.jsp";
	}

	@RequestMapping( value="addProductVoucher", method=RequestMethod.POST )
	public String addProductVoucher( @ModelAttribute("product") Product product ) throws Exception {
		
		System.out.println("/product/addProductVoucher : POST");
		//Business Logic
		productService.addProductVoucher(product);

		return "forward:/product/getProductVoucherList.jsp";

	}
	
	@RequestMapping( value="addProductPointView", method=RequestMethod.GET )
	public String addProductPoint() throws Exception {

		System.out.println("/product/addProductPointView : GET");
		
		return "/product/addProductPointView.jsp";
	}

	@RequestMapping( value="addProductPoint", method=RequestMethod.POST )
	public String addProductPoint( @ModelAttribute("product") Product product ) throws Exception {
		
		System.out.println("/product/addProductPoint : POST");
		//Business Logic
		productService.addProductPoint(product);

		return "forward:/product/getProductPointList";

	}
	
////////////////////////////////////////////////////////
	@RequestMapping( value="getProductCoupon", method=RequestMethod.GET )
	public String getProductCoupon( @RequestParam("productNo") int productNo , Model model ) throws Exception {
		
		System.out.println("/product/getProductCoupon : GET");
		
		//Business Logic
		Product product = productService.getProductCoupon(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		//setAttribute로 쓰고 value값이 들어간다면,
		
		return "forward:/product/getProductCoupon.jsp";
	}
	
	@RequestMapping( value="getProductVoucher", method=RequestMethod.GET )
	public String getProductVoucher( @RequestParam("productNo") int productNo , Model model ) throws Exception {
		
		System.out.println("/product/getProductVoucher : GET");
		
		//Business Logic
		Product product = productService.getProductVoucher(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		//setAttribute로 쓰고 value값이 들어간다면,
		
		return "forward:/product/getProductVoucher.jsp";
	}
	
	@RequestMapping( value="getProductPoint", method=RequestMethod.GET )
	public String getProductPoint( @RequestParam("productNo") int productNo , Model model ) throws Exception {
		
		System.out.println("/product/getProductPoint : GET");
		
		//Business Logic
	
		Product product = productService.getProductPoint(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		//setAttribute로 쓰고 value값이 들어간다면,
		
		return "forward:/product/getProductPoint.jsp";
	}
	/////////////////////////////////Pass/////////////////////////////	

	
	@RequestMapping( value="updateProduct", method=RequestMethod.GET )
	public String updateProduct( @RequestParam("productNo") int productNo , Model model ) throws Exception{	
	
		System.out.println("/product/updateProduct : GET");
		
		//Business Logic
		Product product = productService.getProduct(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";

	}


	@RequestMapping( value="updateProduct", method=RequestMethod.POST )
	public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/product/updateProduct : POST");
		
		//Business Logic
		productService.updateProduct(product);

		return "redirect:/product/getProduct?productNo="+product.getProductNo();
	}
//////////////////////////////////////////////////////////////////
	@RequestMapping( value="getProductCouponList" )
	public String getProductCouponList( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/getProductCouponList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		
		Map<String,Object> map =productService.getProductCouponList(search);
		
				
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/getProductCouponList.jsp";
	}

	@RequestMapping( value="getProductVoucherList" )
	public String getProductVoucherList( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/getProductVoucherList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		
		Map<String,Object> map =productService.getProductVoucherList(search);
		
				
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/getProductVoucherList.jsp";
	}	

	@RequestMapping( value="getProductPointList" )
	public String getProductPointList( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{

		System.out.println("/product/getProductPointList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		
		Map<String,Object> map =productService.getProductPointList(search);
		
				
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/product/getProductPointList.jsp";
	}	
	
	
	
//////////////////////////////////////////////////////////////////	
	
	
	
	@RequestMapping (value = "deleteProduct")
	public String deleteProduct( @ModelAttribute("product") Product productNo , Model model ) throws Exception {
		
	System.out.println("/product/deleteProduct : POST");
	//Business Logic
	productService.deleteProduct(productNo);

	return "redirect:/product/listProduct.jsp";
	
	
	}
	
	
	
	
	
	
}