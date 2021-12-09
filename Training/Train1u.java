public class Train1u
{
	public static void main (String [] args)
	{
		String [] group = {"1. Roni", "2. Yogi", "3. Ali", "4. Hanafi", "5. Fiki", "6. Slamet"};
		System.out.println("Daftar Kelompok :");
		int i=0;
		do
		{
			System.out.println(group[i]);
			i++;
		}
		while (i<group.length);
	}
}