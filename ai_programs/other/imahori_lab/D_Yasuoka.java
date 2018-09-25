import java.util.*;
public class D_Yasuoka{
	int min;       //gij
	int max;       //gij
	int n;         //game
	int battleMax; //1
	int changeConstraint; //X
	String playerName = "Yasuoka";
	Scanner sc;
	private void run(){
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b0 = new ArrayList<Integer>();
		for(int i=0; i<76; i=i+25){
			a.add(i+1); a.add(i+2); a.add(i+4); a.add(i+5);
			a.add(i+8); a.add(i+11); a.add(i+14); a.add(i+17);
			a.add(i+20); a.add(i+21); a.add(i+23); a.add(i+24);
		}
		a.remove(a.indexOf(51));
		a.add(25); a.add(75);
		for(int i=1; i<101; i++){
			if(!(a.contains(i))) b0.add(i);
		}

		Random rnd = new Random();

		int myNum;
		int newMyNum, oldNum, d, move, cnt, assap, r;

		parameterInit();

		for(int v=0; v<n; v++){
			//========el============
			///*exameple*/ myNum = rand()%max+ min;
			myNum = 50;
			r = rnd.nextInt(100)+1;
			if(r>50) myNum = 52;
			else myNum = 49;
			newMyNum=0; oldNum=0; d=0; move=1; cnt=0; assap=0; r=0;
			//=====================================


			for(int b=0; b<battleMax; b++){
				//========================================================
				//l
				System.out.println(myNum);
				//U\zl
				int num = sc.nextInt();
				if(myNum == num) break;
				//========================================================

				//=========lXs========
				cnt++;
	      if((myNum-num)>0){
	        d=1;
	        if(cnt>9) move=1;
	      }else{
	        d=-1;
	        if(cnt>9) move=1;
	      }
	      if(oldNum==num || a.contains(num) || assap==1){
	        if(move==1){
	          newMyNum = rnd.nextInt(49);
	          if(d==1){
	            while(a.get(newMyNum)<myNum || a.get(newMyNum)==25 || a.get(newMyNum)==75) newMyNum = rnd.nextInt(49);
	          }else{
	            while(a.get(newMyNum)>myNum || a.get(newMyNum)==25 || a.get(newMyNum)==75) newMyNum = rnd.nextInt(49);
	          }
	          myNum = a.get(newMyNum);
	          move=0; cnt=0; assap=0;
	        }else{
	          assap=1;
	        }
	      }
				oldNum = num;
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
		(new D_Yasuoka()).run();
	}
}
