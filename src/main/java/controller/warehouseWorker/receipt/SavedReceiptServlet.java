package controller.warehouseWorker.receipt;

import dao.ReceiptDAO;
import dao.SparePartDAO;
import dao.SparePartInReceiptDAO;
import model.Receipt;
import model.SparePartInReceipt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/warehouseWorker/receipt/saved")
public class SavedReceiptServlet extends HttpServlet {
    private final ReceiptDAO receiptDAO = new ReceiptDAO();
    private final SparePartDAO sparePartDAO = new SparePartDAO();
    private final SparePartInReceiptDAO sparePartInReceiptDAO = new SparePartInReceiptDAO();
    public Receipt saveReceipt(Receipt receipt){
        receipt.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        receipt.setId(receiptDAO.insert(receipt).getId());
        for (SparePartInReceipt s:receipt.getSpareParts()){
            sparePartDAO.updateQuantityInStock(s.getId(), s.getQuantity());
            sparePartInReceiptDAO.insert(s);
        }
        return receipt;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Receipt savedReceipt = (Receipt) request.getSession().getAttribute("savedReceipt");
        if (savedReceipt != null){
            request.getSession().removeAttribute("savedReceipt");
            request.setAttribute("savedReceipt", savedReceipt);
            request.getRequestDispatcher("SavedReceiptFrame.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/warehouseWorker/receipt/new");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("cancel".equals(request.getParameter("action"))){
            request.getSession().removeAttribute("receipt");
            response.sendRedirect(request.getContextPath() + "/warehouseWorker/receipt/new");
            return;
        }
        Receipt receipt = (Receipt) request.getSession().getAttribute("receipt");
        request.getSession().setAttribute("savedReceipt", saveReceipt(receipt));
        request.getSession().removeAttribute("receipt");
        doGet(request, response);
    }
}
