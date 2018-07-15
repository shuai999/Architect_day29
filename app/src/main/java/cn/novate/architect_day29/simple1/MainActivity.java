package cn.novate.architect_day29.simple1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.novate.architect_day29.R;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 11:29
 * Version 1.0
 * Params:
 * Description:    网络图片加载水印一般写法
*/

public class MainActivity extends AppCompatActivity {


    private ImageView iv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap)msg.obj;
            iv.setImageBitmap(bitmap);
        }
    } ;
    private String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);




        // 网络图片加水印一般写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建url地址
                    URL url = new URL("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg") ;
                    // 建立连接
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    // 获取输入流
                    InputStream inputStream = urlConnection.getInputStream();
                    TestBean testBean = new TestBean() ;
                    testBean.setInputStream(inputStream);
                    Log.e("TAG" , "inputStream: " + inputStream+ ", getInputStream(): " + testBean.getInputStream()) ;
                    // 把流转为bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(testBean.getInputStream());
                    // 加一个水印
                    bitmap = createWatermark(bitmap , "RxJava2.0") ;
                    // 显示到ImageView上边
                    Message msg = Message.obtain() ;
                    msg.obj = bitmap ;
                    handler.sendMessage(msg) ;

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
