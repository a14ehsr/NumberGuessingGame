# 数当てゲーム対戦用プラットフォーム
Copyright © 2018年 K.Hirano All rights reserved.  
作成日2018/5/18  
最終更新日2018/9/26  

## 0. 数当てゲームとは

## 1. このリポジトリをcloneしよう
### 1.1 clone
ターミナルで以下を実行  
`git clone https://github.com/a14ehsr/NumberGuessingGame.git`

もしくはdownload zipからダウンロードしてホームディレクトリ上に解凍してください．  

### 1.2 作業ディレクトリを移動しよう
ターミナルで以下を実行  
`cd NumberGuessingGame`


## 2. AIプログラムの準備
### 2.1 サンプルプログラム
sample_programs/c/にサンプルプログラムを用意しています．  
以下を実行してカレントディレクトリにコピーを作成しましょう．  
`cp sample_programs/c/attack/A_CSample.c A_自分の名前.c`  
`cp sample_programs/c/defence/D_CSample.c D_自分の名前.c`  
自分の名前にはローマ字などで適宜自分の名字などを入力してください．

### 2.2 AIの作り方．
playerNameを書き換えてください．授業などで使う時には何かしらの統一を推奨します．  
サンプルの指定された部分を修正して，設計したAIを実装しましょう．  
作ったらコンパイルしましょう．

#### __注意事項__
デバッグなどの出力を行いたい場合には，標準エラー出力（fprintf(stderr,"str")）を使用してください．

## 3. 対戦環境の準備
### 3.1 コンパイル
以下の魔法の呪文を実行してください．  
`sh compile.sh`

### 3.2 サンプルと戦わせる
サンプルプログラムと対戦させます．  
以下のコマンドを実行してください.  
攻撃側の場合:`sh battle_sample.sh attack "実行コマンド`  
防御側の場合:`sh battle_sample.sh defence "実行コマンド`  
実行コマンドには`./a.out`などを入力してください．  

サンプルは以下の内容です．

#### 攻撃側
|名前        |戦略                      |
|-----------|-------------------------|
|A_RandomAsk|毎ラウンドランダムな数字を訊く|
|A_SameAsk  |毎ラウンド常に同じ数字を訊く  |

#### 防御側
|名前            |戦略                         |
|---------------|----------------------------|
|D_RandomDeclare|10ラウンド毎にランダムな数字に動く|
|D_SameDeclare  |常に同じ数字を宣言し続ける       |

### AIプログラムの準備の補足
3次元配列recordを使えるようにしています．  
これは，以下の内容を記録しています．  

- 攻撃側 
  - record[i][j][0]はiゲーム目のjラウンドに自分が聞いた数
  - record[i][j][1]はiゲーム目のjラウンドに自分が聞いた数が相手の数より上なら1，下なら-1，一致したなら0が入力されています
- 防御側
  - record[i][j][0]はiゲーム目のjラウンドに自分が出した数
  - record[i][j][1]はiゲーム目のjラウンドに相手が聞いた数


--------------

# 運営担当者向けの細かな補足
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
また，Windows環境での実行方法なども不明瞭ですが，よくある例として，  
Javaプログラムのコンパイル時にエンコードの指定を行わないとエラーになる，などがあります．

## コンパイル

`sh compile.sh`の内容は以下を実行する形になっています．  

~~~
cd java/src/

javac ac/a14ehsr/platform/NumberGuessingGame.java

javac ac/a14ehsr/sample_ai/attack/A_RandomAsk.java
javac ac/a14ehsr/sample_ai/attack/A_SameAsk.java
javac ac/a14ehsr/sample_ai/defence/D_RandomDeclare.java
javac ac/a14ehsr/sample_ai/defence/D_SameDeclare.java
~~~

## 実行
~~~
java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -a "攻撃側実行コマンド" -d "防御側実行コマンド"
~~~

