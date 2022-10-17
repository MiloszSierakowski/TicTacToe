package com.example.tictactoe.GameDataBase;

import com.example.tictactoe.Player;

import java.util.ArrayList;
import java.util.List;

public class GameDataBase {
    List<Player> playerList = new ArrayList<>();
    private String selectedOpponent;
    private String selectedBoard;
    private final int first = 0;
    private final int second = 1;
    private boolean isOpponentHasBeenSelected = false;
    private final int SIZE_OF_SMALL_BOARD = 3;
    private final int SIZE_OF_BIG_BOARD = 5;

    List<String> board = new ArrayList<>();

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

    public String getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(String selectedBoard) {
        this.selectedBoard = selectedBoard;
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

    public List<String> getBoard() {
        return board;
    }

    public void setBoard(List<String> board) {
        this.board = board;
    }
}
