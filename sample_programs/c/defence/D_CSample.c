#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void init();
char *playerName = "D_CSample";

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
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 100; j++) {
			// ============== 書き換え箇所 ここから ===============
			// myNumに数字を代入する．
			// sampleでは最初のラウンドでランダムに入力し，それ以降は常に同じ数字とする．
			int myNum = rand() % 100 + 1;
			if (j != 0) {
				myNum = record[i][j - 1][0];
			}

			// ============== 書き換え箇所 ここまで ===============
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