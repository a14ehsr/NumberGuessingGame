package ac.a14ehsr.sample_ai.attack;

import java.util.Scanner;
public class A_RandomAsk{
	int min;       //Minimum usable value (inclusive)
	int max;       //Maximum usable value (inclusive)
	int gameNum;   //Number of games
	int roundNum;  //Maximum number of rounds per game
	int changeLimit; //Minimum number of times to change
	String playerName = "A_RandomAsk";
	Scanner sc;

	private int ask(int game,int round,int[][][] record){
		return (int)(Math.random()*(max - min)) + min;
	}

	private void run(){
		sc = new Scanner(System.in);
		init();
		int[][][] record = new int[gameNum][roundNum][2];
		for(int g=0; g<gameNum; g++){
			for(int r=0; r<roundNum; r++){
				int askNum = ask(g,r,record);
				record[g][r][0] = askNum;
				System.out.println(askNum);
				// 1 : if the predicted value is larger than the partner's number,
				//-1 : if it is smaller,
				// 0 : if it matches
				int ud = sc.nextInt(); 
				record[g][r][1] = ud;
				if(ud == 0) break;
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
		(new A_RandomAsk()).run();
	}
}
