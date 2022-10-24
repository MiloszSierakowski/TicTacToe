package com.example.tictactoe.Player;

public class Computer implements Player{

    private String name = "Computer";

    private String symbol;

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
