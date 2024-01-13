package tables;

import db.MySQLConnector;
import objects.Group;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupTable extends AbsTable{

    private final static String TABLE_NAME = "`group`";

    public GroupTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("id", "bigint PRIMARY KEY AUTO_INCREMENT");
        columns.put("name", "varchar(300)");
        columns.put("id_curator", "bigint");
        create();
    }

    public ArrayList<Group> selectAll(){
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Group> selectByQuery(String sqlQuery){
        ArrayList<Group> groups = new ArrayList<>();
        db = new MySQLConnector();
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        try {
            while (rs.next()) {
                groups.add(new Group(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("id_curator")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }

    public void updateGroup(int id_curator) throws SQLException{
        String sqlQuery = String.format("UPDATE `group` SET id_curator=%s", tableName, id_curator);
        ResultSet rs = db.executeRequestWithAnswer(sqlQuery);
        while (rs.next()) {
            System.out.println("Обновленная таблица групп: " +
                    rs.getString(1) + "|" +
                    rs.getString(2) + "|" +
                    rs.getString(3) + "|" +
                    rs.getString(4));
        }
    }

    public void insertGroup(Group group){
        db = new MySQLConnector();
        String sqlQuery = String.format("INSERT INTO %s (name, id_curator) " +
                        "VALUES ('%s', '%d')",
                tableName, group.getNameGroup(), group.getId_curator());
        db.executeRequest(sqlQuery);
    }
}
