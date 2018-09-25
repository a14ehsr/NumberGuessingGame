import java.util.Scanner;
public class A_Adachi{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "Adachi";
	Scanner sc;
	private void run(){
		sc = new Scanner(System.in);
		int askNum = 50;
		parameterInit();
		for(int v=0; v<n; v++){
            int min2=min;
			int max2=max;
			int cnt=0;
			int tmp=-1;
			for(int b=0; b<battleMax; b++){
				//=========数値の変更等を行う処理をここに書いてください=======

				//askNum = (int)(Math.random()*(max2+1-min2))+ min2;
				if(max2==min2) askNum=max2;
				else if((max2-min2)%2==0) askNum=(max2+min2)/2;
				else{
					do{
						askNum=(min2+max2)/2+(int)(Math.random()*2);
					}while(tmp==askNum);
				}
				tmp=askNum;
				//攻撃側の予想数値
				System.out.println(askNum);
				//予想に対して相手の数字が大きいなら1,小さいなら-1,一致なら0
				int ud = sc.nextInt();
				if(ud==1){
					min2=askNum;
				}else if(ud==-1){
					max2=askNum;
				}else{
					break;
				}
				if(cnt==7||b==4){ cnt=0; max2=max; min2=min; }
				else cnt++;
				
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
		(new A_Adachi()).run();
	}
}
