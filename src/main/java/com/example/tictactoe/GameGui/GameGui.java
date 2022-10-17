package com.example.tictactoe.GameGui;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.InputValidation.InputValidation;
import com.example.tictactoe.Player;

public class GameGui {

    private final GameDataBase gameDataBase;
    private final InputValidation inputValidation = new InputValidation();

    private final int firstPlayer = 0;

    public GameGui(GameDataBase gameDataBase) {
        this.gameDataBase = gameDataBase;
    }

    public void printGreeting() {
        System.out.println("Witamy w grze w kolko i krzyzyk. Moja gra ma troche opcji wiec zanim " +
                "zaczniemy chcialbym zadac Tobie pare pytan zajmnie to tylko chwile ;) ");
    }

    public void collectNecessaryInformationAboutThePlayer(int whichPlayer) {
        gameDataBase.addPlayerToList(new Player());
        if (whichPlayer == firstPlayer) {
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
        System.out.println("Bardzo milo mi Ciebie poznac " + playerName + ". Tak wiec kolejne pytanie" +
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

    public void askForAnOpponent() {
        System.out.println("Dobrze wiec z kim chcesz zagrac? Wprowadz jedn z opcji 1 - Gracz, 2 - Komputer");
        saveSelectedOpponentInDataBase();
    }

    public void askIfPlayerIsSureAboutOpponent(){
        System.out.println("Czy jestes pewien wybranego przeciwnika? Wybierz Y - Yes lub N - No ");
        inputValidation.takeFromPlayerDecisionAboutOpponent();
    }

    public void answerThePlayerDecisions(){
        if (isPlayerSureAboutOpponent()){
            System.out.println("Rozumiem w takim razie pozostalo nam ustalic ostatnia rzecz przed gra i bedzie mozna zaczac zabawe ");
            gameDataBase.setOpponentHasBeenSelected(isPlayerSureAboutOpponent());
        }else {
            System.out.println("W takim razie jeszcze raz spytam ");
            gameDataBase.setOpponentHasBeenSelected(isPlayerSureAboutOpponent());
        }
    }

    private boolean isPlayerSureAboutOpponent(){
        return inputValidation.getApprovedAnswerYOrN().contains("Y");
    }

    private void saveSelectedOpponentInDataBase(){
        inputValidation.takeFromPlayerSelectedOpponent();
        String selectedOpponent = inputValidation.getApprovedSelectedOpponent();
        if (selectedOpponent.contains("1")){
            gameDataBase.setSelectedOpponent(selectedOpponent);
        }else {
            gameDataBase.setSelectedOpponent(selectedOpponent);
        }
    }

    public void askAboutBoardSize() {
        System.out.println("W takimi razie prosze teraz zdecydowac o rozmiarze planszy masz dwie opcje \n" +
                "1. Plansza 3x3 na ktorej trzeba miec w rzedzie 3 znaki zeby wygrac \n" +
                "2. Plansza 5x5 na ktorej trzeba miec w rzedzie 5 znakow zeby wygrac \n" +
                "Wpisz w konsole 1 lub 2 aby dokonac wyboru.");
        saveSelectedBoardSizeByPlayerInDataBase();
    }

    private void saveSelectedBoardSizeByPlayerInDataBase() {
        inputValidation.takeFromPlayerBoardSize();
        String selectedBoardSize = inputValidation.getApprovedSelectedBoardSize();
        gameDataBase.setSelectedBoard(selectedBoardSize);
    }

}
