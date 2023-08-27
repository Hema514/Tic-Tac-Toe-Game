package aliet.cse.co.tictactoe;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];

    private boolean player1Turn =true;

    private int roundCount;

    private int[] arr={1,2,3,4,5,6,7,8,9};
    private int a,i,j;
    private int player1points;
    private int player2points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textViewPlayer1=findViewById(R.id.text_view_p1);
        textViewPlayer2=findViewById(R.id.text_view_p2);

        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                String buttonID ="button_" +i + j;
                int resID =getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] =findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }

        }

        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }





    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setTextColor(Color.RED);
            ((Button) v).setText("X");


        }
        roundCount++;
        set2();



    }
    public void set2() {
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn=!player1Turn;
            if (!player1Turn){
                set();
                set2();
            }
        }

    }


    private  boolean checkForWin() {
        String[][] field=new String[3][3];

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for (int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return  true;
            }

        }
        for (int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return  true;
            }

        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return  true;
        }
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return  true;
        }

        return  false;
    }
    private  void  player1Wins(){
        player1points++;
        Toast.makeText(this, "player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        handler.postDelayed(r,500);
    }

    private void  player2Wins(){
        player2points++;
        Toast.makeText(this, "Computer  wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        handler.postDelayed(r,500);
    }
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        handler.postDelayed(r,500);
    }
    private void  updatePointsText(){
        textViewPlayer1.setText("player : "+player1points);
        textViewPlayer2.setText("Computer: "+player2points);
    }
    Handler handler=new Handler();
    final Runnable r=new Runnable() {
        @Override
        public void run() {
            resetBoard();
        }
    };

    private void resetBoard(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;

    }

    private  void  resetGame(){
        player1points=0;
        player2points=0;
        updatePointsText();
        resetBoard();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount =savedInstanceState.getInt("roundCount");
        player1points =savedInstanceState.getInt("player1points");
        player2points =savedInstanceState.getInt("player2pointers");
        player1Turn =savedInstanceState.getBoolean("player1Turn");
    }
    public boolean set1()
    {
        if(buttons[i][j].getText()=="")
        {
            return true;
        }
        else{
            return false;
        }
    }
    public void set()
    {
        a = arr[new Random().nextInt(8)+0];
        set1(a);
        while(buttons[i][j].getText()!=""){
            a =arr[new Random().nextInt(8)+0];
            set1(a);
        }
        buttons[i][j].setTextColor(Color.BLACK);
        buttons[i][j].setText("O");
        roundCount++;
    }
    public void set1(int a)
    {
        switch(a){
            case 1:
                i=0;j=0;
                break;
            case 2:
                i=0;j=1;
                break;
            case 3:
                i=0;j=2;
                break;
            case 4:
                i=1;j=0;
                break;
            case 5:
                i=1;j=1;
                break;
            case 6:
                i=1;i=2;
                break;
            case 7:
                i=2;j=0;
                break;
            case 8:
                i=2;j=1;
                break;
            case 9:
                i=2;j=2;
                break;
        }
    }
}