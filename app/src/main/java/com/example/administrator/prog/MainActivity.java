package com.example.administrator.prog;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(MainActivity.this);
                progress.setMax(100);
                progress.setTitle("請稍候");
                progress.setMessage("請耐心等待...");
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  //選擇進度條風格
                progress.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0; //進度初始化
                        while (i<100){
                            i++;
                            try{  //延遲1秒
                                Thread.sleep(100);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            progress.setProgress(i);  //當下進度
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "執行了！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}