package cn.novate.architect_day29.rxjava;

/**
 * Created by Administrator on 2018/7/14.
 */

public interface Consumer<T> {
    void onNext(T item) ;
}
