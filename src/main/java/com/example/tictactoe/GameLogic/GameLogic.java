package com.example.tictactoe.GameLogic;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.GameGui.GameGui;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private final GameDataBase gameDataBase;
    private final GameGui gameGui;
    private List<String> board;

    public GameLogic(GameDataBase gameDataBase, GameGui gameGui) {
        this.gameDataBase = gameDataBase;
        this.gameGui = gameGui;
    }

    public List<String> getBoard() {
        return board;
    }

    public void setBoard(List<String> board) {
        this.board = board;
    }

    private boolean isItASmallBoard() {
        return gameDataBase.getSelectedBoard().contains("1");
    }

    private boolean isItABigBoard() {
        return gameDataBase.getSelectedBoard().contains("2");
    }

    public void prepareBoardForTheGame() {
        List<String> boardAsAList = new ArrayList<>();

        if (isItASmallBoard()) {
            int smallSizeBoard = gameDataBase.getSIZE_OF_SMALL_BOARD();
            for (int i = 0; i < smallSizeBoard * smallSizeBoard; i++) {
                boardAsAList.add(String.valueOf(i + 1));
            }
            setBoard(boardAsAList);
        }

        if (isItABigBoard()) {
            int bigSizeBoard = gameDataBase.getSIZE_OF_BIG_BOARD();
            for (int i = 0; i < bigSizeBoard * bigSizeBoard; i++) {
                boardAsAList.add(String.valueOf(i + 1));
            }
            setBoard(boardAsAList);
        }

        saveBardInDataBase();
    }

    private void saveBardInDataBase() {
        gameDataBase.setBoard(getBoard());
    }

}
