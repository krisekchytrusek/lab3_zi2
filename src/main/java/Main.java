import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.Random;
public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/zi2?useUnicode=true&serverTimezone=UTC", "root", "");
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("select * from lab2");
            while(results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int salary = results.getInt("salary");
                System.out.println(id + " | " + name + " | " + salary);
            }
            int firstInsert = statement.executeUpdate("INSERT INTO lab2 (id, name, salary) VALUES (NULL, 'majda', 35525)");
            String[] names = new String[] {"EmmettHolli","Roxy", "Nery", "Marjory", "Adrienne", "Iraida","Lashell","Cinderella","Waneta"};
            Random randomInt = new Random();
            PreparedStatement template = conn.prepareStatement("INSERT INTO lab2 (name, salary) VALUES (?, ?)");
            for (int i=0; i<names.length; i++){
                int r=randomInt.nextInt(35000);
                template.setString(1,names[i]);
                template.setInt(2, r);
                template.executeUpdate();

            }
            conn.close();
        }
        catch (SQLException dbexc) {
                    String message = dbexc.getMessage();
                    System.out.println(message);
            }
        }
    }