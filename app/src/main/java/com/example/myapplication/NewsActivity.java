package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.adapter.NewsAdapter;
import com.example.myapplication.model.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    String url = "https://ngoisao.net/rss/the-thao.rss";

    private RecyclerView rcvNews;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle("News");
        rcvNews = (RecyclerView) findViewById(R.id.rcvNews);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetNews getNews = new GetNews();
                getNews.execute(url);
            }
        });


    }

    public class GetNews extends AsyncTask<String, Long, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            String link = strings[0];
            ArrayList<News> arrayList = new ArrayList<>();

            try {
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParserFactory.setNamespaceAware(false);


                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();


                xmlPullParser.setInput(inputStream, "utf-8");

                //tien hanh doc du lieu tu xmlPullParser
                int eventType = xmlPullParser.getEventType();
                News news = null;
                String text = "";
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tag = xmlPullParser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tag.equalsIgnoreCase("item")) {
                                news = new News();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            text = xmlPullParser.getText();
                            break;
                        case XmlPullParser.END_TAG:

                            if (news != null) {
                                if (tag.equalsIgnoreCase("title")) {
                                    news.title = text;
                                } else if (tag.equalsIgnoreCase("description")) {
                                    news.description = text;
                                } else if (tag.equalsIgnoreCase("image")) {
                                    news.image = text;
                                } else if (tag.equalsIgnoreCase("pubDate")) {
                                    news.date = text;
                                } else if (tag.equalsIgnoreCase("item")) {
                                    news.url=link;
                                    arrayList.add(news);
                                }
                            }
                            break;
                    }
                    eventType = xmlPullParser.next();
                }


            } catch (MalformedURLException e) {
                //khi url bi loi sai cu phap...
                e.printStackTrace();
            } catch (IOException e) {

                //khi ket noi bi loi hoac khong ket noi dc
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> newsArrayList) {
            super.onPostExecute(newsArrayList);
            NewsAdapter newsAdapter = new NewsAdapter(NewsActivity.this, newsArrayList);
            rcvNews.setAdapter(newsAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewsActivity.this);
            rcvNews.setLayoutManager(linearLayoutManager);
            newsAdapter.notifyDataSetChanged();
        }
    }
}
