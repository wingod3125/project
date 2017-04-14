package service.impl;

import dao.MembersInfoDao;
import dao.impl.MembersInfoDaoImpl;
import entity.MembersInfo;
import entity.PageUtil;
import service.MembersInfoBiz;

public class MembersInfoBizImpl implements MembersInfoBiz {
    MembersInfoDao dao = new MembersInfoDaoImpl();

    @Override
    public PageUtil<MembersInfo> getMembersInfoPage(int pageIndex, int pageSize) {
        return dao.getMembersInfoPage(pageIndex, pageSize);
    }

    @Override
    public MembersInfo findByMid(int mid) {
        return dao.findByMid(mid);
    }

    @Override
    public int updateMembersInfo(MembersInfo member) {
        return dao.updateMembersInfo(member);
    }

}
