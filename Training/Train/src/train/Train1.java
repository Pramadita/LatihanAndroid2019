package train;

public class Train1 {

	public static void main(String[] args) 
	{
		String [] group = {"Roni", "Yogi", "Ali", "Hanafi", "Fiki", "Slamet"};
		System.out.println("Daftar Kelompok :");
		int no = 0;
		for (int i =0; i<6; i++)
		{
			System.out.println(++no + ". "+ group[i]);
		}

	}

}
