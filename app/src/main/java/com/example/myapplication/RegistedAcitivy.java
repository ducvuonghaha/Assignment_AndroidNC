package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.RegistedAdapter;
import com.example.myapplication.dao.RegistedDAO;
import com.example.myapplication.model.RegistedCourse;

import java.util.List;

public class RegistedAcitivy extends AppCompatActivity {

    private RecyclerView rcvRegisted;
    private List<RegistedCourse> registedCourses ;
    private RegistedDAO registedDAO;
    private RegistedAdapter registedAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registed_acitivy);
        setTitle("Danh sách đăng ký");
        rcvRegisted = (RecyclerView) findViewById(R.id.rcvRegisted);
        registedDAO = new RegistedDAO(this) ;
        registedCourses = registedDAO.getAllRegisted();
        registedAdapter = new RegistedAdapter(this, registedCourses);
        rcvRegisted.setAdapter(registedAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        rcvRegisted.setLayoutManager(linearLayoutManager);
        registedAdapter.notifyDataSetChanged();

    }
}
