package com.example.tictactoe.GameLogic;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.GameGui.GameGui;

public class GameLogic {
    private final GameDataBase gameDataBase;
    private final GameGui gameGui;
    private int foundRow;
    private int foundCol;

    public GameLogic(GameDataBase gameDataBase, GameGui gameGui) {
        this.gameDataBase = gameDataBase;
        this.gameGui = gameGui;
    }

    private int getFoundRow() {
        return foundRow;
    }

    private int getFoundCol() {
        return foundCol;
    }

    public void prepareBoardForTheGame() {
        int boardSize = gameDataBase.getSelectedBoardSize();
        int iterationNumber = 1;
        String[][] instructionBoard = new String[boardSize][boardSize];
        String[][] mainBoard = new String[boardSize][boardSize];

        for (int i = 0; i < mainBoard.length; i++) {
            for (int j = 0; j < mainBoard.length; j++) {
                instructionBoard[i][j] = Integer.toString(iterationNumber);
                mainBoard[i][j] = Integer.toString(iterationNumber);
                iterationNumber++;
            }
        }
        gameDataBase.setInstructionBoard(instructionBoard);
        gameDataBase.setMainBoard(mainBoard);
    }

    public void findSelectedSpaceInBoardArray() {
        String[][] mainBoard = gameDataBase.getMainBoard();
        String selectedSpaceOnBoard = Integer.toString(gameDataBase.getSelectedSpaceOnTheBoard());

        for (int i = 0; i < mainBoard.length; i++) {
            for (int j = 0; j < mainBoard.length; j++) {
                if (mainBoard[i][j].equals(selectedSpaceOnBoard)) {
                    this.foundRow = i;
                    this.foundCol = j;
                }
            }

        }
    }

    public boolean isSelectedSpaceNotEmpty() {
        return gameDataBase.getMainBoard()[getFoundRow()][getFoundCol()].contains("X") ||
                gameDataBase.getMainBoard()[getFoundRow()][getFoundCol()].contains("O");
    }

    public void putPlayerFigureOnBoard(String turn) {
        gameDataBase.getMainBoard()[getFoundRow()][getFoundCol()] = turn;
    }

    public boolean isThePlayerWinTheGame() {
        String[][] mainBoard = gameDataBase.getMainBoard();
        String round = gameDataBase.getRound();
        String diagonallyToTheLeft;
        String diagonallyToTheRight;
        String horizontallyLine;
        String verticallyLine;
        boolean isEndGame = false;
        String winLine = round + round + round;

        for (int i = 0; i < mainBoard.length; i++) {
            for (int j = 0; j < mainBoard.length; j++) {
                if (mainBoard[i][j].contains(round)) {

                    if (isItPossibleToWinDiagonallyInTheLowerLeftDirection(i, j)) {
                        diagonallyToTheLeft = isPlayerWinByOrderFigureDiagonallyInTheLowerLeftDirection(i, j, mainBoard);
                        if (winLine.equals(diagonallyToTheLeft)) {
                            return true;
                        }
                    }
                    if (isItPossibleToWinByOrderFigureVertically(i)) {
                        verticallyLine = isPlayerWinByOrderFigureVertically(i, j, mainBoard);
                        if (winLine.equals(verticallyLine)) {
                            return true;
                        }
                    }
                    if (isItPossibleToWinDiagonallyInTheLowerRightDirection(i, j)) {
                        diagonallyToTheRight = isPlayerWinByOrderFigureDiagonallyInTheLowerRightDirection(i, j, mainBoard);
                        if (winLine.equals(diagonallyToTheRight)) {
                            return true;
                        }
                    }
                    if (isItPossibleToWinByOrderFigureHorizontally(j)) {
                        horizontallyLine = isPlayerWinByOrderFigureHorizontally(i, j, mainBoard);
                        if (winLine.equals(horizontallyLine)) {
                            return true;
                        }

                    }
                }
            }
        }
        return isEndGame;
    }

    private boolean isItPossibleToWinByOrderFigureHorizontally(int selectedCol) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        int figuresInLineToWin = 3;
        return boardSize - selectedCol >= figuresInLineToWin;
    }

    private boolean isItPossibleToWinByOrderFigureVertically(int selectedRow) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        int figuresInLineToWin = 3;
        return boardSize - selectedRow >= figuresInLineToWin;
    }

    private boolean isItPossibleToWinDiagonallyInTheLowerLeftDirection(int selectedRow, int selectedCol) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        int figuresInLineToWin = 3;
        return boardSize - selectedRow >= figuresInLineToWin && selectedCol + 1 >= figuresInLineToWin;
    }

    private boolean isItPossibleToWinDiagonallyInTheLowerRightDirection(int selectedRow, int selectedCol) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        int figuresInLineToWin = 3;
        return boardSize - selectedRow >= figuresInLineToWin && boardSize - selectedCol >= figuresInLineToWin;
    }

    private String isPlayerWinByOrderFigureDiagonallyInTheLowerLeftDirection(int selectedRow, int selectedCol, String[][] mainBoard) {
        int figuresInLineToWin = 3;
        StringBuilder winLine = new StringBuilder();

        for (int i = 0; i < figuresInLineToWin; i++) {
            winLine.append(mainBoard[selectedRow + i][selectedCol - i]);
        }
        return winLine.toString();
    }

    private String isPlayerWinByOrderFigureHorizontally(int selectedRow, int selectedCol, String[][] mainBoard) {
        int figuresInLineToWin = 3;
        StringBuilder winLine = new StringBuilder();

        for (int i = 0; i < figuresInLineToWin; i++) {
            winLine.append(mainBoard[selectedRow][selectedCol + i]);
        }
        return winLine.toString();
    }

    private String isPlayerWinByOrderFigureVertically(int selectedRow, int selectedCol, String[][] mainBoard) {
        int figuresInLineToWin = 3;
        StringBuilder winLine = new StringBuilder();

        for (int i = 0; i < figuresInLineToWin; i++) {
            winLine.append(mainBoard[selectedRow + i][selectedCol]);
        }
        return winLine.toString();
    }

    private String isPlayerWinByOrderFigureDiagonallyInTheLowerRightDirection(int selectedRow, int selectedCol, String[][] mainBoard) {
        int figuresInLineToWin = 3;
        StringBuilder winLine = new StringBuilder();

        for (int i = 0; i < figuresInLineToWin; i++) {
            winLine.append(mainBoard[selectedRow + i][selectedCol + i]);
        }
        return winLine.toString();
    }

/*    public boolean isEndGame() {

        return isThePlayerWinTheGame();
    }*/

    public void changeTurn() {
        if (gameDataBase.getRound().contains("X")) {
            gameDataBase.setRound("O");
        } else {
            gameDataBase.setRound("X");
        }
    }

}
