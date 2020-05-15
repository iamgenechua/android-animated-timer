package com.example.spritetimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    ImageView img;

    CountDownTimer timer;
    boolean timerActive = false;

    public void changePicture(int num) {
        int numOfSprites = 10;
        int key = num % numOfSprites;
        if (key == 0) {
            img.setImageResource(R.drawable.run0);
        } else if (key == 1) {
            img.setImageResource(R.drawable.run1);
        } else if (key == 2) {
            img.setImageResource(R.drawable.run2);
        } else if (key == 3) {
            img.setImageResource(R.drawable.run3);
        } else if (key == 4) {
            img.setImageResource(R.drawable.run4);
        } else if (key == 5) {
            img.setImageResource(R.drawable.run5);
        } else if (key == 6) {
            img.setImageResource(R.drawable.run6);
        } else if (key == 7) {
            img.setImageResource(R.drawable.run7);
        } else if (key == 8) {
            img.setImageResource(R.drawable.run8);
        } else {
            img.setImageResource(R.drawable.run9);
        }
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        textView.setText(minutes + " : " + String.format("%02d", seconds));
    }

    public void resetTimer() {
        timer.cancel();
        button.setText("RESET");
        seekBar.setEnabled(true);
        timerActive = false;
    }

    public void buttonClicked(View view) {
        if (timerActive) {
            resetTimer();
        } else {
            timerActive = true;
            seekBar.setEnabled(false);
            button.setText("STOP");

            timer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 50) {
                int imageCount = 0;

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer(((int) (millisUntilFinished / 1000)));
                    changePicture(imageCount);
                    imageCount++;
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    button.setText("RESTART");

                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setText("START");
        img = (ImageView) findViewById(R.id.imageView);
        changePicture(0); // set picture to 0 first

        int max = 600; // maximum that the timer will go is 600 seconds (10 minutes)
        seekBar.setMax(max); // set this as maximum point of timer
        int startingPoint = max / 2; // starting point is halfway
        seekBar.setProgress(startingPoint); // set the timer starting point as this
        updateTimer(startingPoint);
        /**
         * Update the textView as we change seekBar Progress
         */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
