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

    public void createTableStudent() {
        String sqlQuery = String.format("CREATE TABLE student " +
                "(id int primary key, fio varchar(300), sex varchar(3), id_group int)");
        db.executeRequest(sqlQuery);
    }
    public void insert(Student student){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (fio, sex, id_group) " +
                        "VALUES ('%s', '%s', '%d')",
                tableName, student.getFio(), student.getSex(), student.getId_group());
        db.executeRequest(sqlQuery);
    }

    public void select(String[] columns, String[] where) {
        String columnStr = "*";
        if(columns.length > 0 ) {
            columnStr = String.join(",", columns);
        }

        String sqlQuery = String.format("SELECT %s FROM student ", columnStr);
        db.executeRequest(sqlQuery);
    }

    public void update(int id_curator) {
        String sqlQuery = String.format("UPDATE %s SET curator=%s", tableName, id_curator);
        db.executeRequest(sqlQuery);
    }

    public void delete() {
        String sqlQuery = String.format("TRUNCATE TABLE student", tableName);
        db.executeRequest(sqlQuery);
    }
}
