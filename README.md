# 二分探索ゲーム対戦用プラットフォーム
Copyright © 2018年 Hirano Keisuke. All rights reserved.  
作成日2018/5/18  
最終更新日2018/9/14  

### 今後の実装予定 09/14
- テスト実行に対してrunメソッドのタイムアウトを実装
---
## ___長文を読みたくない皆様へ___
長文を読みたくない皆様はここだけは読んでください．  
そうでない方は，`動作確認済み環境`以降を読んでいただければ大丈夫です．
### コンパイル
`javac src/main/java/platform/NumberGuessingGame.java`

### AIプログラムの準備
@TODO 修正
- src/main/sample_programs内の任意のプログラムをベースにAIを作成します． 
- サンプルは毎ラウンド攻撃側はランダム，防御側は一定値を返すだけのプログラムです． 
- 各プログラムのbet関数の中<u>__だけ__</u>を書き換えてください．  
- ファイル名を変えてください．A_から始まるものを攻撃側プログラム，D_から始まるものを防御側プログラムとして実行します．
  - javaの場合にはファイル名とクラス名を合わせるように気をつけてください．
- playerNameを書き換えてください．授業などで使う時には学籍番号などを推奨します．  
- __標準出力は使わないでください．__
  - デバッグなどで出力を使いたいときは標準エラー出力を使用してください．  

各サンプルのbet関数内で使える引数以外の変数は以下の通りです．

|変数名|説明|
|---|---|
|gameNum|ゲーム数|
|roundNum|ラウンド数(基本は10)|

bet関数の引数は

