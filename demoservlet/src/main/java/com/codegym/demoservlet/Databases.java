package com.codegym.demoservlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Databases {
    static int cnt = 4;
    static String user = "root";
    static String password = "01666553995";
    static String url = "jdbc:mysql://localhost:3306/test";
    static Connection c;
    static Statement st;


    public static Connection getConnect(){

        if(c == null){
            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                c = DriverManager.getConnection(url, user, password);
                st = c.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return c;
    }
    public static String getData(){
        try {
            getConnect();
            String sql = "select * from orders";
            ResultSet resultSet = st.executeQuery(sql);
            String s = "[";
            int count = cnt;
            while(count > 0){
                resultSet.next();
                if(count == 1)
                    s += ("{\"id\": \"" + resultSet.getString("id")+"\","+ "\"name\": \"" + resultSet.getString("order_name")+"\"}");
                else s += ("{\"id\": \"" + resultSet.getString("id")+"\","+ "\"name\": \"" + resultSet.getString("order_name")+"\"},");
                count--;
            }
            s+="]";
            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeData(MyObject data){
        getConnect();
        String sql = "insert into orders(id, order_name)" +
                "value("+data.id+",\""+data.name +"\");";
        System.out.println(sql);
        try {
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static void main(String[] args) {
        writeData(new MyObject(90, "Phong Ha"));

    }


}
