package dao;

import entity.MembersInfo;
import entity.PageUtil;

public interface MembersInfoDao {
    PageUtil<MembersInfo> getMembersInfoPage(int pageIndex, int pageSize);

    MembersInfo findByMid(int mid);

    int updateMembersInfo(MembersInfo member);
}
