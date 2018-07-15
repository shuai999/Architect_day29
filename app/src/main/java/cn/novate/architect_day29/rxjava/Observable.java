package cn.novate.architect_day29.rxjava;


import android.graphics.Bitmap;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 12:50
 * Version 1.0
 * Params:
 * Description:    被观察对象
*/

public abstract class Observable<T> implements ObservableSource<T>{


    public static <T> Observable<T> just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    // 1.0源码：是把这里留出来，是在2.0源码才写的
    private static <T> Observable<T> onAssembly(Observable<T> source) {
        // 留出来了
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer)  {
        subscribeActual(observer);
    }

    public void subscribe(Consumer<T> onNext){
        subscribe(onNext,null,null);
    }

    private void subscribe(Consumer<T> onNext , Consumer<T> error , Consumer<T> complete){
        subscribe(new LambdaObserver<T>(onNext));
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public <R> Observable<R> map(Function<T,R> function){
        return onAssembly(new ObservableMap<>(this , function)) ;
    }

    public Observable<Bitmap> subscribeOn(Schedulers schedulers) {
        return onAssembly(new ObservableSchedulers(this,schedulers)) ;
    }
}
