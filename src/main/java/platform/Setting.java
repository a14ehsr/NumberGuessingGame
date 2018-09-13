package src.main.java.platform;

import java.io.PrintWriter;
import java.util.Scanner;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;

/**
 * プラットフォームのコマンドライン引数を受け取って諸々の処理をするクラス
 * パラメータは resource/setting/setting.txtから読み取る.
 * 引数無し実行時には対話モードで動作する．
 */
class Setting {
    private List<String> command;
    private List<String> sampleCommand;
    private int gameNum;
    private int roundNum;
    private int outputLevel;
    private boolean isTest;

    private final String[] sampleName = {
        "Ai_1000yen",
        "Ai_Average",
        "Ai_Minus1",
        "Ai_Random",
        "Ai_Monoinc",
        "Ai_Monodec",
    };
    
    /**
     * デフォルトコンストラクタ
     * コマンドのリストの準備と設定ファイル読み込み
     */
    Setting(){
        isTest = false;
        command = new ArrayList<>();
        sampleCommand = new ArrayList<>();
        String runCommand = "java src.main.java.sample_ai.";
        for (String name : sampleName) {
            sampleCommand.add(runCommand + name);
        }
        try {
            defaultSetting();  
        } catch (Exception e) {
            System.err.println("settingファイルの様式が規定通りになっていません．");
            System.err.println("起動を中止します．");
            e.printStackTrace();
            System.exit(0);
        } 
    }
    /**
     * 実行コマンドを返す
     * 
     * @param index 実行コマンドリストの何番目を返すか
     * @return 実行コマンド
     */
    String getCommand(int index) {
        return command.get(index);
    }

    List<String> getRunCommandList() {
        return command;
    }

    List<String> getSampleCommandList() {
        return sampleCommand;
    }
    
    /**
     * ゲーム数のgetter
     */
    int getGameNum() {
        return gameNum;
    }

    /**
     * ラウンド数のgetter
     */
    int getRoundNum() {
        return roundNum;
    }

    /**
     * 出力レベルのgetter
     */
    int getOutputLevel() {
        return outputLevel;
    }

    boolean isTest() {
        return isTest;
    }

    /**
     * デフォルトの設定を設定ファイルから読み込む
     * 
     * @throws Exception 設定ファイルの様式違いやその他のException
     */
    void defaultSetting() throws Exception{
        String settingFilePath = "src/main/resource/setting/setting.txt";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(settingFilePath));    
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] line;
        line = sc.nextLine().split(" ");
        if(!"game".equals(line[0])){
            throw new Exception("line1 need be game");
        }
        gameNum =  Integer.parseInt(line[1]);

        line = sc.nextLine().split(" ");
        if (!"round".equals(line[0])) {
            throw new Exception("line2 need be round");
        }
        roundNum = Integer.parseInt(line[1]);

        line = sc.nextLine().split(" ");
        if (!"outputlevel".equals(line[0])) {
            throw new Exception("line2 need be outputlevel");
        }
        outputLevel = Integer.parseInt(line[1]);

    }

    /**
     * 設定処理を管理
     * 
     * @param args コマンドライン引数
     */
    void start(final String[] args){
        boolean successed = false;
        if (args.length > 0) {
            try {
                successed = setOption(args);
            } catch (Exception e) {
                System.err.println(e);
                successed = false;
            }
        }
        if (!successed) {
            dialogueMode();
        }
    }
    
    /**
     *  コマンドライン引数を元に各設定を行う
     * 
     * @param options コマンドライン引数
     * @return 成功か否か
     * @throws Exception 範囲外アクセス，型変換例外
     */
    boolean setOption(final String[] options) throws Exception{
        int tmp;
        for (int i = 0; i < options.length; i+=2) {
            switch (options[i]) {
            case "-p":
                command.add(options[i + 1]);
                break;

            case "-game":
                gameNum = Integer.parseInt(options[i + 1]);
                break;

            case "-olevel":
                tmp = Integer.parseInt(options[i + 1]);
                if (tmp > 3 || tmp < 1) {
                    System.out.println("出力モードは1,2,3のいずれかです．その他の値が入力されています．");
                    System.out.println("既定値で実行します．");
                }else{
                    outputLevel = tmp;
                }
                break;

            case "-ai":
                setAi(Integer.parseInt(options[i + 1]));
                break;

            case "-auto":
                readRunCommandList("src/main/resource/command_list/run_command_list_green.txt");
                if("true".equals(options[i + 1])){
                    command.addAll(sampleCommand);
                }
                
                break;
            case "-test":
                gameNum = Integer.parseInt(options[i + 1]);
                isTest = true;
                readRunCommandList("src/main/resource/command_list/run_command_list.txt");
                outputLevel = 0;
                break;
            default:
                System.out.println("オプションがおかしいです．");
                System.out.println("対話モードで起動します．");
                return false;
            }
        }
        return true;
    }

    /**
     *  対話モードでの設定処理
     */
    void dialogueMode() {
        Scanner sc = new Scanner(System.in);
        String ai;
        System.out.println("対話モード起動");

        // aiの設定
        System.out.println("プレイヤー1を設定します．");
        playerSetting(sc);
        System.out.println("プレイヤー2を設定します．");
        playerSetting(sc);

        // gameNum 変更処理
        System.out.print("ゲーム数を変更しますか？ (y/n) :");
        if ("y".equals(sc.next())) {
            System.out.print("ゲーム数を入力してください: ");
            gameNum = sc.nextInt();
        }

        System.out.println("対戦を開始します．");
    }

    private void playerSetting(Scanner sc) {
        System.out.println("使用するAIのプログラムをai_programに配置し，実行コマンドを入力してください");
        System.out.println("サンプルAIを使用する場合にはsampleと入力してください");
        String ai = sc.next();
        if ("sample".equals(ai)) {
            System.out.println("which AI Program? please input number.");
            System.out.println("Ai_1000yen  : 0");
            System.out.println("Ai_Average  : 1");
            System.out.println("Ai_Minus1   : 2");
            System.out.println("Ai_Random   : 3");
            System.out.println("Ai_Monoinc  : 4");
            System.out.println("Ai_Monodec  : 5");
            setAi(sc.nextInt());
        } else {
            command.add(ai);
        }
    }

    void setAi(int aiPattern) {
        if(aiPattern < 0 || aiPattern >= sampleName.length) {
            System.err.println("Ai番号が間違っています．");
            return;
        }
        command.add(sampleCommand.get(aiPattern));
    }

    void readRunCommandList(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(sc.hasNext()) {
            command.add(sc.nextLine());
        }
    }

}