package com.example.bottomdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.login.Setqd;
import com.example.bottomdemo.utils.DBUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class T_setqddd extends AppCompatActivity implements View.OnClickListener {
    private Button back, setqddd;
    private String cid, continue_time, lng, lat, cname;
    private String starttime;
    private Setqd setqd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dingwei_set);
        back = (Button) findViewById(R.id.back9);
        back.setOnClickListener(this);
        setqddd = (Button) findViewById(R.id.setqddd);
        setqddd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setqddd:
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Intent getData = getIntent();
                cid = (String) getData.getSerializableExtra("cid");
                cname = (String) getData.getSerializableExtra("cname");
                starttime = (String) getData.getSerializableExtra("starttime");
                continue_time = (String) getData.getSerializableExtra("timing");
                setqd=new Setqd();
                setqd.setCid(cid);
                setqd.setStarttime(starttime);
                setqd.setContinue_time(continue_time);
                setqd.setLng("0");
                setqd.setLat("0");
                checkLogin(setqd);
                break;
            case R.id.back9:
                finish();
                break;
        }
    }
    class DBThread9 implements Runnable {//线程
        private Setqd setqd;
        private Context context;
        public void setUser(Setqd stu) {
            this.setqd = stu;
        }
        public void setContext(Context context) {
            this.context = context;
        }
        @Override
        public void run() {
            Looper.prepare();//为进程添加消息循环
            DBUtils.Insert_setqd(setqd);
            DBUtils.Insert_ALL_Stu(cid);
            Toast.makeText(T_setqddd.this, "发布成功", Toast.LENGTH_SHORT).show();
            finish();
            Looper.loop();
        }
    }
    private void checkLogin(Setqd u) {
        DBThread9 dt9 = new DBThread9();
        dt9.setUser(u);
        dt9.setContext(this);
        Thread thread9 = new Thread(dt9);
        thread9.start();
    }


}
