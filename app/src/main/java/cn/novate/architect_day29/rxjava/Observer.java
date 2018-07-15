package cn.novate.architect_day29.rxjava;

import io.reactivex.annotations.NonNull;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 12:51
 * Version 1.0
 * Params:
 * Description:    观察者
*/

public interface Observer<T> {
    void onSubscribe() ;
    void onNext(@NonNull T item) ;
    void onError(@NonNull Throwable e) ;
    void onComplete() ;

}
