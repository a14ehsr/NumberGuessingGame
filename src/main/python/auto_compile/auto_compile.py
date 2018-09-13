# 対戦用AIプログラムの自動コンパイル
# コンパイルエラーにならないものと，コンパイル不要言語のプログラムの実行コマンドをリストにして出力
# コンパイルエラーのファイルはlogに出力
import glob, os, subprocess , os.path

def find_all_files(directory):
    for root, dirs, files in os.walk(directory):
        yield root
        for file in files:
            yield os.path.join(root, file)

path = 'src/main/resource/command_list/'
attack_cmd_file = open(path+'attack/attack_command_list.txt', mode='w')
defence_cmd_file = open(path+'defence/defence_command_list.txt', mode='w')
errf = open('src/main/resource/log/auto_compile/compile_err_list.txt', mode = 'w')
cmdlist = []
for file in find_all_files('./src/main/ai_program'):
	root, ext = os.path.splitext(file)
	directory, fname = os.path.split(root)

	if fname.startswith("A_"):
		output_file = attack_cmd_file
	elif fname.startswith("D_"):
		output_file = defence_cmd_file

	if ext == '.java':
		cmd = 'javac ' + file
		err = subprocess.call(cmd, shell=True)
		if err == 0:
			runcmd = 'java -classpath '+ directory + ' ' + fname
			output_file.write(runcmd + '\n')
			#f.write(runcmd+'\n')
		else:
			errf.write('COMPILE_ERROR '+file+'\n')
	elif ext in {'.cpp', '.c'}:
		cmd = 'g++-7 ' + file + " -o " + root
		err = subprocess.call(cmd, shell=True)
		if err == 0:
			runcmd = root
			output_file.write(runcmd + '\n')
		else:
			errf.write('COMPILE_ERROR '+file+'\n')
	elif ext == '.py':
		runcmd = 'python ' + file
		output_file.write(runcmd + '\n')
