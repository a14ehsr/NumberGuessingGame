
//
//  TenThousandYenGame.java
//  ver1.0 (2018/06/01)
//
//  Created by Hirano Keisuke on 2018/06/01.
//  Copyright © 2018年 Hirano Keisuke. All rights reserved.
//
package src.main.java.platform;

import src.main.java.sample_ai.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TenThousandYenGame {

    Process[] processes;
    InputStream[] inputStreams;
    OutputStream[] outputStreams;
    BufferedReader[] bufferedReaders;
    Setting setting;

    public TenThousandYenGame(String[] args) {
        // 各種設定と実行コマンド関連の処理
        setting = new Setting();
        setting.start(args);

        processes = new Process[2];
        inputStreams = new InputStream[2];
        outputStreams = new OutputStream[2];
        bufferedReaders = new BufferedReader[2];
    }

    /**
     * サブプロセスの起動
     * 
     * @param cmd 実行コマンド(２つ)
     * @throws IOException
     */
    private void startSubProcess(String[] cmd) throws IOException{
        Runtime rt = Runtime.getRuntime();

        for (int i = 0; i < 2; i++) {
            processes[i] = rt.exec(cmd[i]);
            outputStreams[i] = processes[i].getOutputStream();
            inputStreams[i] = processes[i].getInputStream();
            bufferedReaders[i] = new BufferedReader(new InputStreamReader(inputStreams[i]));
            new ErrorReader(processes[i].getErrorStream()).start();
        }
    }
    
    private void startSubProcess(int[] cmdIndex) throws IOException{
        startSubProcess(new String[]{setting.getCommand(cmdIndex[0]), setting.getCommand(cmdIndex[1])});
    }

    /**
     * 対戦する
     * 
     * @throws IOException
     * @throws AgainstTheRulesException
     * @throws NumberFormatException
     */
    private Result run() throws IOException, AgainstTheRulesException, NumberFormatException{
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // パラメータ取得
        int n = setting.getGameNum();
        int round = setting.getRoundNum();
        int outputLevel = setting.getOutputLevel();

        String[] names = new String[2];
        for(int p=0; p<2; p++){
            outputStreams[p].write((n + "\n").getBytes());
            outputStreams[p].write((round + "\n").getBytes());
            outputStreams[p].flush();
            names[p] = bufferedReaders[p].readLine();
        }

        if (outputLevel > 0) System.out.println("player1 : " + names[0] + " vs " + names[1] + " : player2");

        int[][][] gameRecord = new int[n][round][2];
        int[] win = new int[2];

        // n回対戦
        for (int i = 0; i < n; i++) {
            int[] sum = new int[2];
            int[] remain = { 10000, 10000 };
            // round回のラウンド
            for (int j = 0; j < round; j++) {
                // ベット金額を取得
                int[] num = {
                    Integer.parseInt(bufferedReaders[0].readLine()),
                    Integer.parseInt(bufferedReaders[1].readLine())
                };

                // 勝ちプレイヤーに全額付与
                if (num[0] < num[1]) {
                    sum[0] += (num[0] + num[1]);
                } else if (num[0] > num[1]) {
                    sum[1] += (num[0] + num[1]);
                }

                // 残金を減らす
                for (int p=0; p<2; p++) remain[p] -= num[p];

                // 持ち金が正かチェック
                checkRemainMinus(remain, names);

                // 各プレイヤーにそれぞれのベット金額を転送
                for (int p=0; p<2; p++) {
                    outputStreams[p].write((num[p] + "\n").getBytes());
                    outputStreams[p].write((num[1 - p] + "\n").getBytes());
                    outputStreams[p].flush();                    
                }

                // レコードへ記録
                for (int p=0; p<2; p++) gameRecord[i][j][p] = num[p];

                if (outputLevel >= 3)
                    System.out.println(
                            "     bet : " + names[0] + " " + String.format("%5d - %5d", num[0], num[1]) + " " + names[1]);
            }
            // 持ち金が0かチェック
            checkEndRemain(remain, names);

            gameResult(sum, win, names, outputLevel);

        }
        if (outputLevel > 0)
            System.out.println(n + "回戦って " + names[0] + " " + win[0] + " - " + win[1] + " " + names[1]);
        return new Result(names, win);
    }

    /**
     * サブプロセスを終了
     */
    private void processDestroy() {
        for (Process p : processes) {
            if (p == null)
                continue;
            try {
                p.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ゲーム終了時に持ち金が0になったかのcheck
     * 
     * @param remain 残額
     * @param names  プレイヤー名
     * @throws AgainstTheRulesException ルール違反例外
     */
    private void checkEndRemain(int[] remain, String[] names) throws AgainstTheRulesException {
        for (int i=0; i<2; i++) {
            if (remain[i] != 0)
                throw new AgainstTheRulesException("ゲーム終了時に" + names[i] + "の持ち金が0になりませんでした");
        }
    }

    /**
     * ゲーム中の持ち金が正かのcheck
     * 
     * @param remain 残額
     * @param names  プレイヤー名
     * @throws AgainstTheRulesException ルール違反例外
     */
    private void checkRemainMinus(int[] remain, String[] names) throws AgainstTheRulesException {
        for (int i=0; i<2; i++) {
            if (remain[i] < 0)
                throw new AgainstTheRulesException(names[i] + "の持ち金が負になりました");
        }
    }

    /**
     * 1ゲームの勝ち負け判定と出力
     *
     * @param sum 取得金額
     * @param win 勝ち数の記録
     * @param names プレイヤー名
     * @param outputLevel 出力レベル
     * @TODO outputlevelをis~~などのbooleanに修正
     */
    private void gameResult(int[] sum, int[] win, String[] names, int outputLevel) {
        String resultMsg;
        if (sum[0] > sum[1]) {
            win[0]++;
            resultMsg = names[0] + "の勝ち";
        } else if (sum[0] < sum[1]) {
            win[1]++;
            resultMsg = names[1] + "の勝ち";
        } else {
            resultMsg = "引き分け";
        }
        if (outputLevel >= 2) {
            System.out.println(
                "  " + names[0] + " " + String.format("%5d - %5d", sum[0], sum[1]) + " " + names[1]
                    + "      | " + resultMsg);
        }
    }

    public static void main(String[] args) {
        TenThousandYenGame obj = new TenThousandYenGame(args);
        if(obj.setting.isTest()){
            obj.test();
        } else {
            obj.autoRun();
        }
        
    }

    /**
     * 対戦の実行
     */
    private void autoRun() {
        List<String> cmdList = setting.getRunCommandList();
        String[] names = new String[cmdList.size()];
        int[][] win = new int[names.length][names.length];
        int[][] winNum = new int[names.length][3];
        for (int i = 0; i < cmdList.size(); i++) {
            for (int j = i + 1; j < cmdList.size(); j++) {
                try {
                    startSubProcess(new int[] { i, j });
                    Result result = run();
                    names[i] = result.names[0];
                    names[j] = result.names[1];
                    win[i][j] = result.win[0];
                    win[j][i] = result.win[1];
                    if (win[i][j] > win[j][i]) {
                        winNum[i][0]++;
                        winNum[j][1]++;
                    } else if (win[i][j] < win[j][i]) {
                        winNum[j][0]++;
                        winNum[i][1]++;
                    } else {
                        winNum[i][2]++;
                        winNum[j][2]++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    processDestroy();
                }
            }
        }
        result(names, win, winNum);
        
    }

    private void result(String[] allNames, int[][] allWin, int[][] winNum) {
        System.out.println("RESULT");
        for (int i = 0; i < allNames.length; i++) {
            System.out.printf("%18s ", allNames[i]);
            for (int j = 0; j < allNames.length; j++) {
                System.out.printf("%3d ", allWin[i][j]);
            }
            System.out.printf("%3d勝 %3d敗 %3d引き分け\n", winNum[i][0], winNum[i][1], winNum[i][2]);
        }

        // リザルト出力用ファイルの準備
        FileWriter file = null;
        try {
            file = new FileWriter("src/main/resource/result/result.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(file));
        pw.println("RESULT");
        for (int i = 0; i < allNames.length; i++) {
            pw.printf(",%s", allNames[i]);
        }
        pw.println(",win,lose,draw");
        for (int i = 0; i < allNames.length; i++) {
            pw.printf("%s,", allNames[i]);
            for (int j = 0; j < allNames.length; j++) {
                pw.printf("%d,", allWin[i][j]);
            }
            pw.printf("%d,%d,%d\n", winNum[i][0], winNum[i][1], winNum[i][2]);
        }
        pw.close();

    }

    /**
     * テスト実行によるふるい
     */
    private void test() {
        List<String> cmdList = setting.getRunCommandList();
        List<String> sampleCmdList = setting.getSampleCommandList();
        Logger testRunLogger = Logger.getLogger(TenThousandYenGame.class.getName());
        loggerInit(testRunLogger, "src/main/resource/log/test_run_err/err.log");

        // 実行コマンド出力ファイルの準備
        FileWriter file = null;
        try {
            file = new FileWriter("src/main/resource/command_list/run_command_list_green.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }    
        PrintWriter pw = new PrintWriter(new BufferedWriter(file));
        
        // サンプルと対戦させ，例外が発生しなかれば，実行可能コマンドとしてファイルに出力
        // @TODO タイムアウト処理
        for (int i=0; i<cmdList.size(); i++) {
            String playerCommand = cmdList.get(i);
            System.out.println(playerCommand);
            try {
                for(int j=0; j<sampleCmdList.size(); j++) {
                    startSubProcess(new String[]{playerCommand, sampleCmdList.get(j)});
                    run();
                    processDestroy();
                }
                pw.println(playerCommand);
            } catch (AgainstTheRulesException e) {
                testRunLogger.log(Level.INFO, "テスト実行時エラー:"+ e);
            } catch (NumberFormatException e) {
                testRunLogger.log(Level.INFO, "テスト実行時エラー：動的型付け言語などで発生が見られます．\nベットする値をintでキャストするなどによって回避できる可能性があります．", e);
            } catch (IOException e) {
                System.err.println(e);
            } finally {
                processDestroy();
            }
        }
        pw.close();
    }

    /**
     * Loggerの初期化等
     * 
     * @param logger 初期化対象オブジェクト
     * @param filePath ログ出力ファイルのパス
     */
    private void loggerInit(Logger logger, String filePath) {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(filePath, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.INFO);
        // Loggerクラスのメソッドを使ってメッセージを出力
        logger.finest("FINEST");
        logger.finer("FINER");
        logger.fine("FINE");
        logger.config("CONFIG");
        logger.info("INFO");
        logger.warning("WARNING");
        logger.severe("SEVERE");
    }

    /**
     * ルール違反発生時用の例外クラス
     */
    class AgainstTheRulesException extends Exception {
        /**
         * コンストラクタ
         * @param mes メッセージ
         */
        AgainstTheRulesException(String mes) {
            super(mes);
        }
    }
    class Result {
        String[] names;
        int[] win;

        Result(String[] names, int[] win) {
            this.names = names;
            this.win = win;;
        }
    }
}

class ErrorReader extends Thread {
    InputStream error;

    public ErrorReader(InputStream is) {
        error = is;
    }

    public void run() {
        try {
            byte[] ch = new byte[50000];
            int read;
            while ((read = error.read(ch)) > 0) {
                String s = new String(ch, 0, read);
                System.out.print(s);
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}