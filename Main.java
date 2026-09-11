import java.util.ArrayList;
import java.util.List;

abstract class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    public String toString() {
        return name + " - " + getType();
    }
}

class TheoryCourse extends Course {
    public TheoryCourse(String name) {
        super(name);
    }

    public String getType() {
        return "Theory";
    }
}

class LabCourse extends Course {
    public LabCourse(String name) {
        super(name);
    }

    public String getType() {
        return "Lab";
    }
}

class Student {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void enroll(Course course) {
        courses.add(course);
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String toString() {
        return name;
    }
}

class Department {
    private String name;
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }
}

public class Main {
    public static void main(String[] args) {
        Department department = new Department("Computer Science");

        Course theory = new TheoryCourse("Java Fundamentals");
        Course lab = new LabCourse("Java Lab");

        department.addCourse(theory);
        department.addCourse(lab);

        Student student1 = new Student("Ahmed");
        Student student2 = new Student("Mona");

        department.addStudent(student1);
        department.addStudent(student2);

        student1.enroll(theory);
        student2.enroll(lab);

        System.out.println("All Courses:");
        for (Course course : department.getCourses()) {
            System.out.println(course);
        }

        System.out.println("\nStudent Courses:");
        for (Student student : department.getStudents()) {
            System.out.println(student.getName() + ":");
            for (Course course : student.getCourses()) {
                System.out.println("  " + course);
            }
        }
    }
}
