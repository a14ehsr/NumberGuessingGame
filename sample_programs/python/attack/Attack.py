import sys
import random

playerName = "playerAtk"
min = int(sys.stdin.readline())
max = int(sys.stdin.readline())
gameNum = int(sys.stdin.readline())
roundNum = int(sys.stdin.readline())
changeLimit = int(sys.stdin.readline())
print(playerName, flush=True)
record = [[[0 for k in range(2)] for i in range(roundNum)] for j in range(gameNum)]

def ask(game,round,record):
	return random.randrange(min,max+1)


for i in range(gameNum):
	for j in range(roundNum):
		askNum = ask(i,j,record)
		record[i][j][0] = askNum;
		print(askNum,flush=True)
		# 1 : if the predicted value is larger than the partner's number,
		#-1 : if it is smaller, 
		# 0 : if it matches
		ud = int(sys.stdin.readline())
		record[i][j][1] = ud;
		if ud == 0 :
			break