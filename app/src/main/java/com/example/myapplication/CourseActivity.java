package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.adapter.CourseAdapter;
import com.example.myapplication.dao.CourseDAO;
import com.example.myapplication.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private TextView tvTitle;
    private RecyclerView rcvCourse;
    private LinearLayout lnlregistedcourse;
    private LinearLayout lnlschedule;
    private LinearLayout lnlexam;
    private CourseAdapter courseAdapter ;
    private CourseDAO courseDAO;
    private Course course;
    private List<Course> courseList;
    private LinearLayoutManager linearLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("Khóa học");
        init();

        courseDAO = new CourseDAO(this);

        courseList = new ArrayList<>();
        courseDAO.insertCourse(new Course("Lập trình Android","Khóa học lập trình Android từ cơ bản đến nâng cao","20-10-2019","20-10-2020","10.000.000 VND"));
        courseDAO.insertCourse(new Course("Lập trình Unity","Khóa học Unity có giáo trình gồm nhiều dự án","20-10-2019","20-10-2020","8.000.000 VND"));
        courseDAO.insertCourse(new Course("Lập trình Designer","Khóa học giúp bạn nắm vững kiến thức CS6, AI","20-10-2019","20-10-2020","15.000.000 VND"));
        courseDAO.insertCourse(new Course("Lập trình Php","Khóa học cung cấp cho học viên có khả năng phân tích Website","20-10-2019","20-10-2020","7.000.000 VND"));

        courseList = courseDAO.getAllCourse();

        courseAdapter = new CourseAdapter(this, courseList);
        linearLayoutManager = new LinearLayoutManager(this);

        rcvCourse.setAdapter(courseAdapter);
        rcvCourse.setLayoutManager(linearLayoutManager);

        courseAdapter.notifyDataSetChanged();

        lnlregistedcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, RegistedAcitivy.class);
                startActivity(intent);
            }
        });
    }


    public void init() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        rcvCourse = (RecyclerView) findViewById(R.id.rcvCourse);
        lnlregistedcourse = (LinearLayout) findViewById(R.id.lnlregistedcourse);
        lnlschedule = (LinearLayout) findViewById(R.id.lnlschedule);
        lnlexam = (LinearLayout) findViewById(R.id.lnlexam);

    }
}
