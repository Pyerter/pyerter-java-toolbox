package pyerter.toolbox.sorting;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

/**
 * This class stores sorting methods for either {@code Comparable} objects or {@code IntIdentifiable} objects,
 * where {@code IntIdentifiable} objects have access to better sorting techniques, such as {@code RadixSort}.
 */
public class Sorting {

    public static final int MAX_INT_DIGITS = 9;
    public static final int MIN_INT_DIGITS = 0;
    public static final int INT_BUCKETS = 10;
    public static final int SIGNED_INT_BUCKETS = 19;

    /**
     * This method is an in-place, stable sort. It utilizes the integer representation of the given objects
     * to create 10 buckets that it sorts items into. The digit used is the 1s place digit.
     * @param items the array of items to be sorted.
     * @return The sorted array, in ascending order.
     * @param <T> an object that extends {@code IntIdentifiable}, allowing this method to take advantage of
     *           sorting objects into 10 buckets at a time.
     */
    public static <T extends IntIdentifiable> boolean bucketSort(List<T> items) {
        return bucketSort(items, 0);
    }

    /**
     * This method is an "in-place" (still O(N) storage), stable sort. It utilizes the integer representation of the
     * given objects to create 10 buckets that it sorts items into.
     * @param items The list of items to be sorted.
     * @param digit The digit used to sort items into buckets. Because the max integer value is 2,147,483,647
     *              (10 digits), this parameter is bounded, inclusively, from 0 to 9 [MIN_INT_DIGITS, MAX_INT_DIGITS].
     * @param <T> A type that extends {@code IntIdentifiable}, allowing this method to take advantage of
     *           sorting objects into 10 buckets at a time.
     * @return The sorted array, in ascending order.
     */
    public static <T extends IntIdentifiable> boolean bucketSort(List<T> items, int digit) {
        int[] bucketCounts = new int[SIGNED_INT_BUCKETS]; // track how many items should go in each bucket
        int[] bucketIndexes = new int[SIGNED_INT_BUCKETS]; // create array of indexes representing current index of each bucket
        // [0, 1, ..., 7, 8] represents [-9, -8, ..., -2, -1]
        // [9, 10, ..., 17, 18] represents [0, 1, ..., 8, 9]

        if (digit > MAX_INT_DIGITS) digit = MAX_INT_DIGITS; // bound by max digit value
        else if (digit < MIN_INT_DIGITS) digit = MIN_INT_DIGITS; // bound by min digit value

        boolean changedOrder = false; // keep track if the order changed at all

        // To get digit, e.g. digit 3 of 12345, divide by 100 (-> 123) and take % 10 (-> 3).
        int divider = (int)Math.pow(10, digit); // digits will be divided by 10^digit

        // int totalBucketCalculations = 0; // debug
        // int totalSwaps = 0; // debug
        // int totalLoops = 0; // debug

        // System.out.println("Summing buckets...");
        // First, iterate through to see how many objects should go in each bucket
        // Making it so that bucketIndexes has the first index of each bucket
        for (int i = 0; i < items.size(); i++) {
            int val = items.get(i).toInt(); // get int value
            int destinationBucket = (val / divider) % 10 + 9; // get target bucket
            bucketCounts[destinationBucket]++; // increment count of target bucket
            // totalBucketCalculations++; // debug
            // totalLoops++; // debug
        }

        // System.out.println("Indexing buckets...");
        // Update the array corresponding to the bucket start indexes
        for (int i = 1; i < SIGNED_INT_BUCKETS; i++) {
            bucketIndexes[i] = bucketIndexes[i - 1] + bucketCounts[i - 1]; // last index + count of previous bucket
            // totalLoops++; // debug
        }

        // System.out.println("Mapping buckets...");
        // Create an array, mapping the index of the sorted array to the item's index of the unsorted array
        int[] mappedIndexes = new int[items.size()];
        int[] currentBucketIndexes = bucketIndexes.clone(); // maintain a list of the current index being appended to
        for (int i = 0; i < items.size(); i++) {
            int val = items.get(i).toInt(); // get int value
            int destinationBucket = (val / divider) % 10 + 9; // get target bucket
            int destinationIndex = currentBucketIndexes[destinationBucket]; // get next index of target bucket
            mappedIndexes[destinationIndex] = i; // set destination index source to current
            if (!changedOrder && destinationIndex != i) changedOrder = true; // mark if order changes
            currentBucketIndexes[destinationBucket]++; // increment current bucket position
            // totalBucketCalculations++; // debug
            // totalLoops++; //debug
        }

        // Shortcut the method if no swapping is necessary!
        if (!changedOrder) {
            // System.out.println("Shortcut - Finished bucket sort over length " + items.size() + " with " + totalBucketCalculations + " bucket calculations, " + totalSwaps + " swaps, and " + totalLoops +" loops."); // debug
            return false;
        }

        // System.out.println("Mapped buckets: " + java.util.Arrays.toString(mappedIndexes));

        // Create a list of booleans indicating if a number was already swapped
        BitSet indexChecked = new BitSet(items.size());
        int totalChecks = 0; // additionally, we can shortcut early if we sort everything halfway through

        // System.out.println("Swapping buckets...");
        // Now, correct the array so that items are in their target indexes
        for (int i = 0; i < items.size(); i++) {
            // totalLoops++; // debug
            if (indexChecked.get(i))
                if (totalChecks >= items.size()) break;
                else continue;
            int targetIndex = i; // track current index
            int sourceIndex = mappedIndexes[i]; // get index where the item is right now
            indexChecked.set(targetIndex, true);
            totalChecks++; // debug
            while (targetIndex != sourceIndex && i != sourceIndex) { // if it's not where it should be
                Collections.swap(items, sourceIndex, targetIndex); // swap!
                //System.out.println(java.util.Arrays.toString(items.stream().map(a -> a.toInt()).toArray()));
                indexChecked.set(sourceIndex, true);
                // Now the item at index is out of place...
                // So we need to make sure the item we swapped with is put where it should go
                targetIndex = sourceIndex; // update target to be the source we swapped to
                sourceIndex = mappedIndexes[targetIndex]; // and source to be the index that we need there
                // totalChecks++; // debug
                // totalSwaps++; // debug
            }
        }

        // e.g.
        // list: [2, 4, 1, 3, 0]
        // sort: [0, 1, 2, 3, 4]
        // mapping: [4, 2, 0, 3, 1]
        // i=0
        // swap: [0, 4, 1, 3, 2] t=0, s=4, i=0
        // swap: [0, 2, 1, 3, 4] t=4, s=1, i=0
        // swap: [0, 1, 2, 3, 4] t=1, s=2, i=0
        // swap: [0, 1, 2, 3, 4] t=2, s=0, i=0
        // i=1 (all the bits are marked as visitted)
        // swap: [0, 1, 2, 3, 4] t=1, s=2, i=0

        // System.out.println("Finished bucket sort over length " + items.size() + " with " + totalBucketCalculations + " bucket calculations, " + totalSwaps + " swaps, and " + totalLoops +" loops."); // debug

        return changedOrder;
    }

    /**
     * This method is an in-place, stable sort. Sorts a list of {@code IntIdentifiable} objects in a radix fashion,
     * using the {@code Integer} value given to represent the object. This method uses {@code BucketSort} as the
     * in-place sorting method for each digit. Run-time is given by 10 * items.size(), or O(N).
     * @param items The list of items to be sorted.
     * @param <T> The {@code IntIdentifiable} type representing the objects in the list, allowing this method to take
     *           advantage of digits in integers for radix sort.
     * @return The sorted list, in ascending order.
     */
    public static <T extends IntIdentifiable> boolean radixSort(List<T> items){
        boolean updated = false; // track if updated

        // Get the max digits in the numbers, hopefully skipping iterations of bucket sort
        int maxDigits = 0;
        for (T item: items) {
            int val = item.toInt();
            int digits = val == 0 ? 1 : (int) Math.log10(Math.abs(val)) + 1;
            if (digits > maxDigits) maxDigits = digits;
        }

        // Loop over each digit, from the 1s place to the 10s place.
        for (int i = 0; i < maxDigits; i++) {
            // Bucket sort, in-place, at the given digit
            if (bucketSort(items, i)) updated = true;
        }
        return updated;
    }

}