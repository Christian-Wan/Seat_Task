import java.util.ArrayList;
import java.util.Arrays;

public class Student {

    private String name;
    private String[] neighbors;
    private int row, column;

    public Student(String name, String[] neighbors, int row, int column) {
        this.name = name;
        this.neighbors = neighbors;
        this.row = row;
        this.column = column;
    }

    public static boolean check(ArrayList<ArrayList<Student>> seating) {
        for (int r = 0; r < seating.size(); r++) {
            for (int c = 0; c < seating.get(r).size(); c++) {
                if (seating.get(r).get(c).row == r && seating.get(r).get(c).column == c) {
                    return true;
                }
                try {
                    if (seating.get(r).get(c).getNeighbors()[0].equals(seating.get(r).get(c - 1).getName()) || seating.get(r).get(c).getNeighbors()[1].equals(seating.get(r).get(c - 1).getName())) {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {}
                try {
                    if (seating.get(r).get(c).getNeighbors()[0].equals(seating.get(r).get(c + 1).getName()) || seating.get(r).get(c).getNeighbors()[1].equals(seating.get(r).get(c + 1).getName())) {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {}
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String[] getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", neighbors=" + Arrays.toString(neighbors) +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
