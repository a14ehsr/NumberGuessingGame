import java.util.Scanner;
public class A_Yoshida{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "Yoshida";
	Scanner sc;
	private void run(){
		sc = new Scanner(System.in);
		int askNum = 50;
		parameterInit();
		int nmin=0,nmax=0;
		for(int v=0; v<n; v++){


			for(int b=0; b<battleMax; b++){
				//=========数値の変更等を行う処理をここに書いてください========

				if(b==0){
					askNum=50;
					nmin=min;
					nmax=max;
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

				if (ud == 1) {
					nmin = askNum;
					if (b == 4) {
					    nmax = max;
					}
				}
				else {
					nmax = askNum;
					if (b == 4) {
					    nmin = min;
					}
				}
				if (askNum == (nmin + nmax) / 2) {
					if (ud == 1) {
						nmin = askNum;
						nmax = max;
					}
					else {
						nmax = askNum;
						nmin = min;
					}
				}
				if (askNum == 99 && nmax == 100) {
					askNum = 100;
				}
				else if(Math.abs(nmax-nmin)<=1){
					if (ud == 1) {
						nmin = askNum;
						nmax = max;
					}
					else {
						nmax = askNum;
						nmin = min;
					}
					askNum = (nmin + nmax) / 2;
				}else{
					askNum = (nmin + nmax) / 2;
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
		(new A_Yoshida()).run();
	}
}
