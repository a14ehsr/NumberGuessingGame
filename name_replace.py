import os
import re

# ai_programs内のplayerName設定部分のgrepコマンド
# grep -r "playerName\s*=\s*" ai_programs/*

# directory内のファイルを再帰的に取得
def find_all_files(directory):
    for root, dirs, files in os.walk(directory):
        yield root
        for file in files:
            yield os.path.join(root, file)

# 内部文字列置換処理
def rename(file):
	root, ext = os.path.splitext(file)
	# 対象ファイル拡張子以外スキップ
	if ext != ".cpp" and ext != ".java" and ext != ".py":
		return

	# パスから学籍番号をパース
	directory, fname = os.path.split(root)
	directory = directory.split("/")
	directory = directory[len(directory)-1].split("@")[0]

	#print(directory)

	# 正規表現使って対象文字列を検索，置換してlinesに代入
	lines = ""
	with open(file, "r") as f:
		lines = f.read()
		lines = re.sub(r"playerName\s*=\s*[\",\'].*[\",\']", f"playerName = {directory}", lines)
			
	# 書き込み
	with open(file, "w") as f:
		f.write(lines)

# 対象ファイルに対して実行
for file in find_all_files('./ai_programs/attack'):
	rename(file)
for file in find_all_files('./ai_programs/defence'):
	rename(file)

