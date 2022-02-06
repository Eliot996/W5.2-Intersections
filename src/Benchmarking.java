import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Benchmarking {
    private static final Random random = new Random();
    private static final ArrayList<Long> timesOptimized = new ArrayList<>();
    private static final ArrayList<Long> timesIntersect = new ArrayList<>();
    private static boolean sameResult = true;

    public static void main(String[] args) {
        System.out.print("Working");
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

        System.out.println("Did the two algorithms get the same answer everytime? " + sameResult);
    }

    private static void runBenchmark() {
        ArrayList<Integer> arrayList1 = randomizeData();
        ArrayList<Integer> arrayList2 = randomizeData();

        // runs the test for intersect()
        long startTime = System.nanoTime();
        List<Integer> listForIntersect = Intersect.intersect(arrayList1, arrayList2);
        long endTime = System.nanoTime();

        // saves time to complete
        timesIntersect.add((endTime - startTime)/1000);

        // runs the test for intersectOptimizedMaybe()
        startTime = System.nanoTime();
        List<Integer> listForOptimized = Intersect.intersectOptimizedMaybe(arrayList1, arrayList2);
        endTime = System.nanoTime();

        // saves time to complete
        timesOptimized.add((endTime - startTime)/1000);

        // check that they have the same answer
        sameResult = sameResult && listForIntersect.equals(listForOptimized);
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
}
