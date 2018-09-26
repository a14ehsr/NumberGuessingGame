#include <iostream>
void init();
int ask(int game,int round,int*** record);
int min;       //Minimum usable value (inclusive)
int max;       //Maximum usable value (inclusive)
int gameNum;   //Number of games
int roundNum;  //Maximum number of rounds per game
int changeLimit; //Minimum number of times to change
std::string playerName = "playerCppAtk";
int ask(int game,int round,int*** record){
	return rand()%max+ min;
}
int main(void) {
	init();
	int ***record = new int**[gameNum];
	for(int i=0; i<gameNum; i++){
		record[i] = new int*[roundNum];
		for(int j=0; j<roundNum; j++){
			record[i][j] = new int[2];
		}
	}
	for(int i=0; i<gameNum; i++){
		for(int j=0; j<roundNum; j++){
			int askNum = ask(i,j,record);
			record[i][j][0] = askNum;
			std::cout << askNum << std::endl;
			int ud;
			std::cin >> ud;
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
	std::cin >> min;
	std::cin >> max;
	std::cin >> gameNum;
	std::cin >> roundNum;
	std::cin >> changeLimit;
	std::cout << playerName << std::endl;
}
