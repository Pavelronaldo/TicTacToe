package com.example.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {
    private Cell[][] board;
    private Cell lastButton;
    private List<Cell> emptyCells;

    public Bot(Cell[][] board, Cell lastButton) {
        this.board = board;
        this.lastButton = lastButton;
        emptyCells = new ArrayList<>();
    }

    public Cell botMove() {
        Random random = new Random();
        int posX = lastButton.getPosX();
        int posY = lastButton.getPosY();
        emptyCells = findEmptyCells(posX, posY);
        if (!emptyCells.isEmpty())
            return emptyCells.get(random.nextInt(emptyCells.size()));
        else{
            int i =0;
            int j= 0;

            while (board[i][j].getText() != ""){
                i = random.nextInt(20);
                j = random.nextInt(20);
            }
            return board[i][j];
        }
    }

    private List<Cell> findEmptyCells(int x, int y) {
        ArrayList<Cell> allCells = new ArrayList<>();
        if (x == 0 && y == 0) {
            allCells.add(getRightCell(x, y));
            allCells.add(getDownCell(x, y));
            allCells.add(getRightDownCell(x, y));
        } else if (x == 0 && y == 19) {
            allCells.add(getLeftCell(x, y));
            allCells.add(getDownCell(x, y));
            allCells.add(getLeftDownCell(x, y));
        } else if (x == 19 && y == 0) {
            allCells.add(getRightCell(x, y));
            allCells.add(getUpCell(x, y));
            allCells.add(getRightUpCell(x, y));
        } else if (x == 19 && y == 19) {
            allCells.add(getLeftCell(x, y));
            allCells.add(getUpCell(x, y));
            allCells.add(getLeftUpCell(x, y));
        } else if (x == 0) {
            allCells.add(getRightCell(x, y));
            allCells.add(getDownCell(x, y));
            allCells.add(getRightDownCell(x, y));
            allCells.add(getLeftDownCell(x, y));
            allCells.add(getLeftCell(x, y));
        } else if (x == 19) {
            allCells.add(getRightCell(x, y));
            allCells.add(getUpCell(x, y));
            allCells.add(getRightUpCell(x, y));
            allCells.add(getLeftUpCell(x, y));
            allCells.add(getLeftCell(x, y));
        } else if (y == 0) {
            allCells.add(getRightCell(x, y));
            allCells.add(getDownCell(x, y));
            allCells.add(getRightDownCell(x, y));
            allCells.add(getRightUpCell(x, y));
            allCells.add(getUpCell(x, y));
        } else if (y == 19) {
            allCells.add(getDownCell(x, y));
            allCells.add(getUpCell(x, y));
            allCells.add(getLeftDownCell(x, y));
            allCells.add(getLeftUpCell(x, y));
            allCells.add(getLeftCell(x, y));
        } else {
            allCells.add(getRightCell(x, y));
            allCells.add(getDownCell(x, y));
            allCells.add(getUpCell(x, y));
            allCells.add(getLeftDownCell(x, y));
            allCells.add(getLeftUpCell(x, y));
            allCells.add(getRightDownCell(x, y));
            allCells.add(getRightUpCell(x, y));
            allCells.add(getLeftCell(x, y));
        }
        List<Cell> tempList = new ArrayList<>();

        for (Cell cell : allCells) {
            if (cell.getText() == "") {
                tempList.add(cell);
            }
        }
        return tempList;


    }

    private Cell getRightCell(int x, int y) {
        return board[x][++y];
    }

    private Cell getLeftCell(int x, int y) {
        return board[x][--y];
    }

    private Cell getUpCell(int x, int y) {
        return board[--x][y];
    }

    private Cell getDownCell(int x, int y) {
        return board[++x][y];
    }

    private Cell getRightUpCell(int x, int y) {
        return board[--x][++y];
    }

    private Cell getRightDownCell(int x, int y) {
        return board[++x][++y];
    }

    private Cell getLeftDownCell(int x, int y) {
        return board[++x][--y];
    }

    private Cell getLeftUpCell(int x, int y) {
        return board[--x][--y];
    }
}