`-a` の後ろにスペースを開けて実行コマンドを入力することで対戦に使用する攻撃側プレイヤーのプログラムを指定できます．  
`-d` の後ろにスペースを開けて実行コマンドを入力することで対戦に使用する防御側プレイヤーのプログラムを指定できます．  
具体的には　攻撃側にAttack.java,防御側にDefence.javaを指定するには，使用するプログラムをコンパイルしてから，  
`java BinarySearchGame -a "java Attack" -d "java Defence"`と実行します．  
C系言語の場合には,`"./a.out"`  
pythonの場合には，`"python Attack.py"`  
などを実行コマンドに使えます．  
細かなオプションを使用しない場合には以下のコマンドでも実行できます

~~~
sh battle.sh "攻撃側実行コマンド" "防御側実行コマンド"
~~~

### ゲーム設定の変更

|オプション|説明|default|
|--------|---|--:|
|-game num|ゲーム数を`num`に指定|100|
|-round num|最大ラウンド数を`num`に指定|100|
|-c num|防御側が1度数値を変更してから再度変更できるまでの制限回数を`num`に変更|10|
|-min num|使用する数値の最小値(inclusive)を`num`に変更|1|
|-max num|使用する数値の最大値(inclusive)を`num`に変更|100|
|-olevel num|対戦結果の出力レベルを`num`に変更|1

#### 対戦結果の出力レベルについて 
|レベル|説明|
|-----:|-----|
|1|当てた回数と当てるまでの平均回数|
|2|レベル1の出力に加え，各ゲームでの結果|
|3|レベル2の出力に加え，各ラウンドの数値|

## 多対多実行について
複数のプログラムで自動対戦を行う機能があります．  
コンパイル処理はPythonで実装しています．

### 手順概要
1. `ai_programs/`に使用するプログラムを配置する．  
  - 攻撃側AIなら`ai_programs/attack/`以下に
  - 防御側AIなら`ai_programs/defence/`以下に
  - プログラム名が"サンプルプログラムについて"にある通りの規則に従っているなら`ai_programs/other/`以下に
  - ディレクトリ内部を再帰的に探索して実行ファイルを取得するので，ディレクトリなどは適当な構成で問題ありません．
2. pythonでの自動コンパイル
3. サンプルとの対戦により実行時エラーをふるいにかける
4. 対戦の実行

### 実行方法
2. 自動コンパイル  
`python python/src/auto_compile/auto_compile.py`  
もしくは  
`sh auto_compile.sh`

3. サンプルとの対戦  
`java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -test 10`  
`-test`以下の数字はサンプルとの対戦のゲーム数です．  
既定の100回だと時間がかかるので適宜指定してください．  
もしくは
`sh test.sh`

4. 対戦の実行  
`java src.main.java.platform.NumberGuessingGame -auto bool`  
もしくは  
`sh auto_run.sh`  
`bool`には，trueかfalseを入れてください．  
  - ~~true :サンプルを含めて対戦~~  
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

- C
- C++
- Java
- Python

##  AIプログラムの準備
`sample_programs/`内のサンプルプログラムの指定した箇所を書き換えてください．  
それ以外の部分を書き換えると，うまく動かない可能性があります．

### 指定箇所以外の変更について
- ファイル名を変えてください．`A_`から始まるものを攻撃側プログラム，`D_`から始まるものを防御側プログラムとして実行します．
  - javaの場合にはファイル名とクラス名を合わせるように気をつけてください．
- playerNameを書き換えてください．授業などで使う時には学籍番号などを推奨します．  

### __注意事項__
__標準出力は使わないでください．__  
出力を行いたい場合には，標準エラー出力（System.err.print(),std::cerr）などを使用してください．


### 参照可能な共通の変数
|変数名      |説明                  |
|-----------|----------------------|
|gameNum    |ゲーム数               |
|roundNum   |最大ラウンド数          |
|min        |使用可能な最小値        |
|max        |使用可能な最大値        |
|changeLimit|防御側の数値変更の制限回数|
