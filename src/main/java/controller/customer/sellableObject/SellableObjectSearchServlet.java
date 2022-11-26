package controller.customer.sellableObject;

import dao.ServiceDAO;
import dao.SparePartDAO;
import model.SellableObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/customer/sellableObject/search")
public class SellableObjectSearchServlet extends HttpServlet {
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final SparePartDAO sparePartDAO = new SparePartDAO();
    public List<SellableObject> getAllSellableObjects(){
        List<SellableObject> res = new ArrayList<>();
        res.addAll(serviceDAO.getAll());
        res.addAll(sparePartDAO.getAll());
        res.sort(new Comparator<SellableObject>() {
            @Override
            public int compare(SellableObject o1, SellableObject o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }

    public List<SellableObject> getAllSellableObjectsByNameContains(String keyword){
        List<SellableObject> res = new ArrayList<>();
        res.addAll(serviceDAO.getByNameContains(keyword));
        res.addAll(sparePartDAO.getByNameContains(keyword));
        res.sort(new Comparator<SellableObject>() {
            @Override
            public int compare(SellableObject o1, SellableObject o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        request.setAttribute(
                "sellableObjects",
                keyword == null?
                        getAllSellableObjects():
                        getAllSellableObjectsByNameContains(keyword)
        );
        request.getRequestDispatcher("SellableObjectSearchFrame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
