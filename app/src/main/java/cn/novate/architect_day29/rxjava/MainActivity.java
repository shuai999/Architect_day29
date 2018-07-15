package cn.novate.architect_day29.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.novate.architect_day29.R;
import io.reactivex.annotations.NonNull;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 11:29
 * Version 1.0
 * Params:
 * Description:
*/

public class MainActivity extends AppCompatActivity {

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.image);

        /*// 1. Observable: 被观察对象
        // 2. subscribe: 注册订阅
        // 3. Observer: 观察者
        Observable.just("urlxxx")
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe() {
                        Log.e("TAG" , "onSubscribe") ;
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("TAG" , "onNext:"+s) ;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG" , "onError"+e) ;
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG" , "onComplete") ;
                    }
                });*/


        // 1. Observable：被观察者
        // 2. Observer：观察者
        // 3. subscribe：注册订阅
        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg")
                        .map(new Function<String, Bitmap>() {
                            @Override
                            public Bitmap apply(String urlPath) throws Exception {
                                // 5. 第五步：
                                URL url = new URL(urlPath) ;
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                InputStream inputStream = urlConnection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }
                        }).map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        bitmap = createWatermark(bitmap , "RxJava2.0") ;
                        return bitmap;
                    }
                }).map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        return bitmap;
                    }
                })
                        .subscribeOn(Schedulers.io())

                        .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void onNext(final Bitmap bitmap) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImage.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
            }
        }).start();

    }


    private Bitmap createWatermark(Bitmap bitmap, String mark) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        // 水印颜色
        p.setColor(Color.parseColor("#C5FF0000"));
        // 水印字体大小
        p.setTextSize(150);
        //抗锯齿
        p.setAntiAlias(true);
        //绘制图像
        canvas.drawBitmap(bitmap, 0, 0, p);
        //绘制文字
        canvas.drawText(mark, 0, h / 2, p);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmp;
    }
}
