package com.example.tictactoe.GameGui;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.InputValidation.InputValidation;
import com.example.tictactoe.Player.Person;

public class GameGui {

    private final GameDataBase gameDataBase;
    private final InputValidation inputValidation = new InputValidation();

    private final int FIRST_PLAYER_ON_LIST = 0;
    private final int SECOND_PLAYER_ON_LIST = 1;

    private final String SYMBOL_X = "X";
    private final String SYMBOL_O = "O";

    public GameGui(GameDataBase gameDataBase) {
        this.gameDataBase = gameDataBase;
    }

    public void printGreeting() {
        System.out.println("Witamy w grze w kolko i krzyzyk. Moja gra ma troche opcji wiec, zanim " +
                "zaczniemy chcialbym zadac Tobie pare pytan zajmie to tylko chwile ;) ");
    }

    public void collectNecessaryInformationAboutThePlayer(int whichPlayerOnList) {
        if (whichPlayerOnList == FIRST_PLAYER_ON_LIST) {
            printGreetingForFirstPlayer();
            askThePlayerForHisName();
            askThePlayerForTheSymbolHeWantsUse();
        } else {
            printGreetingForSecondPlayer();
            askThePlayerForHisName();
            saveSecondPlayerGamePieceInDataBase();
        }
    }

    private void printGreetingForFirstPlayer() {
        System.out.println("Tak wiec zaczynasz jako pierwszy :) a w zwiazku z tym mam do Ciebie pare pytan. ");
    }

    private void printGreetingForSecondPlayer() {
        System.out.println("W takim razie witam drugiego gracza :) ");
    }

    private void askThePlayerForHisName() {
        System.out.println("Napisz jak mam sie do Ciebie zwracac? ");
        savePlayerNameInDataBase();
    }

    private void askThePlayerForTheSymbolHeWantsUse() {
        String playerName = gameDataBase.getPlayerName(FIRST_PLAYER_ON_LIST);
        System.out.println("Bardzo milo mi Ciebie poznac " + playerName + ". Tak wiec kolejne pytanie," +
                " jakim symbolem chcesz zagrac, masz do wyboru X lub O ?");
        saveFirstPlayerSymbolInDataBase();
    }

    private void savePlayerNameInDataBase() {
        inputValidation.takeFromPlayerName();
        String playerName = inputValidation.getApprovedPlayerName();
        Person player = new Person();
        player.setName(playerName);
        gameDataBase.addPlayerToList(player);
    }

    private void saveFirstPlayerSymbolInDataBase() {
        inputValidation.takeFromPlayerHisSymbol();
        String symbol = inputValidation.getApprovedPlayerSymbol();
        gameDataBase.getPlayerFromList(FIRST_PLAYER_ON_LIST).setSymbol(symbol);
        gameDataBase.setSymbolUsedThisRound(symbol.toUpperCase());
    }

    private void saveSecondPlayerGamePieceInDataBase() {
        String firstPlayerGamePiece = gameDataBase.getPlayerSymbol(FIRST_PLAYER_ON_LIST);
        if (firstPlayerGamePiece.equals(SYMBOL_X)) {
            gameDataBase.getPlayerFromList(SECOND_PLAYER_ON_LIST).setSymbol(SYMBOL_O);
            printInformationForSecondPlayerAboutHisSymbol(firstPlayerGamePiece, SYMBOL_O);
        } else {
            gameDataBase.getPlayerFromList(SECOND_PLAYER_ON_LIST).setSymbol(SYMBOL_X);
            printInformationForSecondPlayerAboutHisSymbol(firstPlayerGamePiece, SYMBOL_X);
        }
    }

    private void printInformationForSecondPlayerAboutHisSymbol(String firstPlayerSymbol, String secondPlayerSymbol) {
        System.out.println("Symbolem pierwszego gracza jest " + firstPlayerSymbol + " " +
                "tak wiec zagrasz " + secondPlayerSymbol + ".");
    }

    public void askForAnOpponent() {
        System.out.println("Dobrze wiec z kim chcesz zagrac? Wprowadz jedna z opcji 1 - Gracz, 2 - Komputer");
        saveSelectedOpponentInDataBase();
    }

    public void askIfPlayerIsSureAboutOpponent() {
        System.out.println("Czy jestes pewien wybranego przeciwnika? Wybierz Y - Yes lub N - No ");
        inputValidation.takeFromPlayerDecisionAboutOpponent();
    }

    public void answerThePlayerDecisions() {
        if (isPlayerSureAboutOpponent()) {
            gameDataBase.setIsOpponentHasBeenSelected(isPlayerSureAboutOpponent());
        } else {
            System.out.println("W takim razie jeszcze raz spytam ");
            gameDataBase.setIsOpponentHasBeenSelected(isPlayerSureAboutOpponent());
        }
    }

    private boolean isPlayerSureAboutOpponent() {
        return inputValidation.getApprovedAnswerYOrN().contains("Y");
    }

