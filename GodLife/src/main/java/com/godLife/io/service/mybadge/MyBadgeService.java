package com.godLife.io.service.mybadge;

import java.util.Map;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.MyBadge;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition 
public interface MyBadgeService {

/////////////////////////////////////////////////////////////	
	

	// 내 배지 상세 내용  
	public MyBadge getBadgeMy(int myBadgeNo) throws Exception ;

	// 내 배지 전체 목록 UI 요청
	public Map<String, Object> getBadgeMyList(Search search) throws Exception ;

	// 내 배지 활동 횟수량 증가
	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception ;	
	
	
	
}
