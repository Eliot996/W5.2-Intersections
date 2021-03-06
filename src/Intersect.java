import java.util.ArrayList;
import java.util.List;

public class Intersect {

    public static void main(String[] args) {
        // initial data
        int[] intArr = {1, 4, 8, 9, 11, 15, 17, 28, 41, 59};
        int[] intArr2 = {4, 7, 11, 17, 19, 20, 23, 28, 37, 59, 81};

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();

        // copy arrays into the arraylists
        for (int i : intArr) {
            arrayList1.add(i);
        }
        for (int i : intArr2) {
            arrayList2.add(i);
        }

        // run and print the results
        System.out.println(intersect(arrayList1, arrayList2));
        System.out.println(intersectOptimizedMaybe(arrayList1, arrayList2));
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
