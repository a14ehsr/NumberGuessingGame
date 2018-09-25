/*2018年6月1日 ver1.3*/
import java.util.Scanner;
import java.util.Arrays;
public class D_Hashimoto{
	int min;       //使う数字の最小（含む）
	int max;       //使う数字の最大（含む）
	int n;         //game数
	int battleMax; //対戦1回の最大
	int changeConstraint; //変更を許す最低回数
	String playerName = "Hashimoto";
	Scanner sc;
	private void run(){
		int myNum;

		parameterInit();
		//int max_b=0,max_i=0;
		int fir[] = new int[n];
		int tail=0;
		int flag[] = new int[101];
		int over=1000;

		Arrays.fill(flag,0);

		for(int v=0; v<n; v++){
			//========各試合の初期値の設定============
			///*exameple*/ myNum = rand()%max+ min;
			int cn=0,f=0,f_num=0;
			int o=0;
			if((v>=3*n/10)&&tail>2){
				myNum=fir[(int)(Math.random()*tail)];
			}else{
				while(true){
					o++;
					myNum=(int)(Math.random()*max)+min;
					if((flag[myNum]==0)&&(myNum%25!=0)) break;
					if(o>over) myNum=(int)(Math.random()*max)+min;
				}
			}
			//=====================================


			for(int b=0; b<battleMax; b++){
				//========================================================
				//自分の数値
				System.out.println(myNum);
				//攻撃側の予想数値
				int num = sc.nextInt();
				if(b==0) f_num=myNum;
				if(myNum == num){
					if(b>=16){
						fir[tail]=f_num;
						tail++;
						flag[f_num]=0;
					}else if(b<10){
						flag[f_num]=1;
					}
					break;
				}
				//========================================================

				//=========数値の変更等を行う処理をここに書いてください========
				cn++;
				if(cn==changeConstraint){
					f=0;
				}
				if(f==0){
                    if(0<=myNum-num&&myNum-num<=3){
                        myNum=num-1;
						if(myNum-1>=min) myNum--;
                        if(myNum<min) myNum=num;
                        cn=0;
					    f++;
                    }else if(0<=num-myNum&&num-myNum<=3){
                        myNum=num+1;
						if(myNum+1<=max) myNum++;
                        if(myNum>max) myNum=num;
                        cn=0;
					    f++; 
                    }
				}
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
		(new D_Hashimoto()).run();
	}
}
