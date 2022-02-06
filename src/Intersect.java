import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Intersect {

    private static final Random random = new Random();
    private static final ArrayList<Long> timesOptimized = new ArrayList<>();
    private static final ArrayList<Long> timesIntersect = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Working");
        for (int i = 0; i < 100; i++) {
            runBenchmark();
            System.out.print(".");
        }
        System.out.println("\nDone!");

        // calculate average times
        int averageForIntersect = getAverage(timesIntersect);
        int averageForOptimized = getAverage(timesOptimized);

        System.out.println("That took on average " + averageForIntersect + " microseconds for the regular intersect");
        System.out.println("That took on average " + averageForOptimized + " microseconds for the optimized intersect");


    }

    private static void runBenchmark() {
        ArrayList<Integer> arrayList1 = randomizeData();
        ArrayList<Integer> arrayList2 = randomizeData();

        // runs the test for intersect()
        long startTime = System.nanoTime();
        intersect(arrayList1, arrayList2);
        long endTime = System.nanoTime();

        // saves time to complete
        timesIntersect.add((endTime - startTime)/1000);

        // runs the test for intersectOptimizedMaybe()
        startTime = System.nanoTime();
        intersectOptimizedMaybe(arrayList1, arrayList2);
        endTime = System.nanoTime();

        // saves time to complete
        timesOptimized.add((endTime - startTime)/1000);
    }

    private static int getAverage(ArrayList<Long> timesIntersect) {
        long total = 0;

        for (long l :
                timesIntersect) {
            total += l;
        }

        return (int) total/ timesIntersect.size();
    }

    private static ArrayList<Integer> randomizeData() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int amountOfTimes = random.nextInt(4000) + 8000; // 8000 till 12000 times

        for (int i = 0; i < amountOfTimes; i++) {
            arrayList.add(random.nextInt(1000000));
        }

        arrayList.sort((Comparator.comparingInt(o -> o)));

        return arrayList;
    }

    public static List<Integer> intersect(List<Integer> list1, List<Integer> list2) {
        ArrayList<Integer> intersections = new ArrayList<>();

        // take int from list1
        for (int i : list1) {
            // check if it is present in list2
            if (list2.contains(i)) {
                // check if it is not present in intersections
                if (!intersections.contains(i)) {
                    // then add the int to intersections
                    intersections.add(i);
                }
            }
        }

        return intersections;
    }


    private static ArrayList<Integer> intersections;
    private static List<Integer> list2;
    private static int currentPosition;

    public static List<Integer> intersectOptimizedMaybe(List<Integer> list1, List<Integer> list2) {
        initWorkingData(list2);

        // loop through list1
        for (int target : list1)
        {
            loopThroughList2(target);
        }

        return resetAndReturn();
    }

    private static void initWorkingData(List<Integer> list2) {
        intersections = new ArrayList<>();
        Intersect.list2 = list2;
        // set the current position to 0
        currentPosition = 0;
    }

    private static List<Integer> resetAndReturn() {
        // save the list we want to return
        List<Integer> listToReturn = intersections;

        // reset data
        intersections = null;
        list2 = null;
        currentPosition = 0;

        return listToReturn;
    }

    private static void loopThroughList2(int target) {
        // if the currentPosition smaller than the size of list2
        // and while the taken int is larger than or equal to the int at the currentPosition in list2
        while (currentPosition < list2.size() && target >= list2.get(currentPosition))
        {
            checkForEquality(target, list2.get(currentPosition));

            // increment the currentPosition
            currentPosition++;
        }
    }

    private static void checkForEquality(int target, int observed) {
        if (target == observed)
        {
            addToIntersectionsIfNotPresent(target);
        }
    }

    private static void addToIntersectionsIfNotPresent(int target) {
        // if target does not equal the last int in intersections, then add target to intersections
        if (intersections.size() == 0)
        {
            intersections.add(target);
        }
        else if (target != intersections.get(intersections.size() - 1))
        {
            intersections.add(target);
        }
    }
}