    public void printInfoAboutComputer() {
        System.out.println("""
                Wiec wybrales komputer jako oponenta nie bedzie latwo.... no ale na razie nie ma\s
                poziomu trudnosci wiec nie bedzie tak zle ^^ \n""");
    }

    private void saveSelectedOpponentInDataBase() {
        inputValidation.takeFromPlayerSelectedOpponent();
        String selectedOpponent = inputValidation.getApprovedSelectedOpponent();
        gameDataBase.setSelectedOpponent(selectedOpponent);
    }

    public void askAboutBoardSize() {
        System.out.println("""
                W takimi razie prosze teraz zdecydowac o rozmiarze planszy. Wiec na jak\s
                duzej planszy chcesz grac? Tylko prosze bez przesady zalozmy, ze nie moze byc\s
                mniejsza niz 3 (bo to nie mialoby sensu) i nie wieksza niz 20.""");

        saveSelectedBoardSizeByPlayerInDataBase();
    }

    private void saveSelectedBoardSizeByPlayerInDataBase() {
        inputValidation.takeFromPlayerBoardSize();
        String selectedBoardSize = inputValidation.getApprovedSelectedBoardSize();
        gameDataBase.setSelectedBoardSize(selectedBoardSize);
    }

    public void askAboutNumberOfSymbolsInLineToWin() {
        System.out.println("Skoro juz znamy rozmiar planszy, zapytam jeszcze o to ile znakow w linii, bedzie decydowalo o wygranej w grze\n" +
                "Zalozmy, ze moze to byc wartosc od 3 (bo to najmniejsza dostepna tablica) do rozmiaru wybranej tablicy.");
        saveSelectedNumberOfSymbolsInLineToWin();
    }

    private void saveSelectedNumberOfSymbolsInLineToWin() {
        int boardSize = gameDataBase.getSelectedBoardSize();
        inputValidation.takeFromPlayerNumberOfSymbolsInLineToWin(boardSize);
        int numberOfSymbolsInLineToWin = inputValidation.getApprovedSelectedNumberOfSymbolsInLineToWin();
        gameDataBase.setNumberOfSymbolsInLineToWin(numberOfSymbolsInLineToWin);
    }

    public void printExplanationOfTheGame() {
        int boardSize = gameDataBase.getSelectedBoardSize();
        int numberOfSymbolsInLineToWin = gameDataBase.getNumberOfSymbolsInLineToWin();
        System.out.println("Przy wyborze planszy " + boardSize + "x" + boardSize + " gramy, dopoki" +
                " jeden z graczy nie bedzie mial w rzedzie " + numberOfSymbolsInLineToWin + " swoich symboli.\nPonizej " +
                "widac plansze, ktora wybrales. Widac na niej numerki komorek, ktore nalezy wskazac, zeby" +
                " pojawila sie tam Twoj znak.\nNie pozostalo mi nic innego jak zyczyc Tobie powodzenia " +
                ":)\n");
    }

    public void printInfoWhoMakeMove() {
        String symbolUsedThisRound = gameDataBase.getSymbolUsedThisRound();
        if (gameDataBase.getPlayerSymbol(FIRST_PLAYER_ON_LIST).equals(symbolUsedThisRound)) {
            String name = gameDataBase.getPlayerName(FIRST_PLAYER_ON_LIST);
            System.out.println("ruch wykonuje " + name);
        } else {
            String name = gameDataBase.getPlayerName(SECOND_PLAYER_ON_LIST);
            System.out.println("ruch wykonuje " + name);
        }
    }

    public void askPlayerToSelectSpaceOnTheBoard() {
        System.out.println("Prosze, wpisz numer komorki gdzie mam ustawic Twoj znak? ");
        saveSelectedSpaceOnTheBoard();
    }

    public void saveSelectedSpaceOnTheBoard() {
        int boardSize = gameDataBase.getSelectedBoardSize();
        inputValidation.takeFromPlayerSelectedSpaceOnTheBoard(boardSize);
        int selectedSpaceOnTheBoard = inputValidation.getApprovedSelectedSpaceOnTheBoard();
        gameDataBase.setSelectedSpaceOnTheBoard(selectedSpaceOnTheBoard);
    }

    public void printInfoThatThisSpaceIsNotEmpty() {
        System.out.println("Wybacz, ale tu znajduje sie juz symbol, sprobuj wybrac inne miejsce.");
    }

    public void printWhoWinGame() {
        String symbolUsedThisRound = gameDataBase.getSymbolUsedThisRound();
        if (gameDataBase.getPlayerFromList(FIRST_PLAYER_ON_LIST).getSymbol().equals(symbolUsedThisRound)) {
            System.out.println("Zwyciezca zostaje " + gameDataBase.getPlayerName(FIRST_PLAYER_ON_LIST));
        } else {
            System.out.println("Zwyciezca zostaje " + gameDataBase.getPlayerName(SECOND_PLAYER_ON_LIST));
        }
    }

    public void printInfoAboutDraft() {
        System.out.println("Niestety nie ma juz miejsca na planszy i jednoczesnie nikt nie wygral tak, wiec oglaszam remis");
    }

}
