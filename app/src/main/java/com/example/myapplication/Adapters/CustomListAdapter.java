package com.example.myapplication.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Models.Row;
import com.example.myapplication.R;
import com.example.myapplication.Utils.ImageLoader;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Row> movieItems;
    ImageLoader imageLoader;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Row> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        this.imageLoader = new ImageLoader(activity.getApplicationContext());
    }
 
    @Override
    public int getCount()
    {
        return movieItems.size();
    }
 
    @Override
    public Object getItem(int location)
    {
        return movieItems.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
 
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);
        ImageView imageView =  convertView.findViewById(R.id.thumbnail);

        // getting movie data for the row
        Row m = movieItems.get(position);
 
        // thumbnail image
     //   thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
         
        // title
        title.setText(m.getTitle());
        if(m.getImageHref()!=null && !m.getImageHref().toString().isEmpty())
        {
            imageLoader.DisplayImage(m.getImageHref().toString(), imageView);
//            imageLoader.DisplayImage("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg", imageView);
        }

        // rating
      //  rating.setText("Rating: " + String.valueOf(m.getRating()));
         
        // genre
//        String genreStr = "";
//        for (String str : m.setTitle();) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
//        genre.setText(genreStr);
//
//        // release year
//        year.setText(String.valueOf(m.getYear()));
 
        return convertView;
    }
 
}