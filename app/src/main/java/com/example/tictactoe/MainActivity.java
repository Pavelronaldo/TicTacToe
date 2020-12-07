package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Dialog gameOver;
    private boolean userMove = true;
    private int counter = 0;
    private Cell[][] board = new Cell[20][20];
    Cell cell = null;
    Cell lastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameOver = new Dialog(this);
        TableLayout table = findViewById(R.id.tablelayout);
        for (int i = 0; i < 20; i++) {
            TableRow tableRow = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 20; j++) {
                Cell cell = (Cell) tableRow.getChildAt(j);
                cell.setPosX(i);
                cell.setPosY(j);
                cell.setTag(i);
                setButtonListener(cell);
                board[i][j] = cell;
            }

        }
    }

    private void setButtonListener(final Cell button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userMove) {
                    if (button.getText() == "") {
                        button.setText("X");
                        userMove = true;
                        analyzeResults(button.getPosX(), button.getPosY());
                        Bot bot = new Bot(board, button);
                        Cell botCell = bot.botMove();
                        botCell.setText("O");
                        analyzeResults(botCell.getPosX(),botCell.getPosY());
                    }
                }
            }
        });
    }

    private void newGame(){
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    private void checkGameOver() {
        if (counter >= 4) {
            Toast.makeText(getApplicationContext(), "GAME OvER", Toast.LENGTH_SHORT).show();
            gameOver.show();
            gameOver.setContentView(R.layout.game_over);
            Button btNewGame = gameOver.findViewById(R.id.dialoggameover_btnewgame);
            TextView finalScore = gameOver.findViewById(R.id.finishScore);
            finalScore.setText("You WON");
            gameOver.show();
            btNewGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newGame();
                }
            });

        }
        counter = 0;
    }

    private void analyzeResults(int x, int y) {

        Cell tempButton = board[x][y];
        checkNeighbourCells(tempButton);


    }

    private void checkLeftCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {
            for (int i = 0; i < 4; i++) {
                cell = board[row][--col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("counter left update ", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            checkRightCells(tempButton);
        }
        checkGameOver();
    }

    private void checkRightCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {
            for (int j = 0; j < 4; j++) {
                cell = board[row][++col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("counter right update ", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            checkLeftCells(tempButton);

        }
        checkGameOver();
    }

    private void checkUpCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {
            for (int i = 0; i < 4; i++) {
                cell = board[--row][col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("counter UP update", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            checkDownCells(tempButton);

        }
        checkGameOver();
    }

    private void checkDownCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {

            for (int i = 0; i < 4; i++) {
                cell = board[++row][col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("counter DOWN update", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            checkUpCells(tempButton);

        }
        checkGameOver();
    }

    private void checkRightUpCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {
            for (int i = 0; i < 4; i++) {
                cell = board[--row][++col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("count RIGHT UP update", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (row > 4 && col > 5)
                checkLeftDownCells(tempButton);
        }
        checkGameOver();
    }

    private void checkLeftUpCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {


            for (int i = 0; i < 4; i++) {
                cell = board[--row][--col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("count LEFT UP update", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (col < 14 && row > 4)
                checkRightDownCells(tempButton);
        }
        checkGameOver();
    }

    private void checkRightDownCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {
            for (int i = 0; i < 4; i++) {
                cell = board[++row][++col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("count RIGHT DOWN update", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (row < 15 && col > 5)
                checkLeftUpCells(tempButton);
        }
        checkGameOver();
    }

    private void checkLeftDownCells(Cell tempButton) {
        int row = tempButton.getPosX();
        int col = tempButton.getPosY();
        try {
            for (int i = 0; i < 4; i++) {
                cell = board[++row][--col];
                if (cell.getText() == tempButton.getText()) {
                    counter++;
                    Log.i("count LEFT DOWN update", String.valueOf(counter));
                } else {
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (row < 15 && col < 14)
                checkRightUpCells(tempButton);
        }
        checkGameOver();
    }

    private void checkNeighbourCells(Cell tempButton) {

        if (tempButton.getPosX() == 0 && tempButton.getPosY() == 0) {
            checkRightCells(tempButton);
            checkRightDownCells(tempButton);
            checkDownCells(tempButton);
        } else if (tempButton.getPosX() == 0 && tempButton.getPosY() == 19) {
            checkLeftCells(tempButton);
            checkLeftDownCells(tempButton);
            checkDownCells(tempButton);
        } else if (tempButton.getPosX() == 19 && tempButton.getPosY() == 0) {
            checkRightCells(tempButton);
            checkRightUpCells(tempButton);
            checkUpCells(tempButton);
        } else if (tempButton.getPosX() == 19 && tempButton.getPosY() == 19) {
            checkLeftCells(tempButton);
            checkLeftUpCells(tempButton);
            checkUpCells(tempButton);
        } else {
            checkLeftCells(tempButton);
            checkRightCells(tempButton);
            checkUpCells(tempButton);
            checkDownCells(tempButton);
            checkLeftDownCells(tempButton);
            checkRightUpCells(tempButton);
            checkLeftUpCells(tempButton);
            checkRightDownCells(tempButton);
        }
        checkGameOver();

    }


}