package cn.novate.architect_day29.simple3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.novate.architect_day29.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 11:29
 * Version 1.0
 * Params:
 * Description:
*/

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Observable: 被观察对象
        // 2. subscribe: 注册订阅
        // 3. Observer: 观察者
        Observable.just("urlxxx")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TAG","onSubscribe");  // onSubscribe
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("TAG","onNext = "+s);  // onNext = urlxxx
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG","onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG","onComplete");  // onComplete
                    }
                });

        // 去掉字符串的双引号
        String str = null ;
//        Log.e("TAG" , str.replace("\"","") ) ;
        if (str == null){
            str = "123" ;
            Log.e("TAG" , "str: "+ str) ;
        }else{
            Log.e("TAG" , "执行else逻辑: "+ str) ;
        }
    }

}
