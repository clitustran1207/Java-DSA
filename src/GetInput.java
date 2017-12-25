import java.util.Scanner;

public class GetInput {
	Scanner sc = new Scanner(System.in);
	
	public String getInput(String msg) {
		System.out.print(msg);
		String input = sc.nextLine();
		return input;
	}
	
}
