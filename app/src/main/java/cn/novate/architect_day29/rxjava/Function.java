package cn.novate.architect_day29.rxjava;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/7/14 10:36
 * Version 1.0
 * Params:
 * Description:
*/

public interface Function<T,R> {
    R apply(T t) throws Exception;
}
