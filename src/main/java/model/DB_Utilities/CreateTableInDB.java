package model.DB_Utilities;

import model.tools.ReadFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CreateTableInDB {
        /**
         * Create a new table in the test database
         *
         */
        public static void CreateNewTable(String dbName, String sqlQuery) {
            // SQLite connection string
            String url = "JDBC:sqlite:outputs/" + dbName + ".db";

            ReadFile sqlQueryFile = new ReadFile(sqlQuery);

            // SQL statement for creating a new table
            String sql = sqlQueryFile.export2String();

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                // create a new table
                stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    public static void CreateNewTablesFromList(String dbName, List<String> sqlQueryLocations) {
        // SQLite connection string

        for(String curSqlQueryLocation:sqlQueryLocations){
            CreateNewTable(dbName, curSqlQueryLocation);
        }
    }
//        /**
//         * @param args the command line arguments
//         */
//        public static void main(String[] args) {
//            CreateNewTable();
//        }
}
