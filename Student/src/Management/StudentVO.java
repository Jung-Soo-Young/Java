package Management;

//�� ����
public class StudentVO {
	private String name;
	private int age;
	private int number;
	private static int seq;	// ������Ű�� ��ü����
	
	public StudentVO() {
		
	}
	
	public StudentVO(String name, int age) {
		this.name = name;
		this.age = age;
		this.number = ++seq;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void show() {
		System.out.println("��ȣ : " + this.number + "/�̸� : " + name + "/���� : " + age + "��");
	}

}
