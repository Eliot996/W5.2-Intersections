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

        for (int i : list1) {
            if (list2.contains(i)) {
                if (!intersections.contains(i)) {
                    intersections.add(i);
                }
            }
        }

        return intersections;
    }

    public static List<Integer> intersectOptimizedMaybe(List<Integer> list1, List<Integer> list2) {
        ArrayList<Integer> intersections = new ArrayList<>();

        int currentPosition = 0;

        for (int i : list1)
        {
            System.out.println("looking at: " + i);
            while (currentPosition < list2.size() && i >= list2.get(currentPosition))
            {
                System.out.println("In list2 at position " + currentPosition + ": " + list2.get(currentPosition));
                if (i == list2.get(currentPosition))
                {
                    System.out.println("Target found");
                    if (!intersections.contains(i))
                    {
                        System.out.println("Added " + i + " to intersections");
                        intersections.add(i);
                    }
                }
                currentPosition++;
            }
        }

        return intersections;
    }

}
