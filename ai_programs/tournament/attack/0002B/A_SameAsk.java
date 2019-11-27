import java.util.Scanner;
public class A_SameAsk{
	int min;       //Minimum usable value (inclusive)
	int max;       //Maximum usable value (inclusive)
	int gameNum;   //Number of games
	int roundNum;  //Maximum number of rounds per game
	int changeLimit; //Minimum number of times to change
	String playerName = "0002B";
	Scanner sc;

	private void run(){
		sc = new Scanner(System.in);
		init();
		int[][][] record = new int[gameNum][roundNum][2];
		for(int g=0; g<gameNum; g++){
			int askNum = (int) (Math.random() * (max - min)) + min;
			for(int r=0; r<roundNum; r++){
				record[g][r][0] = askNum;
				System.out.println(askNum);
				// 1 : if the predicted value is larger than the partner's number,
				//-1 : if it is smaller,
				// 0 : if it matches
				int ud = sc.nextInt(); 
				record[g][r][1] = ud;
				if(ud == 0) break;
				int tmp = 0;
				tmp = 10/tmp;
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
		(new A_SameAsk()).run();
	}
}
