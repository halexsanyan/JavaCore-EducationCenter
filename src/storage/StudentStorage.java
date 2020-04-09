package storage;


import model.Lesson;
import model.Student;

public class StudentStorage {
    private Student[] students;
    private int size;

    public StudentStorage(int capacity) {
        students = new Student[capacity];
    }

    public StudentStorage() {
        students = new Student[15];
    }

    public void add(Student student) {
        if (size == students.length) {
            exstand();
        }
        students[size++] = student;
    }

    private void exstand() {
        Student[] temp = new Student[students.length + 10];
        System.arraycopy(students, 0, temp, 0, students.length);
        students = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

    public Student getStudentByName(String name) {
        for (int i = 0; i < size; i++) {
            if (students[i].getName().equals(name)) {
                return students[i];
            }
        }
        return null;
    }

    public void getStudent(Student student1) {
        for (int i = 0; i < size; i++) {
            if (students[i].getName().equals(student1.getName())) {
                students[i] = student1;
            }
        }
    }

    public void getStudentByLesson(Lesson name) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < students[i].getLessons().length; j++) {
                if (students[i].getLessons()[j].equals(name)) {
                    System.out.println(students[i]);
                }
            }
        }
    }
}

