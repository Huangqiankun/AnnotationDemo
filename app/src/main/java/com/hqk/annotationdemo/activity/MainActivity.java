package com.hqk.annotationdemo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hqk.annotationdemo.R;
import com.hqk.annotationdemo.annotion.ContentView;
import com.hqk.annotationdemo.annotion.ViewInject;
import com.hqk.annotationdemo.until.ViewUntil;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.text_welcome)
    TextView text_welcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUntil.inject(this);
        initData();
    }

    private void initData() {
        text_welcome.setText("我是通过注解后成功修改的值");
    }
}
