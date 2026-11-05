
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class ManagementSystem {
    // static means variable belongs to class not object
    // ArrayList
    private static final List<Student> students = new ArrayList<>();

    public static void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName());
    }

    public static void displayStudents() {
        students.forEach(student -> System.out.println(student)); // or forEach(System.out::println)
    }

    // Optional safe approach to avoid null pointer exception, returns empty if not found
    public static Optional<Student> getStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public static void updateStudent(String id, String newName, double newGrade) {
        Optional<Student> optionalStudent = getStudentById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(newName);
            student.setGrade(newGrade);
            System.out.println("Student updated: " + student);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public static void deleteStudent(String id) {
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (removed) {
            System.out.println("Student with ID " + id + " deleted.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {

        addStudent(new Student("101", "Alice Smith", 85.5));

        addStudent(new Student("102", "Bob Jones", 92.0));
        displayStudents();
        System.out.println("\nExecuting Update...");
        updateStudent("101", "Alice Smith", 89.5);
        displayStudents();
        System.out.println("\nExecuting Delete...");

        deleteStudent("102");

        displayStudents();

    }

}

