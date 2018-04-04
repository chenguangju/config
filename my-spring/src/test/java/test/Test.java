package test;

public class Test {

	

	private String name;
	static String gender;
	
	public static void main(String[] args) {
		System.out.println(Test.gender);
		Test.gender="1";
		Test tset=new Test();
		System.out.println(tset.gender);
	}
}
