package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 设置标题
        setTitle("Hello World");
        
        // 获取TextView并设置文本
        TextView textView = findViewById(R.id.text_hello);
        textView.setText("Hello World!\n欢迎使用Jenkins构建的Android应用！");
    }
} 