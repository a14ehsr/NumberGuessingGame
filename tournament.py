import glob, os, subprocess , os.path

run_command = "java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -vs_result true"
run_command_list = {}

def read_run_command_list(filepath) {
    run_command_list = {}
    f = open(filepath)
    for line in f.readlines():
        tmp = line[:12].replace(" ", "") # 学籍番号取得
        run_command_list[tmp] = line[:12]
    return run_command_list
}

def battle(playerA, commandA, attack_run_command_list, defence_run_command_list):
    score = {playerA: 0, playerB: 0}
    output = subprocess.check_output(f"{run_command} -a {attack_run_command_list[playerA]} -b {defence_run_command_list[playerB]}", shell=True)
    output = output.decode("utf-8").strip().split("\n")
    if len(output) > 1:
        output = output[-2].split(":")
        output = output[1]
        score[output] += 1000
    else:
        score[playerA] += float(output)

    output = subprocess.check_output(f"{run_command} -a {attack_run_command_list[playerB]} -b {defence_run_command_list[playerA]}", shell=True)
    output = output.decode("utf-8").strip().split("\n")
    if len(output) > 1:
        output = output[-2].split(":")
        output = output[1]
        score[output] += 1000
    else:
        score[playerB] += float(output)

    


if __name__ == "__main__":
    attack_run_command_list = read_run_command_list("resource/command_list/attack/attack_command_list_green");
    defence_run_command_list = read_run_command_list("resource/command_list/defence/defence_command_list_green");
    battle("0001A", "0002B")
