package com.example.tictactoe.GameDataBase;

import com.example.tictactoe.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameDataBase {
    List<Player> playerList = new ArrayList<>();
    private String selectedOpponent;
    private String selectedBoardSize;
    private final int first = 0;
    private final int second = 1;
    private boolean isOpponentHasBeenSelected = false;
    String[][] instructionBoard;
    String[][] mainBoard;
    private int numberOfSymbolsInLineToWin = 3 ;
    private int selectedSpaceOnTheBoard;
    private String symbolUsedThisRound;

    public void addPlayerToList(Player player) {
        playerList.add(player);
    }

    public Player getPlayerFromList(int whichPlayerFromList) {
        return playerList.get(whichPlayerFromList);
    }

    public String getPlayerName(int whichPlayerFromList) {
        return playerList.get(whichPlayerFromList).getName();
    }

    public String getPlayerSymbol(int whichPlayerFromList) {
        return playerList.get(whichPlayerFromList).getSymbol();
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

    public void setIsOpponentHasBeenSelected(boolean opponentHasBeenSelected) {
        isOpponentHasBeenSelected = opponentHasBeenSelected;
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

    public int getNumberOfSymbolsInLineToWin() {
        return numberOfSymbolsInLineToWin;
    }

    public void setNumberOfSymbolsInLineToWin(int numberOfSymbolsInLineToWin) {
        this.numberOfSymbolsInLineToWin = numberOfSymbolsInLineToWin;
    }

    public int getSelectedSpaceOnTheBoard() {
        return selectedSpaceOnTheBoard;
    }

    public void setSelectedSpaceOnTheBoard(int selectedSpaceOnTheBoard) {
        this.selectedSpaceOnTheBoard = selectedSpaceOnTheBoard;
    }

    public String getSymbolUsedThisRound() {
        return symbolUsedThisRound;
    }

    public void setSymbolUsedThisRound(String symbolUsedThisRound) {
        this.symbolUsedThisRound = symbolUsedThisRound;
    }
}
