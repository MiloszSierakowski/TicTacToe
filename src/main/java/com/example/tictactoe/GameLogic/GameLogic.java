package com.example.tictactoe.GameLogic;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.Player.Computer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;


public class GameLogic {
    private final GameDataBase gameDataBase;
    private int foundRow;
    private int foundCol;
    private final int FIRST_PLAYER_ON_LIST = 0;
    private final int SECOND_PLAYER_ON_LIST = 1;

    private final String SYMBOL_X = "X";
    private final String SYMBOL_O = "O";

    public GameLogic(GameDataBase gameDataBase) {
        this.gameDataBase = gameDataBase;
    }

    private int getFoundRow() {
        return foundRow;
    }

    private int getFoundCol() {
        return foundCol;
    }

    public boolean isOpponentAComputer() {
        int selectedOpponent = Integer.parseInt(gameDataBase.getSelectedOpponent());
        return selectedOpponent == 2;
    }

    public void addComputerAsPlayer() {
        String symbol = gameDataBase.getPlayerSymbol(FIRST_PLAYER_ON_LIST);
        gameDataBase.addPlayerToList(new Computer());
        if (symbol.equals(SYMBOL_X)) {
            gameDataBase.getPlayerFromList(SECOND_PLAYER_ON_LIST).setSymbol(SYMBOL_O);
        } else {
            gameDataBase.getPlayerFromList(SECOND_PLAYER_ON_LIST).setSymbol(SYMBOL_X);
        }
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

    public void chooseARandomSymbolThatStarts() {
        Random random = new Random();
        if ((random.nextInt(2) + 1) == 1) {
            gameDataBase.setSymbolUsedThisRound(SYMBOL_X);
        } else {
            gameDataBase.setSymbolUsedThisRound(SYMBOL_O);
        }
    }

    public boolean isComputerMove() {
        if (gameDataBase.getPlayerFromList(SECOND_PLAYER_ON_LIST).getName().equals("Computer")) {
            String symbolUsedThisRound = gameDataBase.getSymbolUsedThisRound();
            String computerSymbol = gameDataBase.getPlayerSymbol(SECOND_PLAYER_ON_LIST);
            return symbolUsedThisRound.equals(computerSymbol);
        }
        return false;
    }

    //---------------------------------------------------------------------------------- computer logic--------------------------------------------------
    public void chooseSpaceOnBoardForComputer() {
        Random random = new Random();

        String[][] temporaryBoard = gameDataBase.getMainBoard();

        List<String> availableSpacesOnBoard;

        availableSpacesOnBoard = Arrays.stream(temporaryBoard)
                .flatMap(Arrays::stream)
                .filter(x-> !x.equals(SYMBOL_X) && !x.equals(SYMBOL_O))
                .collect(Collectors.toList());

        int chosenElementFromAvailableSpacesOnBoard = random.nextInt(availableSpacesOnBoard.size());
        int chosenSpaceOnBoard = Integer.parseInt(availableSpacesOnBoard.get(chosenElementFromAvailableSpacesOnBoard));

        gameDataBase.setSelectedSpaceOnTheBoard(chosenSpaceOnBoard);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------
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

    public boolean isValidMove() {
        return gameDataBase.getMainBoard()[getFoundRow()][getFoundCol()].contains(SYMBOL_X) ||
                gameDataBase.getMainBoard()[getFoundRow()][getFoundCol()].contains(SYMBOL_O);
    }

    public void placeMoveOnBoard(String turn) {
        gameDataBase.getMainBoard()[getFoundRow()][getFoundCol()] = turn;
    }

    public boolean isThePlayerWinTheGame() {
        String[][] mainBoard = gameDataBase.getMainBoard();
        String symbolUsedInThisRound = gameDataBase.getSymbolUsedThisRound();
        int numberOfSymbolsInLineToWin = gameDataBase.getNumberOfSymbolsInLineToWin();

        String winLine = createAWinningString(symbolUsedInThisRound, numberOfSymbolsInLineToWin);

        for (int row = 0; row < mainBoard.length; row++) {
            for (int col = 0; col < mainBoard.length; col++) {

                if (mainBoard[row][col].contains(symbolUsedInThisRound)) {

                    if (isThePlayerWinningDiagonallyInTheLowerLeftDirection(row, col, winLine, numberOfSymbolsInLineToWin, mainBoard)) {
                        return true;
                    }

                    if (isThePlayerWinningVertically(row, col, winLine, numberOfSymbolsInLineToWin, mainBoard)) {
                        return true;
                    }

                    if (isThePlayerWinningDiagonallyInTheLowerRightDirection(row, col, winLine, numberOfSymbolsInLineToWin, mainBoard)) {
                        return true;
                    }

                    if (isThePlayerWinningHorizontally(row, col, winLine, numberOfSymbolsInLineToWin, mainBoard)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isADraw() {
        String[][] temporaryBoard = gameDataBase.getMainBoard();

        List<String> availableSpacesOnBoard;

        availableSpacesOnBoard = Arrays.stream(temporaryBoard)
                .flatMap(Arrays::stream)
                .filter(x-> !x.equals(SYMBOL_X) && !x.equals(SYMBOL_O))
                .collect(Collectors.toList());

        for (String s: availableSpacesOnBoard) {
            if (!s.equals(SYMBOL_X) && !s.equals(SYMBOL_O)){
                return false;
            }
        }
        return true;
    }

    private String createAWinningString(String SymbolUsedInThisRound, int symbolsInLineToWin) {
        return String.valueOf(SymbolUsedInThisRound).repeat(Math.max(0, symbolsInLineToWin));
    }

    private boolean isThePlayerWinningDiagonallyInTheLowerLeftDirection(int row, int col, String winLine, int numberOfSymbolsInLineToWin, String[][] mainBoard) {
        if (isItPossibleToWinDiagonallyInTheLowerLeftDirection(row, col, numberOfSymbolsInLineToWin)) {

            StringBuilder diagonallyInTheLowerLeft = new StringBuilder();

            for (int i = 0; i < numberOfSymbolsInLineToWin; i++) {
                diagonallyInTheLowerLeft.append(mainBoard[row + i][col - i]);
            }

            return winLine.equals(diagonallyInTheLowerLeft.toString());
        }
        return false;
    }

    private boolean isItPossibleToWinDiagonallyInTheLowerLeftDirection(int selectedRow, int selectedCol, int numberOfSymbolsInLineToWin) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        return boardSize - selectedRow >= numberOfSymbolsInLineToWin && selectedCol + 1 >= numberOfSymbolsInLineToWin;
    }

    private boolean isThePlayerWinningVertically(int row, int col, String winLine, int numberOfSymbolsInLineToWin, String[][] mainBoard) {
        if (isItPossibleToWinByOrderSymbolVertically(row, numberOfSymbolsInLineToWin)) {

            StringBuilder verticallyLine = new StringBuilder();

            for (int i = 0; i < numberOfSymbolsInLineToWin; i++) {
                verticallyLine.append(mainBoard[row + i][col]);
            }
            return winLine.equals(verticallyLine.toString());
        }
        return false;
    }

    private boolean isItPossibleToWinByOrderSymbolVertically(int selectedRow, int numberOfSymbolsInLineToWin) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        return boardSize - selectedRow >= numberOfSymbolsInLineToWin;
    }

    private boolean isThePlayerWinningDiagonallyInTheLowerRightDirection(int row, int col, String winLine, int numberOfSymbolsInLineToWin, String[][] mainBoard) {

        if (isItPossibleToWinDiagonallyInTheLowerRightDirection(row, col, numberOfSymbolsInLineToWin)) {

            StringBuilder diagonallyInTheLowerRight = new StringBuilder();

            for (int i = 0; i < numberOfSymbolsInLineToWin; i++) {
                diagonallyInTheLowerRight.append(mainBoard[row + i][col + i]);
            }

            return winLine.equals(diagonallyInTheLowerRight.toString());
        }
        return false;
    }

    private boolean isItPossibleToWinDiagonallyInTheLowerRightDirection(int selectedRow, int selectedCol, int numberOfSymbolsInLineToWin) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        return boardSize - selectedRow >= numberOfSymbolsInLineToWin && boardSize - selectedCol >= numberOfSymbolsInLineToWin;
    }

    private boolean isThePlayerWinningHorizontally(int row, int col, String winLine, int numberOfSymbolsInLineToWin, String[][] mainBoard) {
        if (isItPossibleToWinByOrderSymbolHorizontally(col, numberOfSymbolsInLineToWin)) {

            StringBuilder horizontallyLine = new StringBuilder();

            for (int i = 0; i < numberOfSymbolsInLineToWin; i++) {
                horizontallyLine.append(mainBoard[row][col + i]);
            }
            return winLine.equals(horizontallyLine.toString());
        }
        return false;
    }

    private boolean isItPossibleToWinByOrderSymbolHorizontally(int selectedCol, int numberOfSymbolsInLineToWin) {
        int boardSize = gameDataBase.getSelectedBoardSize();
        return boardSize - selectedCol >= numberOfSymbolsInLineToWin;
    }

/*    public boolean isEndGame() {

        return isThePlayerWinTheGame();
    }*/

    public void changeTurn() {
        if (gameDataBase.getSymbolUsedThisRound().contains("X")) {
            gameDataBase.setSymbolUsedThisRound("O");
        } else {
            gameDataBase.setSymbolUsedThisRound("X");
        }
    }
}
