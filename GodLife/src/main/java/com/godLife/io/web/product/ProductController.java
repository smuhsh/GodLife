package com.godLife.io.web.product;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	  @Resource(name="uploadPath")
	    String uploadPath;
	
	
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

		return "forward:/product/getProductCouponList";

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
/////////////////////////////////////////////Update 상품/////////////////////////////	
///////////쿠폰 상품
	
	@RequestMapping( value="updateProductCouponView", method=RequestMethod.GET )
	public String updateProductCoupon( @RequestParam("productNo") int productNo , Model model ) throws Exception{	
	
		System.out.println("/product/updateProductCouponView : GET");
		
		//Business Logic
		Product product = productService.getProductCoupon(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductCouponView.jsp";

	}
//세팅 화면에서 이미지 바꾸고 싶다면...이라고 생각하면서 만든 컨트롤러... 맞나?	
//	@RequestMapping( value="updateProductCouponImage", method=RequestMethod.POST )
//	public String updateProductCouponImage( @RequestParam ("imageUpload" )  MultipartFile file, HttpSession session,
//			@RequestParam("productNo") int productNo , Model model) throws Exception {
//		System.out.println("/product/updateProductCouponImage");
//		System.out.println(productNo);
//		
//	
//		
//		
//		
//		
//		
//		return "forward:/product/updateProductCouponView";
//		
//	}
	
	
 
	
	
	
	
	
	
//이미지 업로드
	@RequestMapping( value="updateProductCoupon", method=RequestMethod.POST )
	public String updateProductCoupon( @ModelAttribute("product") Product product , Model model , HttpSession session,
									   @RequestParam ("imageUpload" )  MultipartFile file) throws Exception{

		System.out.println("/product/updateProductCoupon : POST");
		
		//System.out.println("Before : getOriginalFilename ");
		
		String fileName = file.getOriginalFilename();
		
		//System.out.println("After : getOriginalFilename ");
		
		
		//System.out.println("Byte : " + file.getBytes());
		 fileName =  uploadFile(fileName, file.getBytes());   // 파일 이름 중복 제거 
		//System.out.println("파일이름" +  fileName );
		
		product.setProductImg(fileName);
		
		//Business Logic
		productService.updateProductCoupon(product);

		return "forward:/product/getProductCouponList?productNo="+product.getProductNo();
	}

	   //이미지 업로드 파일명 랜덤 생성 메서드
    private String uploadFile(String originalName, byte[] fileData) throws Exception{
    
        // uuid 생성 
        UUID uuid = UUID.randomUUID();
        
        //savedName 변수에 uuid + 원래 이름 추가
        String savedName = uuid.toString()+"_"+originalName;
        System.out.println("Before : target path " + uploadPath);

        //uploadPath경로의 savedName 파일에 대한 file 객체 생성
        File target = new File(uploadPath, savedName);
        //fileData의 내용을 target에 복사함
        System.out.println("Before : FileCopyUtils");
        System.out.println("Before : target " + target);

        FileCopyUtils.copy(fileData, target);	
        
        System.out.println("After : FileCopyUtils");
        
        return savedName;
    }	
	
	
	
	
///////////상품권 상품
	@RequestMapping( value="updateProductVoucherView", method=RequestMethod.GET )
	public String updateProductVoucher( @RequestParam("productNo") int productNo , Model model ) throws Exception{	
	
		System.out.println("/product/updateProductVoucherView : GET");
		
		//Business Logic
		Product product = productService.getProductVoucher(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductVoucherView.jsp";

	}

	@RequestMapping( value="updateProductVoucher", method=RequestMethod.POST )
	public String updateProductVoucher( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{

		System.out.println("/product/updateProductVoucher : POST");
		
		//Business Logic
		productService.updateProductVoucher(product);

		return "forward:/product/getProductVoucherList?productNo="+product.getProductNo();
	}
///////////포인트 상품
	@RequestMapping( value="updateProductPointView", method=RequestMethod.GET )
	public String updateProductPoint( @RequestParam("productNo") int productNo , Model model ) throws Exception{	
	
		System.out.println("/product/updateProductPointView : GET");
		
		//Business Logic
		Product product = productService.getProductPoint(productNo);
		// Model 과 View 연결
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductPointView.jsp";

	}

	@RequestMapping( value="updateProductPoint", method=RequestMethod.POST )
	public String updateProductPoint( @ModelAttribute("product") Product product , Model model , HttpSession session
										) throws Exception{

		System.out.println("/product/updateProductPoint : POST");

		//Business Logic
		productService.updateProductPoint(product);

		return "redirect:/product/getProductPointList?productNo="+product.getProductNo();
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
	
	@RequestMapping (value = "deleteProductCoupon")
	public String deleteProductCoupon( @ModelAttribute("product") Product productNo , Model model ) throws Exception {
	System.out.println("/product/deleteProductCoupon : POST");
	//Business Logic
	productService.deleteProductCoupon(productNo);
	
	return "forward:/product/getProductCouponList";
	}

	@RequestMapping (value = "deleteProductVoucher")
	public String deleteProductVoucher( @ModelAttribute("product") Product productNo , Model model ) throws Exception {
	System.out.println("/product/deleteProductVoucher : POST");
	//Business Logic
	productService.deleteProductCoupon(productNo);
	
	return "forward:/product/getProductVoucherList";
	}

	@RequestMapping (value = "deleteProductPoint")
	public String deleteProductPoint( @ModelAttribute("product") Product productNo , Model model ) throws Exception {
	System.out.println("/product/deleteProductPoint : POST");
	//Business Logic
	productService.deleteProductPoint(productNo);
	
	return "forward:/product/getProductPointList";
	}
/////////////////////파일 업로드/////////////////////////////////////////////	
	 @RequestMapping(value = "requestupload1")
	    public String requestupload1(MultipartHttpServletRequest mtfRequest) {
	        String src = mtfRequest.getParameter("src");
	        System.out.println("src value : " + src);
	        MultipartFile mf = mtfRequest.getFile("file");

	        String path = "C:\\image\\";

	        String originFileName = mf.getOriginalFilename(); // 원본 파일 명
	        long fileSize = mf.getSize(); // 파일 사이즈

	        System.out.println("originFileName : " + originFileName);
	        System.out.println("fileSize : " + fileSize);

	        String safeFile = path + System.currentTimeMillis() + originFileName;

	        try {
	            mf.transferTo(new File(safeFile));
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return "redirect:/";
	    }
	
	
	
	
	
	
}