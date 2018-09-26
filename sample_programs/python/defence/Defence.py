import sys
import random

playerName = "playerDef"
min = int(sys.stdin.readline())
max = int(sys.stdin.readline())
gameNum = int(sys.stdin.readline())
roundNum = int(sys.stdin.readline())
changeLimit = int(sys.stdin.readline())
print(playerName, flush=True)
record = [[[0 for k in range(2)] for i in range(roundNum)] for j in range(gameNum)]

def myNumber(game,round,record):
	if round == 0:
		return random.randrange(min,max+1)
	return record[game][round-1][0]


for i in range(gameNum):
	for j in range(roundNum):
		myNum = myNumber(i,j,record);
		record[i][j][0] = myNum;
		print(myNum,flush=True)
		tmp = int(sys.stdin.readline())
		record[i][j][1] = tmp;
		if myNum == tmp :
			break


