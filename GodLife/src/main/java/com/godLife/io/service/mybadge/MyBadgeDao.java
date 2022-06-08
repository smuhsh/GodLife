package com.godLife.io.service.mybadge;

import java.util.List;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.MyBadge;


//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface MyBadgeDao {
	

/////////////////////////////////////////////////////////////	

	// 내 배지 상세 내용  
	public MyBadge getBadgeMy(int myBadgeNo) throws Exception ;

	// 내 배지 전체 목록 UI 요청
	public List<MyBadge> getBadgeMyList(Search search) throws Exception ;

	// 내 배지 활동 횟수량 증가
	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception ;	
	
	
/////////////////////////////////////////////////////////////
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return (기존 Data)
	public int getTotalCount(Search search) throws Exception ;
	
	

}
