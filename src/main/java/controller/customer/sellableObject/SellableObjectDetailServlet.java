package controller.customer.sellableObject;

import dao.ServiceDAO;
import dao.SparePartDAO;
import model.SellableObject;
import model.SparePart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/customer/sellableObject/detail")
public class SellableObjectDetailServlet extends HttpServlet {
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final SparePartDAO sparePartDAO = new SparePartDAO();

    public SellableObject getSellableObjectById(int id){
        SellableObject res = serviceDAO.getById(id);
        if (res == null){
            res = sparePartDAO.getById(id);
        }
        return res;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("sellableObject", getSellableObjectById(id));
        request.getRequestDispatcher("SellableObjectDetailFrame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
