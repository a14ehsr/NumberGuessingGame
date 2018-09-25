import java.util.Scanner;
public class A_Nagai{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "Nagai";
	Scanner sc;
	private void run(){
		sc = new Scanner(System.in);
		int askNum = 50;
		parameterInit();
		for(int v=0; v<n; v++){

			int foremax = max; int foremin = min;
			int prevmax = 0; int prevmin = 0;
			int cnt1 = 10; int cnt2 = 0;

			for(int b=0; b<battleMax; b++){
				//=========数値の変更等を行う処理をここに書いてください========
				cnt1++; cnt2++;

				if(cnt1 > 10  && cnt2 > 3){
					if(foremax == askNum){
						askNum = foremin;
					}else{
						askNum = foremax;
					}
					cnt2 = 0;
				}else{
                	if((foremax + foremin) % 2 == 0) askNum = (foremax + foremin) / 2;
					else askNum = (foremax + foremin) / 2 + (int)(Math.random()*2);
					if(foremax - foremin > 4){
						askNum += ((int)(Math.random()*2) + 1) * ((int)(Math.random()*3) - 1);
					}
				}


				//========================================================



				//========================================================
				//攻撃側の予想数値
				System.out.println(askNum);
				//予想に対して相手の数字が大きいなら1,小さいなら-1,一致なら0
				int ud = sc.nextInt();
				if(ud == 0) break;
				//========================================================




				//=========数値の変更等を行う処理をここに書いてください========

                if(ud == 1){
					if(foremax == askNum){
						cnt1 = 2;
					}
                    foremin = askNum;
                    if(foremax <= foremin){
						foremax = max;
					}
				}else{
					if(foremin == askNum){
						cnt1 = 2;
					}
                    foremax = askNum;
                    if(foremax <= foremin){
						foremin = min;
					}
                }




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
		(new A_Nagai()).run();
	}
}