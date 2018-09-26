import java.util.Scanner;
public class D_JavaSample{
	int min;       //Minimum usable value (inclusive)
	int max;       //Maximum usable value (inclusive)
	int gameNum;   //Number of games
	int roundNum;  //Maximum number of rounds per game
	int changeLimit; //Minimum number of times to change
	String playerName = "D_JavaSample";
	Scanner sc;

	private void run() {
		sc = new Scanner(System.in);
		init();
		int[][][] record = new int[gameNum][roundNum][2];
		for (int g=0; g<gameNum; g++) {
			for (int r=0; r<roundNum; r++) {
				// ============== 書き換え箇所 ここから ===============
				// myNumに数字を代入する．
				// sampleでは最初のラウンドでランダムに入力し，それ以降は常に同じ数字とする．
				int myNum = r == 0 ? (int)(Math.random()*(max - min)) + min : record[g][r - 1][0];




				// ============== 書き換え箇所 ここまで ===============
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
		(new D_JavaSample()).run();
	}
}
