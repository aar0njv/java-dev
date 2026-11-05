
// public - visible to the entire application, public access
// private - visible only within the class, private access, no outside code can change or access it (Encapsulation)
public class Student {

    // final - (Immutability) once the value is assigned, it cannot be changed, it is constant
    private final String id;
    private String name;
    private double grade;

    // Constructor - a special method which initializes the object, called in creation
    public Student(String id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    // Override - annotation for overloading toString() method, checks parent for same method, if found replace, else error
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}