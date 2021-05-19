package Management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class ScoreDAO {
	// 국어, 영어, 수학
	final int SUBJECT_SIZE = 3;	// 과목 수
	public Scanner sc = new Scanner(System.in);
	private ArrayList<StudentVO> arStudent = null;	// 학생 객체 배열
	private ArrayList<Integer> arScore = null;		// 점수 객체 배열
	private LinkedHashMap<StudentVO, ArrayList<Integer>> smsMap = new LinkedHashMap<>();
	
	public void view() {
		// 메인 view
		int choice = 0;
		do {
			System.out.println("학생 관리 시스템");
			System.out.println("1. 등록");
			System.out.println("2. 검색");
			System.out.println("3. 나가기");
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 3) break;
			
			switch(choice) {
			case 1:
				join();
				break;
			case 2:
				search();
				break;
			default:
				System.out.println("잘못 입력하였습니다.");
			}
		} while (true);
		
	}
	public void join() {
		String isQuit = "";
		String q = "q";
		do {
			System.out.println("==========[학생 정보 입력(종료는 q)]==========");
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("나이 : ");
			int age = sc.nextInt();
			
			// 나이 입력 후 엔터 상쇄
			sc.nextLine();
			StudentVO std = new StudentVO(name, age);	// 이름, 나이 정보 저장
			arStudent = new ArrayList<>();
			arScore = new ArrayList<>();
			arStudent.add(std);							// arStudent 배열에 저장
			System.out.println("국어 영어 수학 점수를 입력하세요");
			for(int i=0; i<SUBJECT_SIZE; i++) {
				arScore.add(Integer.parseInt(sc.next()));	// arScore 배열에 점수 저장
			}
			smsMap.put(std, arScore);
			System.out.println("종료 : ");
			
			// 점수 입력 후 엔터 상쇄
			sc.nextLine();
			isQuit = sc.nextLine();
		} while(isQuit.equals(q));	// 종료 여부
		list(smsMap);				// 목록보기
	}
	
	// 학생 목록
	public void list(LinkedHashMap<StudentVO, ArrayList<Integer>> db) {
		System.out.println("======[가입 목록]======");
		Set<Entry<StudentVO, ArrayList<Integer>>> set = db.entrySet();
		Iterator<Map.Entry<StudentVO, ArrayList<Integer>>> iter = set.iterator();		// Iterator 반복자
		
		while(iter.hasNext()) {
			Entry<StudentVO, ArrayList<Integer>> temp = iter.next();
			temp.getKey().show(); 						// 한 학생의 객체 출력
			for(int score : temp.getValue()) {
				System.out.print(score + "점  ");
			}
			System.out.println("\n");
		}
		
	}
	
	// 학생 정보 검색
	public void search() {
		Set<StudentVO> scoreSet = smsMap.keySet();
		Iterator<StudentVO> iter = scoreSet.iterator();
		
		String name = null;
		System.out.println("검색할 학생의 이름 : ");
		name = sc.nextLine();					// 검색할 이름
		
		System.out.println("=====[검색 결과]=====");
		while(iter.hasNext()) {
			StudentVO std = iter.next();
			if(name.equals(std.getName())) {	// 입력한 이름과 저장된 이름이 같은가?
				std.show();						// 같다면 정보를 불러온다.
				System.out.println("   국어      영어      수학");
				System.out.println("   ");
				for(int score : smsMap.get(std)) {
					System.out.print(score + "점  ");
				}
				System.out.println("\n");
			}
		}
	}
	
	// 학생 정보 수정
	public void update() {
		
	}
	// 학생 정보 삭제
	public void delete() {
		
	}
	
}
