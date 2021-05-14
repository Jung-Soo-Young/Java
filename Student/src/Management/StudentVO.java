package Management;

//모델 생성
public class StudentVO {
	private String name;
	private int age;
	private int number;
	private static int seq;	// 증가시키는 대체변수
	
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
		System.out.println("번호 : " + this.number + "/이름 : " + name + "/나이 : " + age + "살");
	}

}
