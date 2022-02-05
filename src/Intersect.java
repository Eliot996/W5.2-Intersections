import java.util.ArrayList;
import java.util.List;

public class Intersect {

    public static void main(String[] args) {
        int[] intArr = {1, 4, 8, 9, 11, 15, 17, 28, 41, 59};
        int[] intArr2 = {4, 7, 11, 17, 19, 20, 23, 28, 37, 59, 81};

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();

        for (int i : intArr) {
            arrayList1.add(i);
        }

        for (int i : intArr2) {
            arrayList2.add(i);
        }

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

    public static List<Integer> intersectOptimizedMaybe(List<Integer> list1, List<Integer> list2) {
        ArrayList<Integer> intersections = new ArrayList<>();

        // set the current position to 0
        int currentPosition = 0;

        // take int from list1
        for (int i : list1)
        {
            System.out.println("looking at: " + i); // debugging

            // if the currentPosition smaller than the size of list2
            // and while the taken int is larger than or equal to the int at the currentPosition in list2
            while (currentPosition < list2.size() && i >= list2.get(currentPosition))
            {
                System.out.println("In list2 at position " + currentPosition + ": " + list2.get(currentPosition)); // debugging

                // if the two ints are equal
                if (i == list2.get(currentPosition))
                {
                    System.out.println("Target found"); // debugging

                    // if i does not equal the last int in intersections then add i to intersections
                    if (!intersections.contains(i))
                    {
                        System.out.println("Added " + i + " to intersections"); // debugging
                        intersections.add(i);
                    }
                }

                // increment the currentPosition
                currentPosition++;
            }
        }

        return intersections;
    }

}
