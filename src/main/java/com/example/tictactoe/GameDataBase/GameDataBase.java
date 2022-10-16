package com.example.tictactoe.GameDataBase;

import com.example.tictactoe.Player;

import java.util.ArrayList;
import java.util.List;

public class GameDataBase {

    List<Player> playerList = new ArrayList<>();

    private int selectedBoard;

    public void addPlayerToList(Player player){
        playerList.add(player);
    }

    public Player getPlayerFromList(int whichPlayer){
        return playerList.get(whichPlayer);
    }

    public String getPlayerName(int whichPlayer){
        return playerList.get(whichPlayer).getName();
    }

    public String getPlayerGamePiece(int whichPlayer){
        return playerList.get(whichPlayer).getGamePiece();
    }

    public int getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(int selectedBoard) {
        this.selectedBoard = selectedBoard;
    }
}
