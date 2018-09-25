#include <iostream>

void parameterInit();
int min;       //使う数字の最小（含む）
int max;       //使う数字の最大（含む）
int n;         //game数
int battleMax; //対戦1回の最大
int changeConstraint; //変更を許す最低回数
std::string playerName = "playerCpp2";

int main(void)
{
	int askNum = 50;
	int inf = 1;
	int sup = 100;
	parameterInit();

	for(int v=0; v<n; v++){

		askNum = 50;
		for(int b=0; b<battleMax; b++){
			//=========数値の変更等を行う処理をここに書いてください========






			//========================================================

			//========================================================
			//攻撃側の予想数値
			std::cout << askNum << std::endl;
			//予想に対して相手の数字が大きいなら1,小さいなら-1,一致なら0
			int ud;
			std::cin >> ud;
			if(ud == 0) break;
			//========================================================

			//=========数値の変更等を行う処理をここに書いてください========


			if (ud > 0) {
				inf = askNum + 1;
				askNum = (inf + sup) / 2;
			} else {
				sup = askNum - 1;
				askNum = (inf + sup) / 2;
			}


			//========================================================
		}
	}
	return 0;
}
void parameterInit(){
	std::cin >> min;
	std::cin >> max;
	std::cin >> n;
	std::cin >> battleMax;
	std::cin >> changeConstraint;
	std::cout << playerName << std::endl;
}
