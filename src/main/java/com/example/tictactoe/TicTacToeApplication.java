package com.example.tictactoe;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.GameGui.GameGui;
import com.example.tictactoe.GameLogic.GameLogic;



public class TicTacToeApplication {

    public static void main(String[] args) {

        GameDataBase gameDataBase = new GameDataBase();
        GameGui gameGui = new GameGui(gameDataBase);
        GameLogic gameLogic = new GameLogic(gameDataBase,gameGui);

/*
        gameGui.printGreeting();
        gameGui.collectNecessaryInformationAboutThePlayer(gameDataBase.getFirst()); // zastanowić się nad nazwą

        do {
            gameGui.askForAnOpponent();
            gameGui.askIfPlayerIsSureAboutOpponent();
            gameGui.answerThePlayerDecisions();
        } while (!gameDataBase.isOpponentHasBeenSelected());
*/

        gameGui.askAboutBoardSize();
        gameLogic.prepareBoardForTheGame();


    }

}
