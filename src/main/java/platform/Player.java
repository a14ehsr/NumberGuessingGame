package src.main.java.platform;

import java.util.Scanner;

public abstract class Player {
    private int gameNum; // number of game
    private int roundNum; // numebr of round
    private String playerName = "defaultName";
    private Scanner sc;

    public void run() {
        sc = new Scanner(System.in);
        parameterInit();
        int[][][] gameRecord = new int[gameNum][roundNum][2];
        int offer; // 出す金額用の変数
        for (int i = 0; i < gameNum; i++) {
            int[] remain = { 10000, 10000 };
            for (int j = 0; j < roundNum; j++) {
                offer = bet(i, j, remain, gameRecord);
                System.out.println(offer);
                gameRecord[i][j][0] = sc.nextInt();
                gameRecord[i][j][1] = sc.nextInt();
                remain[0] -= gameRecord[i][j][0];
                remain[1] -= gameRecord[i][j][1];
            }
        }
    }

    protected abstract int bet(int game, int round, int[] remain, int[][][] gameRecord);

    public void parameterInit(int gameNum, int roundNum) {
        this.gameNum = gameNum;
        this.roundNum = roundNum;
    }

    private void parameterInit() {
        gameNum = sc.nextInt();
        roundNum = sc.nextInt();
        System.out.println(playerName);
    }

    protected void setName(String playerName) {
        this.playerName = playerName;
    }

    protected int getGameNum() {
        return gameNum;
    }

    protected int getRoundNum() {
        return roundNum;
    }

    public String getName() {
        return playerName;
    }

}