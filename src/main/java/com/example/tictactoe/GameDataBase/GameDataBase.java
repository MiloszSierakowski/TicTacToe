package com.example.tictactoe.GameDataBase;

import com.example.tictactoe.Player;

import java.util.ArrayList;
import java.util.List;

public class GameDataBase {
    List<Player> playerList = new ArrayList<>();
    private String selectedOpponent;
    private String selectedBoardSize;
    private final int first = 0;
    private final int second = 1;
    private boolean isOpponentHasBeenSelected = false;
    private final int SIZE_OF_SMALL_BOARD = 3;
    private final int SIZE_OF_BIG_BOARD = 5;
    String[][] instructionBoard;
    String[][] mainBoard;
    private int selectedSpaceOnTheBoard;
    private String round;

    public void addPlayerToList(Player player) {
        playerList.add(player);
    }

    public Player getPlayerFromList(int whichPlayer) {
        return playerList.get(whichPlayer);
    }

    public String getPlayerName(int whichPlayer) {
        return playerList.get(whichPlayer).getName();
    }

    public String getPlayerGamePiece(int whichPlayer) {
        return playerList.get(whichPlayer).getGamePiece();
    }

    public String getSelectedOpponent() {
        return selectedOpponent;
    }

    public void setSelectedOpponent(String selectedOpponent) {
        this.selectedOpponent = selectedOpponent;
    }

    public int getSelectedBoardSize() {
        return Integer.parseInt(selectedBoardSize);
    }

    public void setSelectedBoardSize(String selectedBoard) {
        this.selectedBoardSize = selectedBoard;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public boolean isOpponentHasBeenSelected() {
        return isOpponentHasBeenSelected;
    }

    public void setOpponentHasBeenSelected(boolean opponentHasBeenSelected) {
        isOpponentHasBeenSelected = opponentHasBeenSelected;
    }

    public int getSIZE_OF_SMALL_BOARD() {
        return SIZE_OF_SMALL_BOARD;
    }

    public int getSIZE_OF_BIG_BOARD() {
        return SIZE_OF_BIG_BOARD;
    }

    public String[][] getInstructionBoard() {
        return instructionBoard;
    }

    public void setInstructionBoard(String[][] instructionBoard) {
        this.instructionBoard = instructionBoard;
    }

    public String[][] getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String[][] mainBoard) {
        this.mainBoard = mainBoard;
    }

    public int getSelectedSpaceOnTheBoard() {
        return selectedSpaceOnTheBoard;
    }

    public void setSelectedSpaceOnTheBoard(int selectedSpaceOnTheBoard) {
        this.selectedSpaceOnTheBoard = selectedSpaceOnTheBoard;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
