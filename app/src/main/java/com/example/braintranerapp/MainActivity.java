package com.example.braintranerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.braintranerapp.R.id.startButton;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    LinearLayout linearLayout;

    ArrayList<Integer>  answers=new ArrayList<Integer>();
    TextView resultTextView,pointTextView;

    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestion;

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        linearLayout.setVisibility(LinearLayout.VISIBLE);
        PlayAgain(findViewById(R.id.playagain));

    }
    public void ChooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestion++;
        pointTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        gnerateQuestion();
    }
    public void gnerateQuestion()
    {
        Random rand=new Random();
        int a= rand.nextInt(21);
        int b =rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCorrectAnswer =rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0; i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer==a+b)
                {
                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void PlayAgain(final View  view)
    {
        score=0;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        gnerateQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");


            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");
                resultTextView.setText("Done: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));

            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumtextview);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        pointTextView = findViewById(R.id.pointtextView);
        timerTextView=findViewById(R.id.timer);
        playAgainButton=findViewById(R.id.playagain);
        linearLayout=findViewById(R.id.linearlayout);





    }

}
