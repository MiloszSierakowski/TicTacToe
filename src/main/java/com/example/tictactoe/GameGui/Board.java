package com.example.tictactoe.GameGui;

import com.example.tictactoe.GameDataBase.GameDataBase;

public class Board {

    private final GameDataBase gameDataBase;

    public Board(GameDataBase gameDataBase) {
        this.gameDataBase = gameDataBase;
    }

    public void printInstructionBoard() {
        int boardSize = gameDataBase.getSelectedBoardSize();

        for (int i = 0; i < boardSize; i++) {
            System.out.println(printFrame(boardSize));
            System.out.println(printRowForInstructionBoard(i));
        }
        System.out.println(printFrame(boardSize));
    }

    private String printFrame(int sizeOfBoard) {
        return "|---".repeat(Math.max(0, sizeOfBoard)) + "|";
    }

    private String printRowForInstructionBoard(int whichRow) {
        StringBuilder rowToDisplay = new StringBuilder("|");
        String[][] cellsNumbers = gameDataBase.getInstructionBoard();

        for (int i = 0; i < cellsNumbers.length; i++) {

            if (Integer.parseInt(cellsNumbers[whichRow][i]) < 99) {
                rowToDisplay.append(" ");
            }

            rowToDisplay.append(cellsNumbers[whichRow][i]);

            if (Integer.parseInt(cellsNumbers[whichRow][i]) < 10) {
                rowToDisplay.append(" |");
            } else {
                rowToDisplay.append("|");
            }
        }
        return rowToDisplay.toString();
    }

    public void printMainBoard() {
        int boardSize = gameDataBase.getSelectedBoardSize();

        for (int i = 0; i < boardSize; i++) {
            System.out.println(printFrame(boardSize));
            System.out.println(printRowForMainBoard(i));
        }
        System.out.println(printFrame(boardSize));
    }

    private String printRowForMainBoard(int whichRow) {
        StringBuilder rowToDisplay = new StringBuilder("|");
        String[][] cellsNumbers = gameDataBase.getMainBoard();

        for (int i = 0; i < cellsNumbers.length; i++) {

            if (cellsNumbers[whichRow][i].contains("X")) {
                rowToDisplay.append(" ");
                rowToDisplay.append("X");
            }
            if (cellsNumbers[whichRow][i].contains("O")){
                rowToDisplay.append(" ");
                rowToDisplay.append("O");
            }
            if (!cellsNumbers[whichRow][i].contains("X") && !cellsNumbers[whichRow][i].contains("O")){
                rowToDisplay.append("  ");
            }
                rowToDisplay.append(" |");
        }
        return rowToDisplay.toString();
    }


}
