package com.purplechai.admin.kissanyojnaapp;

/**
 * Created by Harish on 03-06-2018.
 */
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AdapterHomeData extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataKY> data= Collections.emptyList();
    DataKY current;
    int currentPos=0;


    public String text;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterHomeData(Context context, List<DataKY> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_home, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataKY current=data.get(position);

        myHolder.cardView.setTag(position);

        myHolder.textTitle.setText(current.title);

        // Receiving side
        byte[] data = Base64.decode(current.webinfo, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        myHolder.textDes.setText(current.des);

        // load image into imageview using glide
        Glide.with(context).load(current.kyimage)
                .placeholder(R.drawable.active_dot)
                .error(R.drawable.nonactive_dot)
                .into(myHolder.ivKYimage);


        //Onclick Listener

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(),WebViewActivity.class);
                i.putExtra("URL_Main",current.webinfo);
                /*i.putExtra("Title_Main",current.title);
                i.putExtra("Des_Main",current.des);
                i.putExtra("Image_Main",current.kyimage);
                */context.startActivity(i);
            }
        });

        myHolder.share_card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String info = current.webinfo;
                    // Receiving side
                    byte[] data = Base64.decode(info, Base64.DEFAULT);
                    try {
                        text = new String(data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String shr = String.valueOf(Html.fromHtml(Html.fromHtml(text).toString()));
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = shr;
                    //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, current.title);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody + "\n\n\nShared From - Kisan Yojana App");
                    sharingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });

        /*//Like Button Code
        // set a default background to the button
        SharedPreferences sharedPreferences= context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String likes_count = sharedPreferences.getString("cardposition", null);
        if (likes_count.equals("1"))
        {
            myHolder.like_card_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_pressed));
        }
        else
        {
            myHolder.like_card_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_gray));
        }
        myHolder.like_card_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!liked){
                    myHolder.like_card_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_gray));
                    liked = true;
                    like = 0;
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("cardposition",String.valueOf(like));
                    editor.apply();
                    Toast.makeText(context.getApplicationContext(), String.valueOf(like), Toast.LENGTH_SHORT).show();

                }else{
                    myHolder.like_card_button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_pressed));
                    like++;
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("cardposition",String.valueOf(like));
                    editor.apply();
                    liked = false;
                    Toast.makeText(context.getApplicationContext(), String.valueOf(like), Toast.LENGTH_SHORT).show();
                }


            }
        });
*/
        //Like Button Logic Ends Here

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        TextView textTitle;
        ImageView ivKYimage;
        TextView textSize;
        TextView textDes;
        Button share_card_button;
        //Button like_card_button;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textTitle= (TextView) itemView.findViewById(R.id.textTitle);
            ivKYimage= (ImageView) itemView.findViewById(R.id.ivKYimage);
            textDes = (TextView) itemView.findViewById(R.id.textDes);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            share_card_button = (Button) itemView.findViewById(R.id.share_card_button);
            //like_card_button = (Button) itemView.findViewById(R.id.like_card_button);

        }

    }

}
