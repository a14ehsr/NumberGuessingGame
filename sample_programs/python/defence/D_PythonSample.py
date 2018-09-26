import sys
import random

playerName = "D_PythonSample"
min = int(sys.stdin.readline())
max = int(sys.stdin.readline())
gameNum = int(sys.stdin.readline())
roundNum = int(sys.stdin.readline())
changeLimit = int(sys.stdin.readline())
print(playerName, flush=True)
record = [[[0 for k in range(2)] for i in range(roundNum)] for j in range(gameNum)]

for g in range(gameNum):
	for r in range(roundNum):
		# ============== 書き換え箇所 ここから ===============
		# myNumに数字を代入する．
		# sampleでは最初のラウンドでランダムに入力し，それ以降は常に同じ数字とする．
		myNum = random.randrange(min, max+1)
		if r != 0 :
			myNum = record[g][r - 1][0]



		# ============== 書き換え箇所 ここまで ===============
		record[g][r][0] = myNum;
		print(myNum,flush=True)
		tmp = int(sys.stdin.readline())
		record[g][r][1] = tmp;
		if myNum == tmp :
			break
