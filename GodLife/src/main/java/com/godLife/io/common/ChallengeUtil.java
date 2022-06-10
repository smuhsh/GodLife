package com.godLife.io.common;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.godLife.io.service.domain.Challenge;

public class ChallengeUtil {
	
	
	public static Challenge certiCycle(Challenge challenge)throws Exception {
			
		int result = 0;

		
		String inputDate = challenge.getEndDate(); // 매게변수로 받을곳
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
		String day = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 종료 날짜
		
		String inputDate2= challenge.getStartDate();// 매게변수로 받을곳
		Date date2 =  new SimpleDateFormat("yyyy-MM-dd").parse(inputDate2);
		String day2 = "";
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		//시작날짜
		
		
		long diffSec = (date.getTime() - date2.getTime()) / 1000; //초 차이
		
		long diffDays = (diffSec / (24*60*60))+1;
		
		int startWeek = cal.get(Calendar.DAY_OF_WEEK);
		int counts = 0;//=> 인증 횟수가 될수 잇음.
		
		Calendar cal3 = Calendar.getInstance();
		
		List<String> certiDate = new ArrayList<String>();
			
for(int i=0; i<challenge.getCertiCycle().size(); i++) {
							
	for (LocalDate date1 = LocalDate.parse(inputDate2); date1.isBefore(LocalDate.parse(inputDate)); date1 = date1.plusDays(1))
			{
				
		
			String start = ""+date1;
			Date dates = new SimpleDateFormat("yyyy-MM-dd").parse(start);
			cal3.setTime(dates);
			int week = cal3.get(Calendar.DAY_OF_WEEK);// 이 날짜의 요일을 알 수 있음.
				
			if(week == Integer.parseInt(challenge.getCertiCycle().get(i))) {
					counts++;
					certiDate.add(start);
				}	
					
					
			}
			
		}
	
		int week2 = cal.get(Calendar.DAY_OF_WEEK);// 종료날짜의 요일
		
	for(int i=0; i<challenge.getCertiCycle().size(); i++) {
		
		if(week2 == Integer.parseInt(challenge.getCertiCycle().get(i))) {
			counts++;
			certiDate.add(inputDate);
		}
		
	}
	
	challenge.setTotalCertiCount(counts);
	challenge.setCertiDate(certiDate);
	
	
	List<String> certiCycleName = new ArrayList<String>();
	for(int i=0; i<challenge.getCertiCycle().size(); i++) {
		if(challenge.getCertiCycle().get(i).equals("1")) {
			certiCycleName.add("일");
		}
		if(challenge.getCertiCycle().get(i).equals("2")) {
			certiCycleName.add("월");			
		}
		if(challenge.getCertiCycle().get(i).equals("3")) {
			certiCycleName.add("화");
		}
		if(challenge.getCertiCycle().get(i).equals("4")) {
			certiCycleName.add("수");
		}
		if(challenge.getCertiCycle().get(i).equals("5")) {
			certiCycleName.add("목");
		}
		if(challenge.getCertiCycle().get(i).equals("6")) {
			certiCycleName.add("금");				
		}
		if(challenge.getCertiCycle().get(i).equals("7")) {
			certiCycleName.add("토");
		}
	}
	
	challenge.setCertiCycleName(certiCycleName);
	
		return challenge;
	}
	
	
	public static Challenge setCategName(Challenge challenge) {
		
		if(challenge.getChallengeCategNo() == 1) {
			challenge.setChallengeCategName("운동");
		}else if(challenge.getChallengeCategNo() == 2){
			challenge.setChallengeCategName("식습관");
		}else if(challenge.getChallengeCategNo() == 3){
			challenge.setChallengeCategName("공부");
		}else if(challenge.getChallengeCategNo() == 4){
			challenge.setChallengeCategName("취미");
		}else if(challenge.getChallengeCategNo() == 5){
			challenge.setChallengeCategName("생활");
		}
		
		
		return challenge;
	}
	
	public List<Challenge> setCategNoList(List<Challenge> list){
		
		List<Challenge> challengeList = new ArrayList<Challenge>();
		
		for(Challenge challenge : list) {
			if(challenge.getChallengeCategNo() == 1) {
				challenge.setChallengeCategName("운동");
			}else if(challenge.getChallengeCategNo() == 2){
				challenge.setChallengeCategName("식습관");
			}else if(challenge.getChallengeCategNo() == 3){
				challenge.setChallengeCategName("공부");
			}else if(challenge.getChallengeCategNo() == 4){
				challenge.setChallengeCategName("취미");
			}else if(challenge.getChallengeCategNo() == 5){
				challenge.setChallengeCategName("생활");
			}
			challengeList.add(challenge);
		}
		
		return challengeList;
	}
	
}



	
//int[] athentication = new int[7]; //일요일
//Scanner sc = new Scanner(System.in);
//for(int i=0; i<athentication.length; i++) {
//	System.out.println("1.일 2.월 3.화 4.수 5.목 6.금 7.토");
//	System.out.print("인증주기 입력 : ");
//	athentication[i] = sc.nextInt();
//	int vreak = 0;
//	System.out.println("입력을 그만 두겠습니까. 1 (n) 2 (y)");
//	vreak = sc.nextInt();
//	if(vreak == 2) {
//		break;
//	}
//}  이 부분은 challenge.getCertiCycle

//athentication[i] 인증 주기로 List 타입으로 데이터가 1,2,3,4... 로 들어올거임.



//String[] athenticationDay = new String[7];
//for(int i=0; i<challenge.getCertiCycle().size(); i++) {
//	if(athentication[i]==0) {
//		break;
//	}
//	switch(athentication[i]) {
//	case 1 : athenticationDay[i] = "일";
//	break;                
//	case 2 : athenticationDay[i] = "월";
//	break;                
//	case 3 : athenticationDay[i] = "화";
//	break;                
//	case 4 : athenticationDay[i] = "수";
//	break;                
//	case 5 : athenticationDay[i] = "목";
//	break;                
//	case 6 : athenticationDay[i] = "금";
//	break;                
//	case 7 : athenticationDay[i] = "토";
//	break;
//	}
//	
//}