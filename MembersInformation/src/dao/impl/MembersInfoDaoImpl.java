package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.MembersInfoDao;
import entity.MembersInfo;
import entity.PageUtil;

public class MembersInfoDaoImpl implements MembersInfoDao {

    /*
     * 分页查询会员信息
     */
    @Override
    public PageUtil<MembersInfo> getMembersInfoPage(int pageIndex, int pageSize) {
        String sql = "select count(*) from membersinfo";
        String sql1 = "select * from membersinfo limit ?,?";
        PageUtil<MembersInfo> pu = new PageUtil<>();
        List<MembersInfo> list = new ArrayList<>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next())
                pu.setPageNumber(rs.getInt(1));
            st = conn.prepareStatement(sql1);
            st.setInt(1, pageSize * (pageIndex - 1));
            st.setInt(2, pageSize);
            rs = st.executeQuery();
            while (rs.next()) {
                MembersInfo member = new MembersInfo();
                member.setMid(rs.getInt(1));
                member.setMname(rs.getString(2));
                member.setMgender(rs.getString(3));
                member.setMage(rs.getInt(4));
                member.setMaddress(rs.getString(5));
                member.setMemail(rs.getString(6));
                list.add(member);
            }
            pu.setList(list);
            pu.setPageIndex(pageIndex);
            pu.setPageSize(pageSize);
            pu.setPageCount(pu.getPageNumber() % pageSize == 0 ? pu
                    .getPageNumber() / pageSize : pu.getPageNumber() / pageSize
                    + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return pu;
    }

    /*
     * 根据会员编号查询会员信息
     */
    @Override
    public MembersInfo findByMid(int mid) {
        String sql = "select * from membersinfo where mid = ?";
        Connection conn = BaseDao.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, mid);
            rs = st.executeQuery();
            if (rs.next()) {
                MembersInfo member = new MembersInfo();
                member.setMid(rs.getInt(1));
                member.setMname(rs.getString(2));
                member.setMgender(rs.getString(3));
                member.setMage(rs.getInt(4));
                member.setMaddress(rs.getString(5));
                member.setMemail(rs.getString(6));
                return member;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return null;
    }

    @Override
    public int updateMembersInfo(MembersInfo member) {
        String sql = "update membersinfo set mname=?,mgender=?,mage=?,maddress=?,memail=? where mid=?";
        return BaseDao.executeUpdate(sql, member.getMname(),
                member.getMgender(), member.getMage(), member.getMaddress(),
                member.getMemail(), member.getMid());
    }
}