|変数名|説明|
|---|---|
|game|何回目のゲームか(0以上gameNum未満）|
|round|何回目のラウンドか(0以上roundNum未満）|


作ったプログラムはai_programに配置してください．ディレクトリごと配置しても構いません．

### 実行
1. `python src/main/python/auto_compile/auto_compile.py`
2. `java src.main.java.platform.NumberGuessingGame -test 10`
3. `java src.main.java.platform.NumberGuessingGame -auto false`

コンパイルエラーのプログラムは  
src/main/resource/log/auto_compile/compile_err_list.txt  
テスト実行時エラーは，  
src/main/resource/log/test_run_err/err.log  
対戦結果は,  
src/main/resource/resource/result/result.csv  
に出力されます．

--------------


## 動作確認済み環境
### Java
~~~
java version "1.8.0_162"
Java(TM) SE Runtime Environment (build 1.8.0_162-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.162-b12, mixed mode)
~~~ 
### Python
~~~
Python 3.6.3 |Anaconda, Inc.| (default, Oct  6 2017, 12:04:38) 
[GCC 4.2.1 Compatible Clang 4.0.1 (tags/RELEASE_401/final)] on darwin
~~~
### PC
~~~
MacBookPro 2017 macOS 10.13.4
~~~

### 補足
Java, Pythonのバージョン違いによる動作の可否は未確認です．
また，Windows環境での実行方法なども不明瞭ですが，よくある例として，Javaプログラムのコンパイル時にエンコードの指定を行わないとエラーになるなどがあります．
Linux等ではMacと同じように実行できると思います．

## コンパイル
`javac src/main/java/platform/NumberGuessingGame.java`


## 実行
`java src.main.java.platform.NumberGuessingGame -a "攻撃側実行コマンド" -d "防御側実行コマンド"`

`-a` の後ろにスペースを開けて実行コマンドを入力することで対戦に使用する攻撃側プレイヤーのプログラムを指定できます．  
`-d` の後ろにスペースを開けて実行コマンドを入力することで対戦に使用する防御側プレイヤーのプログラムを指定できます．  
具体的には　攻撃側にAttack.java,防御側にDefence.javaを指定するには，使用するプログラムをコンパイルしてから，  
`java BinarySearchGame -a "java Attack" -d "java Defence"`と実行します．  
C系言語の場合には,`"./a.out"`  
pythonの場合には，`"python Attack.py"`  
などを実行コマンドに使えます．  

### ゲーム設定の変更

|オプション|説明|default|
|-|-|-:|
|-game num|ゲーム数を`num`に指定|100|
|-round num|最大ラウンド数を`num`に指定|100|
|-c num|防御側が1度数値を変更してから再度変更できるまでの制限回数を`num`に変更|10|
|-min num|使用する数値の最小値(inclusive)を`num`に変更|1|
|-max num|使用する数値の最大値(inclusive)を`num`に変更|100|
|-olevel num|対戦結果の出力レベルを`num`に変更|1

#### 対戦結果の出力レベルについて 
|レベル|説明|
|-:|-|
|1|当てた回数と当てるまでの平均回数|
|2|レベル1の出力に加え，各ゲームでの結果|
|3|レベル2の出力に加え，各ラウンドの数値|

## 多対多実行について
自動で複数のプログラムで対戦を行う機能があります．  
コンパイル処理はPythonで実装しています．

### 手順概要
1. src/main/ai_programs/ に使用するプログラムを配置する．  
ディレクトリ内部を再帰的に探索して実行ファイルを取得するので，  
ディレクトリなども適当な構成で問題ありません．
2. pythonでの自動コンパイル
3. サンプルとの対戦により実行時エラーをふるいにかける
4. 対戦の実行

### 実行方法
2. 自動コンパイル  
`python src/main/python/auto_compile/auto_compile.py`

3. サンプルとの対戦  
`java src.main.java.platform.NumberGuessingGame -test 10`  
`-test`以下の数字はサンプルとの対戦のゲーム数です．  
既定の100回だと時間がかかるので適宜指定してください．

4. 対戦の実行  
`java src.main.java.platform.NumberGuessingGame -auto bool`  
`bool`には，trueかfalseを入れてください．
- true :サンプルを含めて対戦
- false:サンプルは含めず対戦

## AIのプログラムについて
### 前提として：対戦プラットフォームの仕組み
対戦はプラットフォームから二つの対戦用プログラムをサブプロセスとして起動し，  
それぞれの標準入出力を利用してデータのやりとりをします．

具体的には，はじめにゲーム数などのパラメータの初期化を行い，  
nゲーム*mラウンド分，ループを回して実装しています．

具体的な実装はサンプルのプログラムを読んでください．

### 言語
上記のように実装しているため，実行環境を用意すれば，  
どの言語でAIを実装しても動く想定です．

サンプルプログラムは以下の言語の物を用意しています.
- Java
- C++
- Python

C++のサンプルはほとんどCの実装になっているため，  
出力などを変えれば，簡単にCのプログラムに書き換えられると思います．

### サンプルプログラムの変更箇所について
サンプルプログラムの以下の関数（メソッド）を書き換えることで使用できます．  
それ以外の部分を書き換えると，うまく動かない可能性があります．
#### 攻撃側:ask
askの引数は以下の通りです．
- game      :何回目のゲームか(0以上gameNum未満）
- round     :何回目のラウンドか(0以上roundNum未満）
- record    :gameNum × roundNum × 2の3次元配列．
  - record[i][j][0]はiゲーム目のjラウンドに自分が聞いた数
  - record[i][j][1]はiゲーム目のjラウンドに自分が聞いた数が相手の数より上なら1，下なら-1，一致したなら0が入力されています．

#### 防御側:myNumber
myNumberの引数は以下の通りです．
- game      :何回目のゲームか(0以上gameNum未満）
- round     :何回目のラウンドか(0以上roundNum未満）
- record    :gameNum × roundNum × 2の3次元配列．
  - record[i][j][0]はiゲーム目のjラウンドに自分が出した数．
  - record[i][j][1]はiゲーム目のjラウンドに相手が聞いた数．

#### 参照可能な共通の変数
|変数名      |説明                  |
|-----------|----------------------|
|gameNum    |ゲーム数               |
|roundNum   |最大ラウンド数          |
|min        |使用可能な最小値        |
|max        |使用可能な最大値        |
|changeLimit|防御側の数値変更の制限回数|

各言語のサンプルにおいて，bet関数(メソッド)の中を書き換えてください．  


また，playerNameとクラス名のNameに当たる部分の書き換えもお願いします．  
授業などで使う時には学籍番号などを指定するなど，運用は一任します．

__標準出力は使わないでください．__
出力を行いたい場合には，標準エラー出力（System.err.print(),std::cerr）などを使用してください．
