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
			System.out.println("3. ����");
			System.out.println("4. ����");
			System.out.println("5. ������");
			choice = sc.nextInt();
			sc.nextLine();
			if (choice == 5) break;
			
			switch(choice) {
			case 1:
				join();
				break;
			case 2:
				search();
				break;
			case 3:
				delete();
				break;
			case 4:
				update();
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
		int size = smsMap.size();
		
		Set<Entry<StudentVO, ArrayList<Integer>>> set = db.entrySet();
		Iterator<Map.Entry<StudentVO, ArrayList<Integer>>> iter = set.iterator();		// Iterator �ݺ���
		if(size == 0) {
			System.out.println("��ϵ� �л��� �����ϴ�.");
		} else {
			System.out.println("======[���� ���]======");
			while(iter.hasNext()) {
				Entry<StudentVO, ArrayList<Integer>> temp = iter.next();
				temp.getKey().show(); 						// �� �л��� ��ü ���
				for(int score : temp.getValue()) {
					System.out.print(score + "��  ");
				}
				System.out.println("\n");
			}
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
				System.out.println("   ����     ����     ����     ");
				for(int score : smsMap.get(std)) {
					System.out.print(score + "��  ");
				}
				System.out.println("\n");
			}
		}
	}
	
	// �л� ���� ����
	public void update() {
		list(smsMap);
		int num = 0, choice = 0;
		boolean isUser = false;
		
		System.out.println("������ �л��� ��ȣ : ");
		num = sc.nextInt();

		Iterator<StudentVO> iter = smsMap.keySet().iterator();
		while(iter.hasNext()) {
			StudentVO std = iter.next();
			if(num == std.getNumber()) {
				isUser = true;
				System.out.println("1. ��������\n2. ��������");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1:
					System.out.println("�̸��� ���̸� ������� �Է��ϼ���.");
					std.setName(sc.next());
					std.setAge(Integer.parseInt(sc.next()));
					sc.nextLine();
					break;
				case 2:
					System.out.println("���� ���� ���� ������ ������� �Է��ϼ���.");
					for(int i=0; i<SUBJECT_SIZE; i++) {
						smsMap.get(std).set(i, sc.nextInt());
					}
					sc.nextLine();
					System.out.println("���� �Ϸ�");
					break;
				default:
					isUser = false;
					System.out.println("���� ����");
				}
			} 
		}
		if(!isUser) {
			System.out.println("�����Ͻ� �л��� �����ϴ�.");
		}
		list(smsMap);
	}
	
	// �л� ���� ����
	public void delete() {
		//Iterator�� �˻� (��ȣ)
		int num = 0;
		StudentVO delStd = null;
		System.out.println("������ �л��� ��ȣ�� �Է��ϼ���.");
		num = sc.nextInt();
		sc.hasNextLine();
		Iterator <StudentVO> iter = smsMap.keySet().iterator();
		
		while(iter.hasNext()){
			StudentVO std = iter.next();
			if(num == std.getNumber()) {
				delStd = std;
			}
		}
		if (delStd == null) {
			System.out.println("�Է��Ͻ� ��ȣ�� �л��� �����ϴ�.");
		} else {
			smsMap.remove(delStd);
			System.out.println("���� �Ϸ�");
			list(smsMap);
		}
	}
	
}
