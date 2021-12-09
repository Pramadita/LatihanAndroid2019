public class Main 
{

	public static void main(String[] args) 
	{
			Person orang = new Person();
			orang.setFname ("John");
			System.out.println("Name: " + orang.getFname() + " " + orang.lname);
			System.out.println("Age: " + orang.age);
			orang.setNor (89);
			System.out.println("No Rumah: " + orang.getNor());
		
	}

}
