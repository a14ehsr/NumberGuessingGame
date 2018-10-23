import sys
import random

playerName = "A_PythonSample"
min = int(sys.stdin.readline())
max = int(sys.stdin.readline())
gameNum = int(sys.stdin.readline())
roundNum = int(sys.stdin.readline())
changeLimit = int(sys.stdin.readline())
print(playerName, flush=True)
record = [[[0 for k in range(2)] for i in range(roundNum)] for j in range(gameNum)]

def ask(game,round,record):
	return random.randrange(min,max+1)


for g in range(gameNum):
	for r in range(roundNum):
		# ============== 書き換え箇所 ここから ===============
		# askNumに聞きたい数字を代入する．
		# sampleでは毎回ランダムに質問する．
		askNum = random.randrange(min, max+1)




		# ============== 書き換え箇所 ここまで ===============
		record[g][r][0] = askNum
		print(askNum,flush=True)
		# -1 : if the predicted value is larger than the partner's number.
		#  1 : if it is smaller.
		#  0 : if it matches.
		ud = int(sys.stdin.readline())
		record[g][r][1] = ud;
		if ud == 0 :
			break
