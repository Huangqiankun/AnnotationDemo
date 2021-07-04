package com.hqk.annotationdemo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hqk.annotationdemo.R;
import com.hqk.annotationdemo.annotion.ContentView;
import com.hqk.annotationdemo.annotion.OnClick;
import com.hqk.annotationdemo.annotion.OnLongClick;
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

    @OnLongClick({R.id.btn_one, R.id.btn_two})
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                Toast.makeText(this, "你在长按按钮----------》1", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_two:
                Toast.makeText(this, "你在长按按钮----------》2", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }


    @OnClick({R.id.btn_one, R.id.btn_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                Toast.makeText(this, "你在点击按钮----------》1", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_two:
                Toast.makeText(this, "你在点击按钮----------》2", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
