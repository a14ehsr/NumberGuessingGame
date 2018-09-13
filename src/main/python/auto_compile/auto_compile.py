# 対戦用AIプログラムの自動コンパイル
# コンパイルエラーにならないものと，コンパイル不要言語のプログラムの実行コマンドをリストにして出力
# コンパイルエラーのファイルはlogに出力
import glob, os, subprocess , os.path

def find_all_files(directory):
    for root, dirs, files in os.walk(directory):
        yield root
        for file in files:
            yield os.path.join(root, file)

cmdf = open('src/main/resource/command_list/run_command_list.txt', mode='w')
errf = open('src/main/resource/log/auto_compile/compile_err_list.txt', mode = 'w')
cmdlist = []
for file in find_all_files('./src/main/ai_program'):
	root, ext = os.path.splitext(file)
	directory, fname = os.path.split(root)
	if ext == '.java':
		cmd = 'javac ' + file
		err = subprocess.call(cmd, shell=True)
		if err == 0:
			runcmd = 'java -classpath '+ directory + ' ' + fname
			cmdf.write(runcmd + '\n')
			#f.write(runcmd+'\n')
		else:
			errf.write('COMPILE_ERROR '+file+'\n')
	elif ext in {'.cpp', '.c'}:
		cmd = 'g++-7 ' + file + " -o " + root
		err = subprocess.call(cmd, shell=True)
		if err == 0:
			runcmd = root
			cmdf.write(runcmd + '\n')
		else:
			errf.write('COMPILE_ERROR '+file+'\n')
	elif ext == '.py':
		runcmd = 'python ' + file
		cmdf.write(runcmd + '\n')
