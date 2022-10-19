package com.example.tictactoe.InputValidation;

import java.util.Scanner;

public class InputValidation {

    private final Scanner scanner = new Scanner(System.in);
    private String gamePiece;
    private String playerName;
    private String selectedBoardSize;
    private final int MINIMUM_BOARD_SIZE = 3;
    private final int MAXIMUM_BARD_SIZE = 20;
    private String selectedOpponent;
    private String playerAnswerYOrN;
    private String selectedSpaceOnTheBoard;


    private String getGamePiece() {
        return gamePiece;
    }

    private String getPlayerName() {
        return playerName;
    }

    private String getSelectedBoardSize() {
        return selectedBoardSize;
    }

    private String getSelectedOpponent() {
        return selectedOpponent;
    }

    private String getPlayerAnswerYOrN() {
        return playerAnswerYOrN;
    }

    private String getSelectedSpaceOnTheBoard() {
        return selectedSpaceOnTheBoard;
    }

    public void takeFromPlayerName() {
        do {
            this.playerName = scanner.nextLine();

            if (getPlayerName().isEmpty()) {
                System.out.println("Wybacz ale jesli nie wpiszesz swojej nazwy " +
                        "nie bede wiedzial jak sie do Ciebie zwracac. Prosze sprobowac jeszcze raz: ");
            }
        } while (getPlayerName().isEmpty());
    }

    public String getApprovedPlayerName() {
        return this.playerName;
    }

    public void takeFromPlayerHisGamePiece() {
        do {
            this.gamePiece = scanner.nextLine();

            if (isGamePieceNotReady()) {
                System.out.println("Wpisales niewlasciwe wartosci prosze podaj X lub O.");
            }
        } while (isGamePieceNotReady());
    }

    private boolean isGamePieceNotReady() {
        return !getGamePiece().matches("[xXoO]*") || getGamePiece().length() != 1;
    }

    public String getApprovedPlayerGamePiece() {
        return this.gamePiece.toUpperCase();
    }

    public void takeFromPlayerSelectedOpponent() {
        do {
            this.selectedOpponent = scanner.nextLine();

            if (isSelectedOpponentNotReady()) {
                System.out.println("Wpisales niewlasciwe wartosci prosze podaj 1 lub 2.");
            }
        } while (isSelectedOpponentNotReady());
    }

    private boolean isSelectedOpponentNotReady() {
        return !getSelectedOpponent().matches("[1-2]") || getSelectedOpponent().length() != 1;
    }

    public String getApprovedSelectedOpponent() {
        return getSelectedOpponent();
    }

    public void takeFromPlayerDecisionAboutOpponent() {
        do {
            this.playerAnswerYOrN = scanner.nextLine();

            if (isPlayerAnswerYOrNNotReady()) {
                System.out.println("Wpisales niewlasciwe wartosci prosze podaj Y lub N.");
            }
        } while (isPlayerAnswerYOrNNotReady());
    }

    private boolean isPlayerAnswerYOrNNotReady() {
        return !getPlayerAnswerYOrN().matches("[yYnN]*") || getPlayerAnswerYOrN().length() != 1;
    }

    public String getApprovedAnswerYOrN() {
        return getPlayerAnswerYOrN().toUpperCase();
    }

    public void takeFromPlayerBoardSize() {
        do {
            this.selectedBoardSize = scanner.nextLine();

            if (isSelectedBoardSizeNotReady()) {
                System.out.println("Wpisales niewlasciwa wartosc prosze podaj wartosc z zakresu " + MINIMUM_BOARD_SIZE + "-" + MAXIMUM_BARD_SIZE);
            }
        } while (isSelectedBoardSizeNotReady());
    }

    private boolean isSelectedBoardSizeNotReady() {
        if (getSelectedBoardSize().matches("[0-9]*") && getSelectedBoardSize().length() == 1) {

            int boardSize = Integer.parseInt(getSelectedBoardSize());
            return boardSize < MINIMUM_BOARD_SIZE || boardSize > MAXIMUM_BARD_SIZE;
        }
        return true;
    }

    public String getApprovedSelectedBoardSize() {
        return getSelectedBoardSize();
    }

    public void takeFromPlayerSelectedSpaceOnTheBoard(int boardSize) {
        do {
            this.selectedSpaceOnTheBoard = scanner.nextLine();

            if (isSelectedSpaceOnTheBoardIsNotReady(boardSize)) {
                System.out.println("Wpisana wartosc jest nie poprawna prosze sprobuj jeszcze raz");
            }

        } while (isSelectedSpaceOnTheBoardIsNotReady(boardSize));
    }

    private boolean isSelectedSpaceOnTheBoardIsNotReady(int boardSize) {

        if (getSelectedSpaceOnTheBoard().matches("[0-9]*") && getSelectedSpaceOnTheBoard().length() >= 1) {
            int spaceInBoard = Integer.parseInt(getSelectedSpaceOnTheBoard());
            return spaceInBoard < 1 || spaceInBoard > (boardSize * boardSize);
        }
        return true;
    }

    public int getApprovedSelectedSpaceOnTheBoard() {
        return Integer.parseInt(getSelectedSpaceOnTheBoard());
    }

}
