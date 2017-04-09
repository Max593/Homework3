import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by max on 09/04/2017.
 */
public class MyMap <T> extends HashMap <T, List<T>> {
    public void add(T key, T val) {
        List<T> current = get(key);
        if (current == null) {
            current = new ArrayList<>();
            super.put(key, current);
        }
        current.add(val);
    }
}
