import sys
import random
import copy

def jump_myNum(myNum,tmp):
    if tmp<myNum:#ue
        myNum-=random.randint(15,34)
        if myNum<0:
            myNum = random.randint(35,65)
    elif tmp>myNum:#sita
        myNum+=random.randint(15,34)
        if myNum>100:
            myNum = random.randint(35,65)
    
    return myNum

def jump_rev_myNum(myNum,tmp):
    if tmp>myNum:#ue
        myNum-=random.randint(15,34)
        if myNum<0:
            myNum = random.randint(35,65)
    elif tmp<myNum:#sita
        myNum+=random.randint(15,34)
        if myNum>100:
            myNum = random.randint(35,65)
    
    return myNum

playerName = "Masuda"
min = int(sys.stdin.readline())
#print(min)
max = int(sys.stdin.readline())
n = int(sys.stdin.readline())
battleMax = int(sys.stdin.readline())
changeConstraint = int(sys.stdin.readline())

myNum = 10
count=0
print(playerName, flush=True)
#sys.stderr.write(playerName)
#enemy_game_data=[[0 for i in range(n)] for j in range(battleMax)]
enemy_round_data=[]
enemy_first_data=[]
last_game=[]

for i in range(n):
    count=0
    first_jump=0
    checking=False
    myNum = random.randint(35,65)
    enemy_round_data.clear()
    for j in range(battleMax):
        count+=1
        
        print(myNum,flush=True)            
        tmp = int(sys.stdin.readline())

        if j==0:
            enemy_first_data.append(tmp)

        if myNum == tmp :
            last_game=copy.deepcopy(enemy_round_data)
            break

        if count>=10:
            myNum=jump_myNum(myNum,tmp)
            count=0
        

        enemy_round_data.append(tmp)

        if first_jump==0 and i<1:      
            myNum=jump_myNum(myNum,tmp)          
            first_jump=1
            count=0

        if i>0:
            diff=abs(tmp-myNum)
            if diff<=10:
                if first_jump==0:
                    myNum=jump_myNum(myNum,tmp)
                    first_jump=1
                    count=0

            for k in last_game:#judge_checking
                check_counter=last_game.count(k)
                if check_counter>1:
                    checking=True
            
            if checking==True:
                if first_jump==0 and i<1: 
                    myNum=jump_rev_myNum(myNum,tmp)               
                    first_jump=1
                    count=0
            else:
                if first_jump==0:
                    myNum=jump_myNum(myNum,tmp)
                    first_jump=1
                    count=0             
            


