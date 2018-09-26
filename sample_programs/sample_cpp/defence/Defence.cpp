#include <iostream>
void init();
int ask(int game,int round,int*** record);
int min;       //Minimum usable value (inclusive)
int max;       //Maximum usable value (inclusive)
int gameNum;   //Number of games
int roundNum;  //Maximum number of rounds per game
int changeLimit; //Minimum number of times to change
std::string playerName = "playerCppAtk";
int myNumber(int game,int round,int*** record){
	if(round == 0){
		return rand()%max + min;
	}else{
		return record[game][round - 1][0];
	}
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
			int myNum = myNumber(i,j,record);
			record[i][j][0] = myNum;
			std::cout << myNum << std::endl;
			int num;
			std::cin >> num;
			record[i][j][1] = num;
			if(myNum == num) break;
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
