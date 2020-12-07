package com.example.tictactoe;

import android.content.Context;
import android.util.AttributeSet;

public class Cell extends androidx.appcompat.widget.AppCompatButton {
    private int posX;
    private int posY;
    public Cell( Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public Cell(MainActivity mainActivity) {
        super(mainActivity);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
