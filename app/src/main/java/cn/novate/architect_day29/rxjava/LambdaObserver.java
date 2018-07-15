package cn.novate.architect_day29.rxjava;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2018/7/14.
 */

public class LambdaObserver<T> implements Observer<T> {

    private Consumer<T> onNext ;
    public LambdaObserver(Consumer<T> onNext){
        this.onNext = onNext ;
    }
    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(@NonNull T item) {
        onNext.onNext(item);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
