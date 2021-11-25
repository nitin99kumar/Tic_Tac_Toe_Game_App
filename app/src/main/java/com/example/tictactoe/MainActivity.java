package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button buttonReset;
    private Button [] buttons = new Button[9];

    int[] gameStates = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {1,2,0}, {4,5,3}, {7,8,6},
            {2,0,1}, {5,3,4}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };

    Boolean activePlayer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonReset = findViewById(R.id.Rematch);

        try{
            for(int i=0 ; i < buttons.length; i++){
                String buttonId = "btn" + i;
                int resourceId = getResources().getIdentifier(buttonId, "id" ,getPackageName());
                buttons[i] = (Button) findViewById(resourceId);
                buttons[i].setOnClickListener(this);

            }
        }
        catch(Exception e){
            Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View v) {

        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStartPointer = Integer.parseInt(buttonID.substring(buttonID.length()-1));


        if(activePlayer == true){
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.RED);
            gameStates[gameStartPointer] = 0;
            activePlayer = false;
        }
        else{
            ((Button) v).setText("O");
            ((Button) v).setTextColor(Color.BLACK);
            gameStates[gameStartPointer] = 1;
            activePlayer = true;
        }

        if(checkWinner()){
            if(!activePlayer == false){
                Toast.makeText(MainActivity.this, "Player O Won", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"Player X Won" , Toast.LENGTH_SHORT).show();
            }
        }

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });


    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningPos : winningPositions){
            if(gameStates[winningPos[0]] == gameStates[winningPos[1]] &&
                    gameStates[winningPos[1]] == gameStates[winningPos[2]] && gameStates[winningPos[0]] != 2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void playAgain(){

        activePlayer = true;

        for(int i=0; i<buttons.length; i++){
            gameStates[i] = 2;
            buttons[i].setText("");

        }

    }


}