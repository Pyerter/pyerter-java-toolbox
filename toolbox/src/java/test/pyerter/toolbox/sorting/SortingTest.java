package pyerter.toolbox.sorting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class SortingTest {

    protected static class IntIDWrapper implements IntIdentifiable {
        protected int val;
        public IntIDWrapper(int val) { this.val = val; }
        public int toInt() { return val; }

        public static List<IntIDWrapper> getList(Integer[] ids) {
            List<IntIDWrapper> items = new ArrayList<>(ids.length);
            for (Integer id : ids) {
                items.add(new IntIDWrapper(id));
            }
            return items;
        }
    }

    @Test
    public void testBucketSort() {
        Integer[] itemsArr = new Integer[]{ 5, 2, 1, 6, 9, 11, 25, 36, 48, 18 };
        Integer[] itemsTarget = new Integer[]{ 1, 11, 2, 5, 25, 6, 36, 48, 18, 9 };
        List<IntIDWrapper> items = IntIDWrapper.getList(itemsArr);
        Sorting.bucketSort(items);
        Assertions.assertArrayEquals(itemsTarget, items.stream().map(IntIDWrapper::toInt).toArray());
    }

    @Test
    public void testRadixSort() {
        Integer[] itemsArr = new Integer[]{ 5, 2, 1, 6, 9, 11, 25, 36, 48, 18, -5, -2, -22, -42 };
        Integer[] itemsTarget = new Integer[]{ -42, -22, -5, -2, 1, 2, 5, 6, 9, 11, 18, 25, 36, 48 };
        List<IntIDWrapper> items = IntIDWrapper.getList(itemsArr);
        Sorting.radixSort(items);
        Assertions.assertArrayEquals(itemsTarget, items.stream().map(IntIDWrapper::toInt).toArray());
    }

}
