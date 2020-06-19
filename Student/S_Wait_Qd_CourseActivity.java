package com.example.bottomdemo.Student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomdemo.Adapter.S_Finish_Qd_Adapter;
import com.example.bottomdemo.Adapter.S_Wait_Qd_Adapter;
import com.example.bottomdemo.CourseActivity;
import com.example.bottomdemo.R;
import com.example.bottomdemo.T_choice_student;
import com.example.bottomdemo.login.Finish_qd;
import com.example.bottomdemo.login.Student;
import com.example.bottomdemo.login.Student_info;
import com.example.bottomdemo.utils.DBUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S_Wait_Qd_CourseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<Finish_qd> mData=null;
    private Context mContext;
    private S_Wait_Qd_Adapter mAdapter=null;
    private ListView listView;
    private Button back;
    private Student stu;
    private Student_info student_info;
    private Handler handler=null;
    String sno,sname;
    Map map;
    Map map2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wait_qd);
        mContext=this;
        listView=(ListView)findViewById(R.id.wait_qd);
        listView.setOnItemClickListener(this);
        back=findViewById(R.id.back16);
        Intent getData=getIntent();
        stu=(Student)getData.getSerializableExtra("stu");
        sno=stu.getSno();
        sname=stu.getSname();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handler=new Handler();
        new Thread(){
            public void run(){
                map=new HashMap();
                mData= DBUtils.Wait_qd_by_sno(stu.getSno());
                for(Finish_qd cr:mData){
                    map.put(cr.getCid(),DBUtils.Count_finish(cr.getCid()));
                }
                handler.post(runnableUi);
            }
        }.start();

    }
    Runnable runnableUi=new Runnable() {
        @Override
        public void run() {
            mAdapter=new S_Wait_Qd_Adapter(mData,mContext,map);
            listView.setAdapter(mAdapter);
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(S_Wait_Qd_CourseActivity.this, S_wait_qd_FaceActivity.class);
        intent.putExtra("finishqd",mData.get(position));
        intent.putExtra("stu",stu);
        startActivity(intent);
    }
}