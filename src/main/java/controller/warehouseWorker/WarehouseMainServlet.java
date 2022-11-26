package controller.warehouseWorker;

import dao.WarehouseWorkerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/warehouseWorker/")
public class WarehouseMainServlet extends HttpServlet {
    private final WarehouseWorkerDAO warehouseWorkerDAO = new WarehouseWorkerDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("warehouseWorker", warehouseWorkerDAO.getById(1));
        request.getRequestDispatcher("WarehouseMainFrame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
