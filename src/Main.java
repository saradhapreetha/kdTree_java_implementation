import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<double[]> points = new ArrayList<>();
        points.add(new double[]{0.89, 0.90, 0.78, 0.67});
        points.add(new double[]{0.69, 0.50, 0.78, 0.97});

        kdTree tree = new kdTree(4);
        for (double[] point : points) {
            tree.insert(point);
        }

        double[] searchFor = {0.89, 0.80, 0.68, 0.67};
        double[] nearest = tree.findNearest(searchFor);

        System.out.println("Nearest point to [" + arrayToString(searchFor) + "] is [" + arrayToString(nearest) + "]");
    }

    private static String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        for (double v : array) {
            sb.append(v).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}