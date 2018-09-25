import java.util.Scanner;
public class D_Miura{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
    int cnt;
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "Miura";
	Scanner sc;
	private void run(){
		int myNum = 30;

		parameterInit();

		for(int v=0; v<n; v++){
			//========各試合の初期値の設定============
			myNum = (int)(Math.random()*max)+ min;

			//=====================================


			for(int b=0; b<battleMax; b++){
				//========================================================
				//自分の数値
				System.out.println(myNum);
				//攻撃側の予想数値
				int num = sc.nextInt();
				if(myNum == num) break;
				//========================================================

				//=========数値の変更等を行う処理をここに書いてください========

                if((Math.abs(myNum-num) <= 13) && (cnt >= 10)){
                    if(myNum > num) myNum = num-2;
                    else myNum = num+2;
					myNum = num;
					cnt = 0;
				}


                cnt++;

				//========================================================

			}
		}

	}
	private void parameterInit(){
		sc = new Scanner(System.in);
		min = sc.nextInt();
		max = sc.nextInt();
		n = sc.nextInt();
		battleMax = sc.nextInt();
		changeConstraint = sc.nextInt();
		System.out.println(playerName);
	}
	public static void main(String[] args) {
		//System.out.println("DEF");
		(new D_Miura()).run();
	}
}
