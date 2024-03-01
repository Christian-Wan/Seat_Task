import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("Data/Seating");
        Scanner s = new Scanner(f);
        ArrayList<ArrayList<String>> originalSeats = new ArrayList<ArrayList<String>>();
        originalSeats.add(new ArrayList<String>());
        int row = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split(": ");
            if (line.equals("—")) {
                originalSeats.add(new ArrayList<String>());
                row++;
            }
            else {
                originalSeats.get(row).add(parts[1]);
            }
        }

//        System.out.println(originalSeats);

        ArrayList<Student> students = new ArrayList<Student>();
        for (int r = 0; r < originalSeats.size(); r++) {
            for (int c = 0; c < originalSeats.get(r).size(); c++) {
                if (!(originalSeats.get(r).get(c).equals("N/A"))) {
                    String[] neighbors = new String[2];
                    try {
                        if (!(originalSeats.get(r).get(c - 1).equals("N/A"))) {
                            neighbors[0] = originalSeats.get(r).get(c - 1);
                        } else {
                            neighbors[0] = "";
                        }
                    } catch (IndexOutOfBoundsException e) {
                        neighbors[0] = "";
                    }
                    try {
                        if (!(originalSeats.get(r).get(c + 1).equals("N/A"))) {
                            neighbors[1] = originalSeats.get(r).get(c + 1);
                        } else {
                            neighbors[1] = "";
                        }
                    } catch (IndexOutOfBoundsException e) {
                        neighbors[1] = "";
                    }
                    students.add(new Student(originalSeats.get(r).get(c), neighbors, r, c));
                }
            }
        }
//        System.out.println(students);


        ArrayList<ArrayList<Student>> newSeats;
        do {
            ArrayList<Student> studentsClone = new ArrayList<>(students);
            newSeats = new ArrayList<ArrayList<Student>>();
            newSeats.add(new ArrayList<Student>());
            for (int r = 0; r < originalSeats.size(); r++) {
                for (int c = 0; c < originalSeats.get(r).size(); c++) {
                    if (!(r == 0 && (c == 4 || c == 5))) {
                        try {
                            int random = (int) (Math.random() * studentsClone.size());
                            newSeats.get(r).add(studentsClone.get(random));
                            studentsClone.remove(random);
                        } catch (IndexOutOfBoundsException e) {
                            String[] neighbor = {"", ""};
                            newSeats.get(r).add(new Student("Empty", neighbor, -1, -1));
                        }
                    }
                    else {
                        String[] neighbor = {"", ""};
                        newSeats.get(r).add(new Student("Empty", neighbor, -1, -1));
                    }
                }
                newSeats.add(new ArrayList<Student>());
            }
        } while(Student.check(newSeats));

        int counter = 0;
        for (ArrayList<Student> thing: newSeats) {
            for (Student yes: thing) {
                counter++;
                System.out.println(counter + ") " + yes.getName());
            }
            System.out.println();
        }

        for (ArrayList<Student> thing: newSeats) {
            String result = "";
            for (Student yes: thing) {
                result += yes.getName() + ", ";
            }
            try {
                System.out.println(result.substring(0, result.length() - 2));
            } catch (StringIndexOutOfBoundsException e) {}
        }
    }
}