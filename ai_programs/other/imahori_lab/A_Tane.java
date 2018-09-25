import java.util.Scanner;
public class A_Tane{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "player2";
	Scanner sc;
	private void run(){
		sc = new Scanner(System.in);
		int askNum = 50;
		parameterInit();
		int changeNum = 4;
		for(int v=0; v<n; v++){
			int L = min;
			int R = max;
			int tmp = -1;
			int udtmp = -1;
			for(int b=0; b<battleMax; b++){
				//=========数値の変更等を行う処理をここに書いてください========

				//example
				askNum = (L + R)/2;
				if((L+R)%2 == 1 && Math.random() < 0.5){
					askNum++;
				}
				if(L == min && R == max){
					tmp = askNum;
				}
				if(b == changeNum){
					askNum = tmp;
				}



				//========================================================



				//========================================================
				//攻撃側の予想数値
				System.out.println(askNum);
				//予想に対して相手の数字が大きいなら1,小さいなら-1,一致なら0
				int ud = sc.nextInt();
				if(ud == 0) break;
				//========================================================
				if(b == changeNum){
					if(udtmp == ud){
						continue;
					}else{
						if(ud == 1){
							L = min;
							R = askNum;
							continue;
						}else{
							L = askNum;
							R = max;
							continue;
						}
					}
				}
				if(ud == 1){
					L = askNum;
					if(b == 0){
						udtmp = ud;
					}
				}else{
					R = askNum;
					if(b == 0){
						udtmp = ud;
					}
				}
				if(L - R == 1|| R - L == 1){
					L = min;
					R = max;
				}


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
		(new A_Tane()).run();
	}
}
