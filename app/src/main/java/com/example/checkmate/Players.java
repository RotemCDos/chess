package com.example.checkmate;

public class Players {
    private String nameW;
    private int scoreW;
    private char colorW;

    private String nameB;
    private int scoreB;
    private char colorB;

    public Players(String nameW, int scoreW, char colorW, String nameB, int scoreB, char colorB) {
        this.nameW = nameW;
        this.scoreW = scoreW;
        this.colorW = colorW;

        this.nameB = nameB;
        this.scoreB = scoreB;
        this.colorB = colorB;
    }

    public String getNameW() {
        return nameW;
    }

    public int getScoreW() {
        return scoreW;
    }

    public void setNameW(String nameW) {
        this.nameW = nameW;
    }

    public void setScoreW(int scoreW) {
        this.scoreW = scoreW;
    }

    public char getColorW() {
        return this.colorW;
    }

    public void setColorW(char colorW) {
        this.colorW = colorW;
    }




    public String getNameB() {
        return nameB;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public char getColorB() {
        return this.colorB;
    }

    public void setColorB(char colorB) {
        this.colorB = colorB;
    }
}
