package com.example.tictactoe;

import com.example.tictactoe.GameDataBase.GameDataBase;
import com.example.tictactoe.GameGui.GameGui;

public class TicTacToeApplication {

    public static void main(String[] args) {

        GameDataBase gameDataBase = new GameDataBase();
        GameGui gameGui = new GameGui(gameDataBase);

        gameGui.printGreeting();
        gameGui.collectNecessaryInformationAboutThePlayer(0);

    }

}
