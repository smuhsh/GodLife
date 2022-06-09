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
		
		
	
		
//		switch(cal.get(Calendar.DAY_OF_WEEK)) {
//			case 1 : day2 = "일";
//			break;
//			case 2 : day2 = "월";
//			break;
//			case 3 : day2 = "화";
//			break;
//			case 4 : day2 = "수";
//			break;
//			case 5 : day2 = "목";
//			break;
//			case 6 : day2 = "금";
//			break;
//			case 7 : day2 = "토";
//			break;
//		}
//		
//		System.out.println("시작 날짜 : "+inputDate2);
//		System.out.println("종료 날짜 : "+inputDate);
//		
//		System.out.println("시작 날 종료날 차이 : "+diffDays);
//		
//		
//		// 인증주기가 체크박스로 1일 / 2월 / 3화 / 4수 / 5목 / 6금 / 7토
//		
//		int startWeek = cal.get(Calendar.DAY_OF_WEEK);
//		System.out.println("startWeek "+startWeek);
//		//시작날짜의 요일이 나옴 위에 처럼 1이면 일 2면 월...
//		
		int result = 0;
//		for(int i=0; i<challenge.getCertiCycle().size(); i++) {// 3 5 두개
//			
//			System.out.println("인증주기 0: "+Integer.parseInt(challenge.getCertiCycle().get(i)));
//			int count = 0;
//			int largeCount = 0;
//			
//			if(startWeek > Integer.parseInt(challenge.getCertiCycle().get(i))) { //startweek = 4 
//				int j = 0;
//				
//				System.out.println("인증주기 1: "+Integer.parseInt(challenge.getCertiCycle().get(i)));
//				
//				for(j = startWeek; j<=7; j++) {
//					count++;
//				}
//				
//				for(j = 0; j<startWeek; j++) {
//					count++;
//				}
//				count = 1;
//				result = result + ((int)(diffDays - count));//
//				System.out.println("result :"+result);
//				//       4                               6
//			}else if(startWeek < Integer.parseInt(challenge.getCertiCycle().get(i))){
////				for(int j = startWeek; j<challenge.getCertiCycle().size(); j++) {
////					System.out.println("인증주기 2: "+Integer.parseInt(challenge.getCertiCycle().get(i)));
////					count++;
////				}
////				count -= 1;
//				largeCount++;
//				result = result + (int)diffDays;
//				
//			}else {
//				System.out.println("인증주기 3: "+Integer.parseInt(challenge.getCertiCycle().get(i)));
//				result = result + (int)diffDays;
//			}
//	}
		
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
	
	
	
	
	
	
//		System.out.println("날짜 : "+inputDate);
//		
//		counts++;
//		System.out.println("week2 : "+week2);
//		System.out.println("counts : "+counts);
//		
//		
//		System.out.println("///////////////총정리////////////////////");
//		
//		System.out.println("시작날짜 : "+inputDate2);
//		System.out.println("시작 요일 : "+day2);
//		System.out.println("시작날짜 : "+inputDate);
////		int stack = 0;
////		for(int i=0; i<challenge.getCertiCycle().size(); i++) {
////			stack++;
////		}// 총 진행하는 날이 7일 이하일때 최소 그 만큼의 인증 횟수가 나오기때문에 만들어둔거같음.
////		
//		System.out.println("챌린지 총 인증 횟수 :"+((result/7))+"번");
//		
//		
//		System.out.println("최종 분모 : "+((result/7)));
//		
//		challenge.setCertiCount((result/7));
		
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