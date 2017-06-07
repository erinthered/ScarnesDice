package com.ewilliams.scarnesdice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int user_score = 0;
    private int computer_score = 0;
    private int turn_score = 0;
    private int currentRoll = 0;
    public enum Player {USER, COMPUTER}
    private Player player = Player.USER;
    private static final int NUM_DICE_FACES = 6;
    private Random random = new Random();
    ImageView img;
    Button button1, button2, button3;
    TextView textView;
    String scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = Player.USER;
        img = (ImageView) findViewById(R.id.imageView);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById(R.id.textView);

    }

    /** Rolls dice when user clicks Roll button */
    public void rollDice(View view) {
        currentRoll = random.nextInt(NUM_DICE_FACES) + 1;
        switch (currentRoll) {
            case 1 :
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice1, null));
                break;
            case 2 :
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice2, null));
                break;
            case 3 :
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice3, null));
                break;
            case 4 :
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice4, null));
                break;
            case 5 :
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice5, null));
                break;
            case 6 :
                img.setImageDrawable(getResources().getDrawable(R.drawable.dice6, null));
                break;
        }
        if(currentRoll == 1) {
            turn_score = 0;
            button2.performClick();
        }
        if(currentRoll != 1) {
            turn_score = turn_score + currentRoll;
        }

        textView.setText(updateText(user_score, computer_score, turn_score));
    }

    /* Resets game when user clicks Reset button */
    public void resetGame(View view) {
        turn_score = 0;
        user_score = 0;
        computer_score = 0;

        textView.setText(updateText(user_score, computer_score, turn_score));
    }

    /* Ends round and increments total score when user clicks Hold button */
    public void endTurn(View view) {
        if(player == Player.USER) {
            user_score = user_score + turn_score;
        }
        else if(player == Player.COMPUTER) {
            computer_score = computer_score + turn_score;
        }
        turn_score = 0;
        textView.setText(updateText(user_score, computer_score, turn_score));
        if (player == Player.USER) {
            player = Player.COMPUTER;
            computerTurn();
        }
        else if (player == Player.COMPUTER) {
            player = player.USER;
        }
    }

    private String updateText(int userScore, int computerScore, int turnScore) {
        String text = "Your Score: " + user_score + " Computer Score: " + computer_score +
                " Turn Score: " + turn_score;
        return text;
    }

    private void computerTurn() {
        button1.setEnabled(false);
        button2.setEnabled(false);
        String notification;

        while(player == Player.COMPUTER) {
            rollDice(null);

            if(currentRoll == 1) {
                notification = "Computer rolled a one";
                textView.setText(notification);
            }
            else if(turn_score >= 20) {
                notification = "Computer Holds";
                textView.setText(notification);
                button2.performClick();
            }
        }

        button1.setEnabled(true);
        button2.setEnabled(true);

    }
}


