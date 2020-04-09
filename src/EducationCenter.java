

import model.Lesson;
import model.Student;
import storage.LessonStorage;
import storage.StudentStorage;

import java.util.Scanner;

public class EducationCenter {

    private static final int EXIT = 0;
    private static final int ADD_STUDENT = 1;
    private static final int ADD_LESSON = 2;
    private static final int PRINT_STUDENTS = 3;
    private static final int PRINT_LESSONS = 4;
    private static final int CHANGE_STUDENT_LESSON = 5;
    private static final int PRINT_STUDENTS_BY_LESSON_NAME = 6;

    public static StudentStorage studentStorage = new StudentStorage();
    public static LessonStorage lessonStorage = new LessonStorage();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            String commandsStr = scanner.nextLine();
            int comand = Integer.parseInt(commandsStr);
            switch (comand) {
                case EXIT:
                    isRun = false;
                    System.out.println("Good by");
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                case CHANGE_STUDENT_LESSON:
                    change();
                    break;
                case PRINT_STUDENTS_BY_LESSON_NAME:
                    printStudentByLessoName();
                    break;
                default:
                    System.out.println("Invalid input:");
            }
        }
    }

    private static void printStudentByLessoName() {
        lessonStorage.getLessonName();
        System.out.println("Input lesson of name:");
        String lessonName = scanner.nextLine();
        Lesson lesson1 = lessonStorage.getByName(lessonName);
        studentStorage.getStudentByLesson(lesson1);
    }

    private static void change() {
        studentStorage.print();
        System.out.println("Input name of student:");

        String studentName = scanner.nextLine();
        lessonStorage.getLessonName();
        System.out.println("Input lesson name");
        String lessonNameStr = scanner.nextLine();
        String[] lessonName = lessonNameStr.split(",");
        Lesson[] lessons = new Lesson[lessonName.length];
        int index = 0;
        for (String s : lessonName) {
            lessons[index++] = lessonStorage.getByName(s);
        }
        Student studentCange = studentStorage.getStudentByName(studentName);
        studentCange.setLessons(lessons);
        System.out.println(studentCange);
    }

    private static void addLesson() {
        System.out.println("input lesson details (name, duratiօn, price, lecturerName)");
        String lessonDataStr = scanner.nextLine();
        String[] lessonData = lessonDataStr.split(",");

        Lesson lesson = new Lesson();
        lesson.setName(lessonData[0]);
        lesson.setDuration(Integer.parseInt(lessonData[1]));
        lesson.setPrice(Double.parseDouble(lessonData[2]));
        lesson.setLecturerName(lessonData[3]);
        lessonStorage.add(lesson);
        System.out.println("Lesson added");
    }

    private static void addStudent() {
        System.out.println("Select lesson names:");
        lessonStorage.getLessonName();

        System.out.println("Input lessons name");
        String addLessonsStr = scanner.nextLine();
        String[] addLessons = addLessonsStr.split(",");

        Lesson[] res = new Lesson[addLessons.length];
        int index = 0;
        for (String addLesson : addLessons) {
            res[index++] = lessonStorage.getByName(addLesson);
        }

        System.out.println("Input students details (name,surname, phon, email)");
        String studentDataStr = scanner.nextLine();
        String[] studentData = studentDataStr.split(",");

        Student student = new Student();
        student.setName(studentData[0]);
        student.setSurname(studentData[1]);
        student.setPhone(studentData[2]);
        student.setEmail(studentData[3]);
        student.setLessons(res);
        studentStorage.add(student);
        System.out.println("Student added");
    }

    private static void printCommands() {
        System.out.println("Please input " + EXIT + " to exit");
        System.out.println("Please input " + ADD_STUDENT + " to add student");
        System.out.println("Please input " + ADD_LESSON + " to add lesson");
        System.out.println("Please input " + PRINT_STUDENTS + " to print students");
        System.out.println("Please input " + PRINT_LESSONS + " to print lessons");
        System.out.println("Please input " + CHANGE_STUDENT_LESSON + " to change student and lessons");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON_NAME + " to print students by lesson name");
    }
}
