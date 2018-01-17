package com.example.administrator.glide;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                show();
                break;
        }
    }

    private void show() {
        String[] item = {"1、加载网络图片",
                "2、加载工程中的图片资源",
                "3、加载外部存储中的图片",
                "4、加载Uri格式的资源",
                "5、监听图片展示成功或失败",
                "6、占位图、错误图",
                "7、内存缓存、磁盘缓存",
                "8、加载gif图",
                "9、gif图展示第一帧",
                "10、展示圆角图片（圆图片）"};
        new AlertDialog.Builder(this)
                .setTitle("Glide的操作")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                test1();
                                break;
                            case 1:
                                test2();
                                break;
                            case 2:
                                test3();
                                break;
                            case 3:

                                break;
                            case 4:
                                test5();
                                break;
                            case 5:
                                test6();
                                break;
                            case 6:
                                test7();
                                break;
                            case 7:
                                test8();
                                break;
                            case 8:
                                test9();
                                break;
                            case 9:
                                test10();
                                break;
                        }
                    }
                }).create().show();
    }

    private void test1() {
        String IMG_URL = ImageUrls.IMAGE_URL_0;

        Glide.with(this)
                .load(IMG_URL)
                .into(mImageView);
    }

    private void test2(){
        Glide.with(this)
                .load(R.drawable.girl)
                .into(mImageView);
    }

    private void test3(){
        String path = Environment.getExternalStorageDirectory().getPath();
        String cwj_b = "cwj_b";

        File file = new File(path, cwj_b);
        Glide.with(this)
                .load(file)
                .into(mImageView);
    }

    private void test5(){
        String imageUrl1 = ImageUrls.IMAGE_URL_1;

        Glide.with(this)
                .load(imageUrl1)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(MainActivity.this, "fail : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .into(mImageView);
    }

    private void test6(){
        RequestOptions error = new RequestOptions()
                .placeholder(R.drawable.load)
                .error(R.drawable.fail);

        Glide.with(this)
                .load(ImageUrls.IMAGE_URL_1)
                .apply(error)
                .into(mImageView);
    }

    private void test7(){
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(this)
                .load(ImageUrls.IMAGE_URL_8)
                .apply(options)
                .into(mImageView);
    }

    private void test8(){
        Glide.with(this)
                .load(ImageUrls.GIF_URL_0)
                .into(mImageView);
    }

    private void test9(){
        Glide.with(this)
                .asBitmap()
                .load(ImageUrls.GIF_URL_0)
                .into(mImageView);
    }

    private void test10(){
        int radius = 200;

        RequestOptions options = new RequestOptions().transform(new RoundedCorners(radius));

        Glide.with(this)
                .load(ImageUrls.IMAGE_URL_1)
                .apply(options)
                .into(mImageView);
    }
}
