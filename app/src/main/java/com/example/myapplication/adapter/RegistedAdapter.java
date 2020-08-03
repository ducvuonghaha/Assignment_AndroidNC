package com.example.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.RegistedDAO;
import com.example.myapplication.model.RegistedCourse;

import java.util.List;

public class RegistedAdapter extends RecyclerView.Adapter<RegistedAdapter.Holder> {

    private Context context;
    private List<RegistedCourse> registedCourseList;

    public RegistedAdapter(Context context, List<RegistedCourse> registedCourseList) {
        this.context = context;
        this.registedCourseList = registedCourseList;
    }

    @NonNull
    @Override
    public RegistedAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.registed_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistedAdapter.Holder holder, final int position) {
        holder.tvNameRegisted.setText(registedCourseList.get(position).getRegisted());
        holder.tvContentRegisted.setText(registedCourseList.get(position).getContent());
        holder.deleteRegisted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
                View dialogDetail = LayoutInflater.from(context).inflate(R.layout.dialog_delete_registed, null);
                aBuilder.setView(dialogDetail);
                aBuilder.setTitle("Bạn có muốn xóa khóa học này không ?");
                final AlertDialog alertDialog = aBuilder.show();
                 Button btnCancelDelete;
                 Button btnDelete;

                btnCancelDelete = (Button) alertDialog.findViewById(R.id.btnCancelDelete);
                btnDelete = (Button) alertDialog.findViewById(R.id.btnDelete);

                btnCancelDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nameRegist = registedCourseList.get(position).registed;
                        RegistedDAO registedDAO = new RegistedDAO(context);
                        long result = registedDAO.deleteRegisted(nameRegist);
                        if(result>0){
                            Toast.makeText(context, "Hủy đăng kí thành công", Toast.LENGTH_SHORT).show();
                            registedCourseList.remove(position);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Hủy đăng kí không thành công", Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return registedCourseList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView tvNameRegisted;
        private ImageView deleteRegisted;
        private RegistedCourse registedCourse;
        private TextView tvContentRegisted;





        public Holder(@NonNull View itemView) {
            super(itemView);
            tvNameRegisted = (TextView) itemView.findViewById(R.id.tvNameRegisted);
            deleteRegisted = (ImageView) itemView.findViewById(R.id.deleteRegisted);
            tvContentRegisted = (TextView) itemView.findViewById(R.id.tvContentRegisted);
        }
    }
}
