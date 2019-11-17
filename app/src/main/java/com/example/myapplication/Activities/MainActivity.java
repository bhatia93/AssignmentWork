package com.example.myapplication.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.myapplication.Adapters.CustomListAdapter;
import com.example.myapplication.Models.Row;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class  MainActivity  extends AppCompatActivity
{
    public List<Row> movieList = new ArrayList<Row>();
    public ListView listView;
    public CustomListAdapter adapter;
    public TextView tv_title;
    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
        callWebService();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callWebService();
            }
        });
    }

    private void init() {
        listView =  findViewById(R.id.list_data);
        tv_title =  findViewById(R.id.tv_title);
        imageView =  findViewById(R.id.imageView);
    }

    public void  callWebService()
    {
        AndroidNetworking.get("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.js")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener()
                {
                    @Override
                    public void onResponse(JSONObject responseObject)
                    {
                        if (!responseObject.toString().isEmpty())
                        {
                            setAdapter(responseObject);
                        }
                    }
                    @Override
                    public void onError(ANError error)
                    {
                        error.printStackTrace();
                    }
                });
    }

    private void setAdapter(JSONObject responseObject)
    {
        try
        {
            tv_title.setText(responseObject.optString("title"));

            JSONArray response = responseObject.getJSONArray("rows");

            for (int i = 0; i < response.length(); i++)
            {
                try
                {
                    JSONObject obj = response.getJSONObject(i);
                    Row movie = new Row();
                    movie.setDescription(obj.getString("description"));
                    movie.setTitle(obj.getString("title"));
                    movie.setImageHref(obj.getString("imageHref"));
                    movieList.add(movie);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            // notifying list adapter about data changes
            // so that it renders the list view with updated data
            adapter = new CustomListAdapter(MainActivity.this, movieList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}