package app.util;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import java.util.Collection;

public class Collections3 {
    public static <T> void each(Collection<T> collection, ICallback<T> callback) {
        int flag = 0;
        for (T t : collection) {
            callback.doWork(flag++, t);
        }
    }

    public static <E> Collection<E> filter(
            Collection<E> unfiltered, Predicate<? super E> predicate) {
        Collection<E> rst = Lists.newLinkedList();
        for (E one : unfiltered) {
            if (predicate.apply(one)) {
                rst.add(one);
            }
        }
        return rst;
    }
    public static <F, T> Collection<T> transform(Collection<F> fromCollection,
                                                 Function<? super F, T> function) {
        Collection<T> rst = Lists.newLinkedList();
        for (F one : fromCollection) {
            rst.add(function.apply(one));
        }
        return rst;
    }
}
