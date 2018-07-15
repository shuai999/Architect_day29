package cn.novate.architect_day29.rxjava;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 12:51
 * Version 1.0
 * Params:
 * Description:    线程
*/

public interface ObservableSource<T> {
    /**
     * 订阅
     */
    void subscribe(Observer<T> observer) ;
}
