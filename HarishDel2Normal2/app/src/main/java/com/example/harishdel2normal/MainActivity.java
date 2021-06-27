package com.example.harishdel2normal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Views.CustomView;

public class MainActivity extends AppCompatActivity {


    public CustomView customView;
    public TextView t1;
    public TextView t2;
    public TextView t3;
    public Button b1;
    public int time = 25 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = (CustomView) findViewById(R.id.customView);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        b1 = (Button) findViewById(R.id.button);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        Thread thread = new Thread(){
            public  void run(){
                while (!isInterrupted()){
                    try {
                        Thread.sleep(time);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customView.move();
                                int highScore = settings.getInt("HIGH_SCORE",0);
                                if (customView.cy >2100f) {
                                    t1.setText(" SCORE :" + customView.count);
                                    if (customView.count >= highScore){
                                        t3.setText("HIGH SCORE :" + customView.count);
                                        editor.putInt("HIGH_SCORE",customView.count);
                                        editor.commit();
                                    } else {
                                        t3.setText("HIGH SCORE :" + highScore);
                                    }
                                    t1.setVisibility(View.VISIBLE);
                                    t2.setVisibility(View.VISIBLE);
                                    t3.setVisibility(View.VISIBLE);
                                    b1.setVisibility(View.VISIBLE);

                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public void tryAgain(View view){

        customView.cx = (int) Math.floor((Math.random() * (150 + 700 - 25)) + 150);;
        customView.cy = customView.z;

        customView.count = 0;
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);

    }
}