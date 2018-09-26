import java.util.Scanner;
public class Defence{
	int min;       //Minimum usable value (inclusive)
	int max;       //Maximum usable value (inclusive)
	int gameNum;   //Number of games
	int roundNum;  //Maximum number of rounds per game
	int changeLimit; //Minimum number of times to change
	String playerName = "Defence";
	Scanner sc;

	private int myNumber(int game,int round,int[][][] record){
		if(round == 0){
			return (int)(Math.random()*(max - min)) + min;
		}else{
			return record[game][round - 1][0];
		}
	}
	private void run(){
		sc = new Scanner(System.in);
		init();
		int[][][] record = new int[gameNum][roundNum][2];
		for(int g=0; g<gameNum; g++){
			for(int r=0; r<roundNum; r++){
				int myNum = myNumber(g,r,record);
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
		(new Defence()).run();
	}
}
