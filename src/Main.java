import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter writer = null;
	public static void main(String[] args) {
		String answerEliza = null;
		try {
			writer = new PrintWriter(new File("output.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Hello! My name is Eliza. May I know your name please?");
		String name = sc.nextLine();
		String query = null;
		QueryProcessor qp = new QueryProcessor();
		System.out.println("So " + name + ". What can I do for you today?");
		for(int i = 0; i < 100; i++){
			query = sc.nextLine();
			if(query.equalsIgnoreCase("Leave")){
				break;
			}
			else{
				qp.populateSets(name);
				answerEliza = qp.getAnswer(query, name);
				System.out.println(answerEliza);
				writer.println(answerEliza);
			}
		}		
		answerEliza = "Thank you for coming " + name + ". Please visit again :)";
		System.out.println(answerEliza);
		writer.println(answerEliza);
	}
}
