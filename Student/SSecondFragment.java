package com.example.bottomdemo.Student;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottomdemo.R;
import com.example.bottomdemo.login.LoginActivity;
import com.example.bottomdemo.login.Student;

public class SSecondFragment extends Fragment {

    private SSecondViewModel mViewModel;
    private Button Exit;
    private Student stu;
    private TextView stutext;
    public static SSecondFragment newInstance() {
        return new SSecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.s_second_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SSecondViewModel.class);
        // TODO: Use the ViewModel
        Exit=(Button)getActivity().findViewById(R.id.exit2);
        stutext=(TextView)getActivity().findViewById(R.id.studenttext);
        Intent getData=getActivity().getIntent();//接收参数
        stu=(Student) getData.getSerializableExtra("stu");
        System.out.println(stu.getSname());
        stutext.setText("你好"+stu.getSname());
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog=new AlertDialog.Builder(getActivity())
                        .setTitle("是否确认退出")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }

}
