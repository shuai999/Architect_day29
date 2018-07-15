package cn.novate.architect_day29.rxjava;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/23 13:02
 * Version 1.0
 * Params:
 * Description:
*/

public class ObservableJust<T> extends Observable<T> {

    private T item ;

    public ObservableJust(T item){
        this.item = item ;
    }
    @Override
    protected void subscribeActual(Observer<T> observer) {
        // 代理对象，why? 方便代码扩展，
        // 2. 第二步
        ScalarDisposable sd = new ScalarDisposable(observer,item);
        observer.onSubscribe();
        sd.run();
    }

    private class ScalarDisposable<T>{
        private Observer observer;
        private T item;

        public ScalarDisposable(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        public void run(){
            try {
                // 3. 第三步：observer -> MapObserver.onNext(String)
                observer.onNext(item);
                observer.onComplete();
            }catch (Exception e){
                observer.onError(e);
            }
        }
    }
}
