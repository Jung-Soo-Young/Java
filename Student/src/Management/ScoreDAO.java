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
	// ����, ����, ����
	final int SUBJECT_SIZE = 3;	// ���� ��
	public Scanner sc = new Scanner(System.in);
	private ArrayList<StudentVO> arStudent = null;	// �л� ��ü �迭
	private ArrayList<Integer> arScore = null;		// ���� ��ü �迭
	private LinkedHashMap<StudentVO, ArrayList<Integer>> smsMap = new LinkedHashMap<>();
	
	public void view() {
		// ���� view
		int choice = 0;
		do {
			System.out.println("�л� ���� �ý���");
			System.out.println("1. ���");
			System.out.println("2. �˻�");
			System.out.println("3. ������");
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
				System.out.println("�߸� �Է��Ͽ����ϴ�.");
			}
		} while (true);
		
	}
	public void join() {
		String isQuit = "";
		String q = "q";
		do {
			System.out.println("==========[�л� ���� �Է�(����� q)]==========");
			System.out.print("�̸� : ");
			String name = sc.nextLine();
			System.out.print("���� : ");
			int age = sc.nextInt();
			
			// ���� �Է� �� ���� ���
			sc.nextLine();
			StudentVO std = new StudentVO(name, age);	// �̸�, ���� ���� ����
			arStudent = new ArrayList<>();
			arScore = new ArrayList<>();
			arStudent.add(std);							// arStudent �迭�� ����
			System.out.println("���� ���� ���� ������ �Է��ϼ���");
			for(int i=0; i<SUBJECT_SIZE; i++) {
				arScore.add(Integer.parseInt(sc.next()));	// arScore �迭�� ���� ����
			}
			smsMap.put(std, arScore);
			System.out.println("���� : ");
			
			// ���� �Է� �� ���� ���
			sc.nextLine();
			isQuit = sc.nextLine();
		} while(isQuit.equals(q));	// ���� ����
		list(smsMap);				// ��Ϻ���
	}
	
	// �л� ���
	public void list(LinkedHashMap<StudentVO, ArrayList<Integer>> db) {
		System.out.println("======[���� ���]======");
		Set<Entry<StudentVO, ArrayList<Integer>>> set = db.entrySet();
		Iterator<Map.Entry<StudentVO, ArrayList<Integer>>> iter = set.iterator();		// Iterator �ݺ���
		
		while(iter.hasNext()) {
			Entry<StudentVO, ArrayList<Integer>> temp = iter.next();
			temp.getKey().show(); 						// �� �л��� ��ü ���
			for(int score : temp.getValue()) {
				System.out.print(score + "��  ");
			}
			System.out.println("\n");
		}
		
	}
	
	// �л� ���� �˻�
	public void search() {
		Set<StudentVO> scoreSet = smsMap.keySet();
		Iterator<StudentVO> iter = scoreSet.iterator();
		
		String name = null;
		System.out.println("�˻��� �л��� �̸� : ");
		name = sc.nextLine();					// �˻��� �̸�
		
		System.out.println("=====[�˻� ���]=====");
		while(iter.hasNext()) {
			StudentVO std = iter.next();
			if(name.equals(std.getName())) {	// �Է��� �̸��� ����� �̸��� ������?
				std.show();						// ���ٸ� ������ �ҷ��´�.
				System.out.println("   ����      ����      ����");
				System.out.println("   ");
				for(int score : smsMap.get(std)) {
					System.out.print(score + "��  ");
				}
				System.out.println("\n");
			}
		}
	}
	
	// �л� ���� ����
	public void update() {
		
	}
	// �л� ���� ����
	public void delete() {
		
	}
	
}
