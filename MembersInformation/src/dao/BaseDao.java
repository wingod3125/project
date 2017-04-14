package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库相关类
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
    /*
     * 静态加载驱动
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        String userName = "root";
        String password = "123123";
        String url = "jdbc:mysql://localhost:3306/1219?characterEncoding=utf-8";
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /*
     * 关闭数据库连接，释放资源
     */
    public static void closeAll(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 用于执行增删改的方法
     */
    public static int executeUpdate(String sql, Object... obj) {
        Connection conn = getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            if (obj.length > 0) {
                for (int i = 0; i < obj.length; i++) {
                    st.setObject(i + 1, obj[i]);
                }
            }
            return st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, st, null);
        }
        return 0;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("数据库连接成功");
        }
    }
}
