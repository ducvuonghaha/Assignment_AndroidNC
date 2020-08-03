package com.example.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.News;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.Holder holder, final int position) {
        holder.tvTitle.setText(newsList.get(position).title);
        holder.tvDate.setText(newsList.get(position).date);
        holder.tvDes.setText(newsList.get(position).description);
        String img=newsList.get(position).image;
        new DownLoadImageTask(holder.imgData).execute(img);
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View view=LayoutInflater.from(context).inflate(R.layout.infor_adapter,null);
                builder.setView(view);

                TextView tvTitleDialog=view.findViewById(R.id.tvTitleDialog);
                TextView tvDateDialog=view.findViewById(R.id.tvDateDialog);
                TextView tvDesDialog=view.findViewById(R.id.tvDesDialog);
                ImageView imgDialog=view.findViewById(R.id.imgDialog);

                String image=newsList.get(position).image;
                new DownLoadImageTask(imgDialog).execute(image);
                tvDesDialog.setText(newsList.get(position).description);
                tvTitleDialog.setText(newsList.get(position).title);
                builder.show();
            }
        });
        holder.tvDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(position).url));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }


    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDes;
        private ImageView imgData;
        private TextView tvDate;


        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDes = (TextView) itemView.findViewById(R.id.tvDes);
            imgData = (ImageView) itemView.findViewById(R.id.imgData);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);

        }
    }
}
