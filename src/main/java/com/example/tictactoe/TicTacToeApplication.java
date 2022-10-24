package com.example.tictactoe;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.GameGui.Board;
import com.example.tictactoe.GameGui.GameGui;
import com.example.tictactoe.GameLogic.GameLogic;


public class TicTacToeApplication {

    public static void main(String[] args) {

        GameDataBase gameDataBase = new GameDataBase();
        GameGui gameGui = new GameGui(gameDataBase);
        GameLogic gameLogic = new GameLogic(gameDataBase);
        Board board = new Board(gameDataBase);

        gameGui.printGreeting();
        gameGui.collectNecessaryInformationAboutThePlayer(gameDataBase.getFirst()); // zastanowić się nad nazwą

        do {
            gameGui.askForAnOpponent();
            gameGui.askIfPlayerIsSureAboutOpponent();
            gameGui.answerThePlayerDecisions();
        } while (!gameDataBase.isOpponentHasBeenSelected());

        if (gameLogic.isOpponentAComputer()) {
            gameGui.printInfoAboutComputer();
            gameLogic.addComputerAsPlayer();
        } else {
            gameGui.collectNecessaryInformationAboutThePlayer(gameDataBase.getSecond());
        }


        gameGui.askAboutBoardSize();
        gameLogic.prepareBoardForTheGame();
        gameGui.askAboutNumberOfSymbolsInLineToWin();

        gameGui.printExplanationOfTheGame();
        board.printInstructionBoard();

        gameLogic.chooseARandomSymbolThatStarts();
        boolean isEnd;


        do {
            gameGui.printInfoWhoMakeMove();
            do {

                if (!gameLogic.isComputerMove()) {
                    gameGui.askPlayerToSelectSpaceOnTheBoard();
                } else {
                    gameLogic.chooseSpaceOnBoardForComputer();
                }

                gameLogic.findSelectedSpaceInBoardArray();

                if (gameLogic.isValidMove() && !gameLogic.isComputerMove()) {
                    gameGui.printInfoThatThisSpaceIsNotEmpty();
                }

            } while (gameLogic.isValidMove());

            gameLogic.placeMoveOnBoard(gameDataBase.getSymbolUsedThisRound());

            board.printMainBoard();
            isEnd = gameLogic.isThePlayerWinTheGame() || gameLogic.isADraw();

            if (gameLogic.isThePlayerWinTheGame() && isEnd) {
                gameGui.printWhoWinGame();
            }
            if (gameLogic.isADraw() && isEnd && !gameLogic.isThePlayerWinTheGame()){
                gameGui.printInfoAboutDraft();
            }

            gameLogic.changeTurn();
        } while (!isEnd);
    }

}
