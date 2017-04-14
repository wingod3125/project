package service;

import entity.MembersInfo;
import entity.PageUtil;

public interface MembersInfoBiz {
    PageUtil<MembersInfo> getMembersInfoPage(int pageIndex, int pageSize);

    MembersInfo findByMid(int mid);

    int updateMembersInfo(MembersInfo member);
}
