package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ������
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
    /*
     * ��̬��������
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * ��ȡ���ݿ�����
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
     * �ر����ݿ����ӣ��ͷ���Դ
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
     * ����ִ����ɾ�ĵķ���
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
            System.out.println("���ݿ����ӳɹ�");
        }
    }
}
