#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void init();
char *playerName = "A_CSample";

int main(void) {
	// ======= 使いたい変数を宣言 =======



	// ===============================
	init();
	int ***record = malloc(sizeof(int **) * 100);
	for (int i=0; i<100; i++) {
		record[i] = malloc(sizeof(int *) * 100);
		for(int j=0; j<100; j++) {
			record[i][j] = malloc(sizeof(int ) * 2);
		}
	}

	/** 二重ループの説明
	 * iのループ:第iゲームを実行する
	 * jのループ:第jラウンドを実行する
	 **/
	//srand((unsigned) time(NULL));
	for(int i = 0; i < 100; i++) {
		// ============== 書き換え箇所A ここから ===============



		// ============== 書き換え箇所A ここまで ===============

		for(int j = 0; j < 100; j++) {
			
			// ============== 書き換え箇所B ここから ===============
			// askNumに聞きたい数字を代入する．
			// sampleでは毎回ランダムに質問する．
			int askNum = rand() % 100 + 1;


			// ============== 書き換え箇所B ここまで ===============

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
	// パラメータを破棄する
	int tmp;
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	scanf("%d",&tmp);
	fprintf(stdout, "%s\n", playerName);
	fflush(stdout);
}
