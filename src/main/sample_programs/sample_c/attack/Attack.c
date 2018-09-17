#include <stdio.h>
#include <stdlib.h>

void init();
int ask(int game,int round,int*** record);
int min;       //Minimum usable value (inclusive)
int max;       //Maximum usable value (inclusive)
int gameNum;   //Number of games
int roundNum;  //Maximum number of rounds per game
int changeLimit; //Minimum number of times to change
char playerName[] = "playerCAtk";
int ask(int game,int round,int*** record){
	return rand()%max+ min;
}
int main(void) {
	init();
	int ***record = malloc(sizeof(int **) * gameNum);
	for (int i=0; i<gameNum; i++) {
		record[i] = malloc(sizeof(int *) * roundNum);
		for(int j=0; j<2; j++) {
			record[i][j] = malloc(sizeof(int) * 2);
		}
	}
	for(int i=0; i<gameNum; i++){
		for(int j=0; j<roundNum; j++){
			int askNum = ask(i,j,record);
			record[i][j][0] = askNum;
			fprintf(stdout,"%d",askNum);
			int ud;
			scanf("%d",&ud);
			// 1 : if the predicted value is larger than the partner's number,
			//-1 : if it is smaller,
			// 0 : if it matches
			record[i][j][1] = ud;
			if(ud == 0) break;
		}
	}
	return 0;
}
void init() {
	scanf("%d",&min);
	scanf("%d",&max);
	scanf("%d",&gameNum);
	scanf("%d",&roundNum);
	scanf("%d",&changeLimit);
	fprintf(stdout, "%s\n", playerName);
}
