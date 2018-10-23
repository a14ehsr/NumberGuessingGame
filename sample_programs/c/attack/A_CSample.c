#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void init();
char *playerName = "A_CSample";

int main(void) {
	//srand((unsigned) time(NULL));
	init();
	int ***record = malloc(sizeof(int **) * 100);
	for (int i=0; i<100; i++) {
		record[i] = malloc(sizeof(int *) * 100);
		for(int j=0; j<100; j++) {
			record[i][j] = malloc(sizeof(int ) * 2);
		}
	}
	for(int i = 0; i < 100; i++) {
		for(int j = 0; j < 100; j++) {
			// ============== 書き換え箇所 ここから ===============
			// askNumに聞きたい数字を代入する．
			// sampleでは毎回ランダムに質問する．
			int askNum = rand() % 100 + 1;

			// ============== 書き換え箇所 ここまで ===============
			record[i][j][0] = askNum;
			fprintf(stdout,"%d\n",askNum);
			fflush(stdout);
			int ud;
			scanf("%d",&ud);
			// -1 : if the predicted value is larger than the partner's number.
			//  1 : if it is smaller.
			//  0 : if it matches.
			record[i][j][1] = ud;
			if(ud == 0) break;
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
