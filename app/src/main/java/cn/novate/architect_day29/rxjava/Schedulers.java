package cn.novate.architect_day29.rxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/7/14 17:47
 * Version 1.0
 * Params:
 * Description:
*/

public abstract class Schedulers {

    static Schedulers MAIN_THREAD ;
    static Schedulers IO ;

    static {
        IO = new IOSchedulers() ;
        MAIN_THREAD = new MainSchedulers(new Handler(Looper.getMainLooper())) ;  // 这句代码保证是在主线程
    }

    public static Schedulers io() {
        return IO ;
    }

    public abstract void scheduleDirect(Runnable runnable);

    public static Schedulers mainThread(){
        return MAIN_THREAD ;
    }


    private static class IOSchedulers extends Schedulers {

        ExecutorService service ;

        public IOSchedulers() {
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r);
                }
            });
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            service.execute(runnable);
        }

    }

    private static class MainSchedulers extends Schedulers {
        Handler handler ;
        public MainSchedulers(Handler handler) {
            this.handler = handler ;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler , runnable);
            handler.sendMessage(message) ;
        }
    }
}
