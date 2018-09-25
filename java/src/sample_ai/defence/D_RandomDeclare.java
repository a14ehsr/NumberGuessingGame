package src.main.java.sample_ai.defence;
import java.util.Scanner;
public class D_RandomDeclare{
	int min;       //Minimum usable value (inclusive)
	int max;       //Maximum usable value (inclusive)
	int gameNum;   //Number of games
	int roundNum;  //Maximum number of rounds per game
	int changeLimit; //Minimum number of times to change
	String playerName = "D_RandomDeclare";
	Scanner sc;

	private void run(){
		sc = new Scanner(System.in);
		init();
		int[][][] record = new int[gameNum][roundNum][2];
		for(int g=0; g<gameNum; g++){
			int myNum = (int) (Math.random() * (max - min)) + min;
			int c_time = (int)(Math.random()*10);
			for(int r=0; r<roundNum; r++){
				if (r>0 && r%10 == c_time) myNum = (int) (Math.random() * (max - min)) + min;
				record[g][r][0] = myNum;
				System.out.println(myNum);
				int num = sc.nextInt(); //attack number
				record[g][r][1] = num;
				if(myNum == num) break;
			}
		}
		sc.close();
	}

	private void init(){
		min = sc.nextInt();
		max = sc.nextInt();
		gameNum = sc.nextInt();
		roundNum = sc.nextInt();
		changeLimit = sc.nextInt();
		System.out.println(playerName);
	}

	public static void main(String[] args) {
		(new D_RandomDeclare()).run();
	}
}
