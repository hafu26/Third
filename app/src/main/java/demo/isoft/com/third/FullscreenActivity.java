package demo.isoft.com.third;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class FullscreenActivity extends AppCompatActivity {
        Handler handler=new Handler();
        Handler skiphandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                skip.setText("跳过"+Maxtimer);
            }
        };
        Timer timer;
        TimerTask task;
        int Maxtimer=5;
        TextView skip;
        Runnable runnable;
        private boolean isFirstuse;
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fullscreen);
            skip=(TextView)findViewById(R.id.skip);
            skip.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    timer.cancel();
                    SharedPreferences loginStatus = getSharedPreferences("loginStatus1",MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginStatus.edit();
                    Intent intent=new Intent();
                    if(isFirstuse){
                        intent.setClass(FullscreenActivity.this,GrideActivity.class);
                        editor.putBoolean("isFirstUse",false);
                    }
                    else {
                        intent.setClass(FullscreenActivity.this,LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();
                    editor.commit();
                }
            });
            if(runnable==null){
                runnable=new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences loginStatus = getSharedPreferences("loginStatus1",MODE_PRIVATE);
                        SharedPreferences.Editor editor = loginStatus.edit();
                        Intent intent=new Intent();
                        if(isFirstuse){
                            intent.setClass(FullscreenActivity.this,GrideActivity.class);
                            editor.putBoolean("isFirstUse",false);
                        }
                        else {
                            intent.setClass(FullscreenActivity.this,LoginActivity.class);
                        }
                        startActivity(intent);
                        finish();
                        editor.commit();
                    }
                };
            }
            final SharedPreferences preferences = getSharedPreferences("loginStatus1",MODE_PRIVATE);
            isFirstuse = preferences.getBoolean("isFirstUse", true);
            handler.postDelayed(runnable,5000);
            if(timer==null)//单例模式
                timer =new Timer();
            if(task==null)
                task =new TimerTask() {
                @Override
                public void run() {
                    Maxtimer--;
                    //                    skip.setText("跳过"+Maxtimer);
                    skiphandler.sendEmptyMessage(0);
                    if(Maxtimer==0){
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task,1000,1000);
        }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }
}
