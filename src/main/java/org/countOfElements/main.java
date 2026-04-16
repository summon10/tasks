package countOfElements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public <K, V> Map<K, Integer> countOfElements(List<K> elements) {

        Map<K, Integer> result = new HashMap<K, Integer>();

        for (K item : elements) {
            result.merge(item, 1, Integer::sum);
        }
        return result;
    }
}
