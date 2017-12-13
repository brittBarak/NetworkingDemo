package com.playground.arch.britt.networkingdemo.network.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageDownloader {

    public static void download(Context context, String url, ImageView view){
        Picasso.with(context).load(url).into(view);

    }
}
