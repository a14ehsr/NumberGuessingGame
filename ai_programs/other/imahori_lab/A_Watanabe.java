import java.util.Scanner;
public class A_Watanabe{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "Watanabe";
	Scanner sc;
	private void run(){
		sc = new Scanner(System.in);
		int askNum = 50;
		parameterInit();
		for(int v=0; v<n; v++){
		    int min_s=min;
		    int max_s=max;
		    int count=0;

			for(int b=0; b<battleMax; b++){
				//=========数値の変更等を行う処理をここに書いてください========

				//example
				//askNum = (int)(Math.random()*max)+ min;
			    if(b%7==0){ min_s=min; max_s=max; }
			    askNum=(min_s+max_s)/2;



				//========================================================



				//========================================================
				//攻撃側の予想数値
				System.out.println(askNum);
				//予想に対して相手の数字が大きいなら1,小さいなら-1,一致なら0
				int ud = sc.nextInt();
				if(ud == 0) break;
				else if(ud==1)
				    if(count==0){ max_s=askNum; count++; }
				    else min_s=askNum;
				else
				    if(count==0){ min_s=askNum; count++; }
				    else max_s=askNum;
				//========================================================




				//=========数値の変更等を行う処理をここに書いてください========






				//========================================================
			}
		}
	}
	private void parameterInit(){
		min = sc.nextInt();
		max = sc.nextInt();
		n = sc.nextInt();
		battleMax = sc.nextInt();
		changeConstraint = sc.nextInt();
		System.out.println(playerName);
	}
	public static void main(String[] args) {
		//System.out.println("ATK");
		(new A_Watanabe()).run();
	}
}
