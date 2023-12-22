import db.MySQLConnector;
import objects.Curator;
import objects.Group;
import objects.Student;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentTable;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            StudentTable studentTable = new StudentTable();
            studentTable.createTableStudent();
            ArrayList<Student> students = studentTable.selectAll();
            if (students.size() <= 15) {
                studentTable.insert(new Student("Иванов Иван Иванович", "муж", 1));
                studentTable.insert(new Student("Петрова Валентина Петровна", "жен", 2));
                studentTable.insert(new Student("Кречетов Геннадий Иванович", "муж", 3));
                studentTable.insert(new Student("Кремнев Геннадий Иванович", "муж", 1));
                studentTable.insert(new Student("Сидоров Иван Иванович", "муж", 1));
                studentTable.insert(new Student("Маликова Валентина Петровна", "жен", 2));
                studentTable.insert(new Student("Пугачев Геннадий Иванович", "муж", 3));
                studentTable.insert(new Student("Достоевский Геннадий Иванович", "муж", 1));
                studentTable.insert(new Student("Пушкин Иван Иванович", "муж", 1));
                studentTable.insert(new Student("Онегина Валентина Петровна", "жен", 2));
                studentTable.insert(new Student("Деревянко Геннадий Иванович", "муж", 3));
                studentTable.insert(new Student("Тряпков Геннадий Иванович", "муж", 1));
                studentTable.insert(new Student("Лопатов Иван Иванович", "муж", 1));
                studentTable.insert(new Student("Тилапиева Валентина Петровна", "жен", 2));
                studentTable.insert(new Student("Минтаев Геннадий Иванович", "муж", 3));
                students = studentTable.selectAll();
            }

            for (Student tmp : students) {
                System.out.println(tmp.toString());
            }

            System.out.println();
            students = studentTable.selectAll();
            for (Student tmp : students) {
                System.out.println(tmp.toString());
            }

            //studentTable.select(new String[]{"fio"}, new String[]{});
            //studentTable.delete();

        } finally {
            MySQLConnector.close();
        }
    }
}
