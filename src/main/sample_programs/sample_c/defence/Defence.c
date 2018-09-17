#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void init();
int myNumber(int game,int round,int*** record);
char *playerName = "playerCDefence";


int myNumber(int game,int round,int*** record){
	if(round == 0){
		return rand()%100 + 1;
	}else{
		return record[game][round - 1][0];
	}
}
int main(void) {
	srand((unsigned) time(NULL));
	init();
	int ***record = malloc(sizeof(int **) * 100);
	for (int i=0; i<100; i++) {
		record[i] = malloc(sizeof(int *) * 100);
		for(int j=0; j<100; j++) {
			record[i][j] = malloc(sizeof(int ) * 2);
		}
	}
	for(int i=0; i<100; i++){
		for(int j=0; j<100; j++){
			int myNum = myNumber(i,j,record);
			record[i][j][0] = myNum;
			fprintf(stdout,"%d\n",myNum);
			fflush(stdout);
			int num;
			scanf("%d",&num);
			record[i][j][1] = num;
			if(myNum == num) break;
		}
	}
	return 0;
}
void init() {
	int tmp;
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	fprintf(stdout, "%s\n", playerName);
	fflush(stdout);
}