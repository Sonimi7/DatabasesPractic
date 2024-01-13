package tables;

import db.MySQLConnector;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentTable extends AbsTable{

    private final static String TABLE_NAME = "student";

    public StudentTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("fio", "varchar(300)");
        columns.put("sex", "varchar(15)");
        columns.put("id_group", "bigint");
        create();
    }

    public ArrayList<Student> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Student> selectByQuery(String sqlQuery){
        ArrayList<Student> students = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("id"),
                        rs.getString("fio"),
                        rs.getString("sex"),
                        rs.getInt("id_group")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return students;
    }

    public void informationAllStudentsGroupCurator() throws SQLException {
        String sqlQuery = String.format("SELECT * FROM student JOIN `group` ON student.id_group=`group`.id " +
                "JOIN curator ON `group`.id=curator.id");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Все студенты, группы, кураторы: |" +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4) + "|" +
                    rs.getString(5) + "|" +
                    rs.getString(6));
        }
    }

    public void selectCountStudent() throws SQLException {
        String sqlQuery = String.format("SELECT COUNT(*) FROM student");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Количество студентов " + rs.getString(1));
        }
    }

    public void selectAllWomen() throws SQLException {
        String sqlQuery = String.format("SELECT * FROM student WHERE sex = 'жен'");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Студентки: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }

    public void selectGroupAndCurator() throws SQLException{
        String sqlQuery = String.format("SELECT * FROM curator JOIN `group` on curator.id=`group`.id_curator");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Список групп и кураторов: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }

    public void selectStudentCondition() throws SQLException{
        String sqlQuery = String.format("SELECT * FROM student JOIN `group` " +
                "ON student.id_group=`group`.id WHERE name='тестировщики'");
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Студенты из группы тестировщики: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }

    public void insert(Student student){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (fio, sex, id_group) " +
                        "VALUES ('%s', '%s', '%d')",
                tableName, student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlQuery);
    }

}
