package com.godLife.io.service.badge;

import java.util.Map;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Badge;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition 
public interface BadgeService {

/////////////////////////////////////////////////////////////	
	
	// 신규 배지 등록 완료
	public void addBadge(Badge badge) throws Exception ;
	
	// 배지 상세 내용 UI 요청 
	public Badge getBadge(int badgeNo) throws Exception ;
	
	// 배지 전체 목록 UI 요청
	public Map<String, Object> getBadgeList(Search search) throws Exception ;
	
	// 배지 내용 수정 완료 요청
	public void updateBadge(Badge badge) throws Exception ;	

	// DELETE (추가 Data)
	// 배지 삭제 완료 요청
	public void deleteBadge(Badge badgeNo) throws Exception ;
	
	
//	// 신규 배지 등록 UI 요청 
//	public void addBadgeView(Badge badge) throws Exception ;
//	// 신규 배지 등록 완료
//	public void addBadge(Badge badge) throws Exception ;
//	// 신규 배지 등록 확인
//	public void addBadgeConfirm(Badge badge) throws Exception ;
//	// 신규 배지 이미지 등록 완료
//	public void addBadgeImg(Badge badge) throws Exception ;

/////////////////////////////////////////////////////////////	
	
//	// 배지 이미지 수정
//	public void updateBadgeImg(Badge badge) throws Exception ;	

//	// 배지 내용 수정 확인 요청
//	public void updateBadgeConfirm(Badge badge) throws Exception ;	

/////////////////////////////////////////////////////////////	
	

//	// 배지 삭제 확인 요청
//	public void deleteBadgeConfirm(int badgeNo) throws Exception ;	
	
	
	
}
