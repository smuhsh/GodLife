package com.godLife.io.service.mybadge;

import java.util.Map;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.User;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition 
public interface MyBadgeService {

/////////////////////////////////////////////////////////////	
	

	// 내 배지 상세 내용  
	public MyBadge getBadgeMy(int myBadgeNo, User user) throws Exception ;

	// 내 배지 전체 목록 UI 요청
	public Map<String, Object> getBadgeMyList(Search search, User user, Badge badge) throws Exception ;

	// 내 배지 활동 횟수량 증가
	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception ;	
	
	// 인증 이미지 제거시 활동 횟수량 감소
	public void updateBadgeMyActCountMinus(MyBadge myBadge) throws Exception ;	
	
	
	
}
