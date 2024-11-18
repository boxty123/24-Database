package com.example.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication()
public class DatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://192.168.111.131:4567/madang",
                    "leejeonghyeon", "password");
            Statement stmt = con.createStatement();


           String insertQuery = "INSERT INTO Book(bookid, bookname, publisher, price) " +
                    "VALUES (18,'이정현 공부법', '정현출판사', 13000)";
            stmt.executeUpdate(insertQuery);



            ResultSet rs = stmt.executeQuery("SELECT * FROM Book");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) +
                        "  " + rs.getString(3)+" "+rs.getInt(4));


            String deleteQuery = "DELETE FROM Book WHERE bookid = 22";
            stmt.executeUpdate(deleteQuery);

            System.out.println("\n");

            rs = stmt.executeQuery("SELECT * FROM Book");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) +
                        "  " + rs.getString(3)+" "+rs.getInt(4));

            System.out.println("\n");
            //책 이름에 '이정현'이 들어가는 책만 검색.
            rs= stmt.executeQuery("SELECT * FROM Book WHERE bookname like '이정현%'");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) +
                        "  " + rs.getString(3)+" "+rs.getInt(4));

            con.close();





        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
