import glob, os, subprocess , os.path
import queue, random
import traceback

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
    tmp = output.decode("utf-8")
    output = output.decode("utf-8").strip().split("\n")
    #print(output)
    if len(output) > 1:
        #print(output)
        try:
            output = output[-2].split(":")
            output = output[1]
            score[output] += 1000
        except Exception as e:
            print(playerA, playerB)
            print(e)
            traceback.print_exc()
            print(tmp)

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
    print("#",score)
    if score[playerA] < score[playerB]:
        return playerA
    elif score[playerA] > score[playerB]:
        return playerB
    else:
        if random.random() < 0.5:
            return playerA
        else:
            return playerB

    
if __name__ == "__main__":
    attack_run_command_list = read_run_command_list("resource/command_list/attack/attack_command_list_green.txt");
    defence_run_command_list = read_run_command_list("resource/command_list/defence/defence_command_list_green.txt");
    players = [key for key in attack_run_command_list.keys() if key in defence_run_command_list.keys()]

    dendo_iri = ["17D8104031D", "17D8101030E", "17D8103005H","17D8104005L","17D8103008B","17D8102009G"]
    for d in dendo_iri:
        players.remove(d)

    print(players)
    win_count = 0
    while win_count < 10:
        random.shuffle(players)
        q = queue.Queue()
        for player in players:
            q.put(player)

        is_best4 = False
        best4 = []
        loser = []    
        while(q.qsize() >= 2):
            if q.qsize() == 4:
                is_best4 = True
            playerA = q.get()
            playerB = q.get()
            #print(playerA, "vs", playerB)

            try:
                winner = battle(playerA, playerB, attack_run_command_list, defence_run_command_list)
            except Exception as e:
                print(playerA, playerB)
                print(e)
                traceback.print_exc()
                exit(0)
            q.put(winner)
            if winner == playerA:
                loser.append(playerB)
                if is_best4:
                    best4.append(playerB)
                #print("#", winner, playerB)
            elif winner == playerB:
                loser.append(playerA)
                if is_best4:
                    best4.append(playerA)
                #print("#", winner, playerA)

        if q.qsize() > 0:
            winner_player = q.get()
            #print("winner:",winner_player)
            best4.append(winner_player)
            print(best4)
            win_count+=1
        else:
            print("no winner")

        #players = loser