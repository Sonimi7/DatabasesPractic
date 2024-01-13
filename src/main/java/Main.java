import db.MySQLConnector;
import objects.Curator;
import objects.Group;
import objects.Student;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            StudentTable studentTable = new StudentTable();
            ArrayList<Student> students = studentTable.selectAll();
            if (students.isEmpty()) {
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

            CuratorTable curatorTable = new CuratorTable();
            ArrayList<Curator> curators = curatorTable.selectAll();
            if (curators.isEmpty()) {
                curatorTable.insertCurator(new Curator("Дягилев Иван Иванович"));
                curatorTable.insertCurator(new Curator("Меньшиков Валентина Петровна"));
                curatorTable.insertCurator(new Curator("Островский Геннадий Иванович"));
                curatorTable.insertCurator(new Curator("Петровский Геннадий Иванович"));
                curators = curatorTable.selectAll();
            }

            GroupTable groupTable = new GroupTable();
            ArrayList<Group> groups = groupTable.selectAll();
            if(groups.isEmpty()) {
                groupTable.insertGroup(new Group("разработчики", 1));
                groupTable.insertGroup(new Group("тестировщики", 2));
                groupTable.insertGroup(new Group("аналитики", 3));
                groups = groupTable.selectAll();
            }

            studentTable.informationAllStudentsGroupCurator();
            studentTable.selectCountStudent();
            studentTable.selectAllWomen();
            studentTable.selectGroupAndCurator();
            studentTable.selectStudentCondition();
            groupTable.updateGroup(50);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MySQLConnector.close();
        }
    }
}
