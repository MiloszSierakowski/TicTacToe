package com.example.tictactoe.GameGui;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.InputValidation.InputValidation;
import com.example.tictactoe.Player;

public class GameGui {

    private final GameDataBase gameDataBase;
    private final InputValidation inputValidation = new InputValidation();

    public GameGui(GameDataBase gameDataBase) {
        this.gameDataBase = gameDataBase;
    }

    public void printGreeting() {
        System.out.println("Witamy w grze w kolko i krzyzyk. Moja gra ma troche opcji wiec zanim " +
                "zaczniemy chcialbym zadac Tobie pare pytan zajmnie to tylko chwile ;) ");
    }

    public void collectNecessaryInformationAboutThePlayer(int whichPlayer) {
        gameDataBase.addPlayerToList(new Player());
        if (whichPlayer == 0) {
            printGreetingForFirstPlayer();
            askThePlayerForHisName(whichPlayer);
            askThePlayerForTheGamePieceHeWantsUse();
        } else {
            printGreetingForSecondPlayer();
            askThePlayerForHisName(whichPlayer);
            saveSecondPlayerGamePieceInDataBase();
        }
    }

    private void printGreetingForFirstPlayer() {
        System.out.println("Tak wiec zaczynasz jako pierwszy :) a w zwiazku z tym mam do Ciebie pare pytan. ");
    }

    private void printGreetingForSecondPlayer() {
        System.out.println("Witam drugiego gracza :) ");
    }

    private void askThePlayerForHisName(int whichPlayer) {
        System.out.println("Napisz jak mam sie do Ciebie zwracac? ");
        savePlayerNameInDataBase(whichPlayer);
    }

    private void askThePlayerForTheGamePieceHeWantsUse() {
        String playerName = gameDataBase.getPlayerName(0);
        System.out.println("Bardzo milo mi Ciebie poznac " + playerName + ". Tak wiec kolejne pytanie " +
                " jak i przywilej pierwszego gracza ;) Jakim znakiem chcesz zagrac masz do wyboru X lub O ?");
        savePlayerGamePieceInDataBase(0);
    }

    private void savePlayerNameInDataBase(int whichPlayer) {
        inputValidation.takeFromPlayerName();
        String playerName = inputValidation.getApprovedPlayerName();
        gameDataBase.getPlayerFromList(whichPlayer).setName(playerName);
    }

    private void savePlayerGamePieceInDataBase(int whichPlayer) {
        inputValidation.takeFromPlayerHisGamePiece();
        String gamePiece = inputValidation.getApprovedPlayerGamePiece();
        gameDataBase.getPlayerFromList(whichPlayer).setGamePiece(gamePiece);
    }

    private void saveSecondPlayerGamePieceInDataBase() {
        String firstPlayerGamePiece = gameDataBase.getPlayerGamePiece(1);
        if (firstPlayerGamePiece.equals("X")) {
            gameDataBase.getPlayerFromList(1).setGamePiece("O");
            printInformationForSecondPlayerAboutHisGamePiece(firstPlayerGamePiece, "O");
        } else {
            gameDataBase.getPlayerFromList(1).setGamePiece("X");
            printInformationForSecondPlayerAboutHisGamePiece(firstPlayerGamePiece, "X");
        }
    }

    private void printInformationForSecondPlayerAboutHisGamePiece(String firstPlayerGamePiece, String secondPlayerGamePiece) {
        System.out.println("Znakiem pierwszego gracza jest " + firstPlayerGamePiece + " " +
                "tak wiec zagrasz " + secondPlayerGamePiece);
    }

    // dodac enumerator albo zmienna ktora opisuje numer gracza


/*    public void askAboutBoardSize() {
        System.out.println("W takimi razie prosze teraz zdecydowac o rozmiarze planszy masz dwie opcje \n" +
                "1. Plansza 3x3 na ktorej trzeba miec w rzedzie 3 znaki zeby wygrac \n" +
                "2. Plansza 5x5 na ktorej trzeba miec w rzedzie 5 znakow zeby wygrac \n" +
                "Wpisz w konsole 1 lub 2 aby dokonac wyboru.");
        gameDataBase.setSelectedBoard(inputValidation.get);
    }*/

}
