package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.RegistedDAO;
import com.example.myapplication.model.Course;
import com.example.myapplication.model.RegistedCourse;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.Holder> {

    private Context context ;
    private List<Course> courseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseAdapter.Holder holder, final int position) {
        courseList.get(position);
        holder.tvNameCourse.setText(courseList.get(position).getNameCourse());
        holder.tvContent.setText(courseList.get(position).getContentCourse());
        holder.imvRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogRegis = LayoutInflater.from(context).inflate(R.layout.dialog_regist_course, null);
                builder.setView(dialogRegis);
                builder.setTitle("Ban có muốn đăng kí khóa học này không ?");
                final AlertDialog dialog = builder.show();

                Button btnYes=dialogRegis.findViewById(R.id.btnYes);
                Button btnNo=dialogRegis.findViewById(R.id.btnNo);

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog
                                .dismiss();
                    }
                });

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RegistedDAO registedDAO = new RegistedDAO(context);
                        RegistedCourse registedCourse = new RegistedCourse();
                        registedCourse.setRegisted(courseList.get(position).getNameCourse());
                        registedCourse.setContent(courseList.get(position).getContentCourse());
                        long result = registedDAO.insertRegisted(registedCourse);
                        if (result > 0) {
                            Toast.makeText(context, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(context, "Bạn đã đăng kí khóa học này rồi", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogDetail = LayoutInflater.from(context).inflate(R.layout.dialog_details_course, null);
                builder.setView(dialogDetail);
                TextView tvTenKhoaHoc = dialogDetail.findViewById(R.id.tvNameCourse);
                TextView tvNoiDung = dialogDetail.findViewById(R.id.tvNoiDung);
                TextView tvNgayBatDau = dialogDetail.findViewById(R.id.tvNgayBatDau);
                TextView tvNgayKetThuc = dialogDetail.findViewById(R.id.tvNgayKetThuc);
                TextView tvHocPhi = dialogDetail.findViewById(R.id.tvHocPhi);

                tvTenKhoaHoc.setText(courseList.get(position).getNameCourse());
                tvNoiDung.setText(courseList.get(position).getContentCourse());
                tvNgayBatDau.setText(courseList.get(position).getStartCourse());
                tvNgayKetThuc.setText(courseList.get(position).getEndCourse());
                tvHocPhi.setText(courseList.get(position).getFeeCourse());

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imvRegist;
        private ImageView imvIconCourse;
        private TextView tvNameCourse;
        private Course course;
        private TextView tvContent;




        public Holder(@NonNull View itemView) {

            super(itemView);
            imvIconCourse = (ImageView) itemView.findViewById(R.id.imvIconCourse);
            tvNameCourse = (TextView) itemView.findViewById(R.id.tvNameCourse);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            imvRegist = (ImageView) itemView.findViewById(R.id.imvRegist);
        }
    }
}
