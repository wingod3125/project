package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MembersInfoBiz;
import service.impl.MembersInfoBizImpl;
import entity.MembersInfo;
import entity.PageUtil;

/**
 * Servlet implementation class MembersInfoSer
 */
@WebServlet("/MembersInfoSer")
public class MembersInfoSer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Ìø×ªÖ´ÐÐgetÇëÇó
        String tag = request.getParameter("tag");
        MembersInfoBiz biz = new MembersInfoBizImpl();
        if (tag != null && tag.equals("edit")) {
            int mid = Integer.parseInt(request.getParameter("mid"));
            MembersInfo member = biz.findByMid(mid);
            request.setAttribute("member", member);
            request.getRequestDispatcher("editMember.jsp").forward(request,
                    response);
            return;
        }
        int pageIndex = 1;
        if (request.getParameter("pageIndex") != null) {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        }
        int pageSize = 4;
        PageUtil<MembersInfo> pu = biz.getMembersInfoPage(pageIndex, pageSize);
        request.setAttribute("pu", pu);
        request.getRequestDispatcher("membersInfoList.jsp").forward(request,
                response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        MembersInfoBiz biz = new MembersInfoBizImpl();
        if (request.getParameter("tag") != null) {
            String tag = request.getParameter("tag");
            if (tag.equals("edit")) {
                MembersInfo member = new MembersInfo();
                member.setMid(Integer.parseInt(request.getParameter("mid")));
                member.setMname(request.getParameter("mname"));
                member.setMgender(request.getParameter("mgender"));
                member.setMage(Integer.parseInt(request.getParameter("mage")));
                member.setMaddress(request.getParameter("maddress"));
                member.setMemail(request.getParameter("memail"));
                int result = biz.updateMembersInfo(member);
                if (result > 0) {
                    response.sendRedirect("MembersInfoSer");
                } else {
                    request.setAttribute("member", member);
                    request.getRequestDispatcher("editMember.jsp").forward(
                            request, response);
                }

            }
        }
    }

}
