package com.example.tictactoe.InputValidation;

import java.util.Scanner;

public class InputValidation {

    private final Scanner scanner = new Scanner(System.in);

    private String gamePiece;
    private String playerName;

    private String getPlayerName() {
        return playerName;
    }

    private String getGamePiece() {
        return gamePiece;
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

    private boolean isGamePieceNotReady() {
        return !getGamePiece().matches("[xXoO]*") || getGamePiece().length() != 1;
    }

    public void takeFromPlayerHisGamePiece() {
        do {
            this.gamePiece = scanner.nextLine();

            if (isGamePieceNotReady()){
                System.out.println("Wpisales niewlasciwe wartosci prosze podaj X lub O.");
            }
        } while (isGamePieceNotReady());
    }

    public String getApprovedPlayerGamePiece() {
        return this.gamePiece;
    }
}
