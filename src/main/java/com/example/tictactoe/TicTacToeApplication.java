package com.example.tictactoe;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.GameGui.Board;
import com.example.tictactoe.GameGui.GameGui;
import com.example.tictactoe.GameLogic.GameLogic;


public class TicTacToeApplication {

    public static void main(String[] args) {

        GameDataBase gameDataBase = new GameDataBase();
        GameGui gameGui = new GameGui(gameDataBase);
        GameLogic gameLogic = new GameLogic(gameDataBase, gameGui);
        Board board = new Board(gameDataBase);

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
        gameGui.printExplanationOfTheGame();
        board.printInstructionBoard();


        boolean isEnd;
        gameDataBase.setRound("X");

        do {

            do {
                gameGui.askPlayerToSelectSpaceOnTheBoard();
                gameLogic.findSelectedSpaceInBoardArray();
                if (gameLogic.isSelectedSpaceNotEmpty()) {
                    gameGui.printInfoThatThisSpaceIsNotEmpty();
                }

            } while (gameLogic.isSelectedSpaceNotEmpty());

            gameLogic.putPlayerFigureOnBoard(gameDataBase.getRound());
            board.printMainBoard();
            isEnd = gameLogic.isThePlayerWinTheGame();

            gameLogic.changeTurn();
        } while (!isEnd);
    }

}
