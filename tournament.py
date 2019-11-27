import glob, os, subprocess , os.path
import queue, random

run_command = "java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -vs_result true"
run_command_list = {}

def read_run_command_list(filepath):
    run_command_list = {}
    f = open(filepath)
    for line in f.readlines():
        tmp = line[:11].replace(" ", "") # 学籍番号取得
        run_command_list[tmp] = line[12:].strip()
    return run_command_list

def battle(playerA, playerB, attack_run_command_list, defence_run_command_list):
    score = {playerA: 0, playerB: 0}
    output = subprocess.check_output(f"{run_command} -a \"{attack_run_command_list[playerA]}\" -d \"{defence_run_command_list[playerB]}\"", shell=True)
    output = output.decode("utf-8").strip().split("\n")
    if len(output) > 1:
        output = output[-2].split(":")
        output = output[1]
        score[output] += 1000
    else:
        score[playerA] += float(output[0])

    output = subprocess.check_output(f"{run_command} -a \"{attack_run_command_list[playerB]}\" -d \"{defence_run_command_list[playerA]}\"", shell=True)
    output = output.decode("utf-8").strip().split("\n")
    if len(output) > 1:
        output = output[-2].split(":")
        output = output[1]
        score[output] += 1000
    else:
        score[playerB] += float(output[0])
    
    if score[playerA] < score[playerB]:
        return playerA
    elif score[playerA] > score[playerB]:
        return playerB
    else:
        return ""

    
if __name__ == "__main__":
    attack_run_command_list = read_run_command_list("resource/command_list/attack/attack_command_list_green.txt");
    defence_run_command_list = read_run_command_list("resource/command_list/defence/defence_command_list_green.txt");
    players = [key for key in attack_run_command_list.keys() if key in defence_run_command_list.keys()]
    print(players)
    win_count = 0
    while win_count < 1:
        random.shuffle(players)
        q = queue.Queue()
        for player in players:
            q.put(player)

        loser = []    
        while(q.qsize() >= 2):
            playerA = q.get()
            playerB = q.get()

            winner = battle(playerA, playerB, attack_run_command_list, defence_run_command_list)

            q.put(winner)
            if winner == playerA:
                loser.append(playerB)
            elif winner == playerB:
                loser.append(playerA)

        if q.qsize() > 0:
            print("winner:",q.get())
            win_count+=1
        else:
            print("no winner")

        players = loser