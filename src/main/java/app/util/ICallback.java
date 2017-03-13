package app.util;


public interface ICallback<T> {
    public void doWork(int index, T t);
}
