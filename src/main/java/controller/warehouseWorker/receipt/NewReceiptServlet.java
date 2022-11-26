package controller.warehouseWorker.receipt;

import dao.SparePartDAO;
import dao.SupplierDAO;
import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/warehouseWorker/receipt/new")
public class NewReceiptServlet extends HttpServlet {
    private final SupplierDAO supplierDAO = new SupplierDAO();
    private final SparePartDAO sparePartDAO = new SparePartDAO();
    public List<Supplier> getAllSuppliers(){
        List<Supplier> res = supplierDAO.getAll();
        res.sort(new Comparator<Supplier>() {
            @Override
            public int compare(Supplier o1, Supplier o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }
    public List<Supplier> getAllSuppliersByNameContains(String keyword){
        List<Supplier> res = supplierDAO.getByNameContains(keyword);
        res.sort(new Comparator<Supplier>() {
            @Override
            public int compare(Supplier o1, Supplier o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }
    public List<SparePart> getAllSpareParts(){
        List<SparePart> res = sparePartDAO.getAll();
        res.sort(new Comparator<SparePart>() {
            @Override
            public int compare(SparePart o1, SparePart o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }

    public List<SparePart> getAllSparePartsByNameContains(String keyword){
        List<SparePart> res = sparePartDAO.getByNameContains(keyword);
        res.sort(new Comparator<SparePart>() {
            @Override
            public int compare(SparePart o1, SparePart o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return res;
    }
    public void addToReceipt(SparePartInReceipt addedSparePart){
        Receipt receipt = addedSparePart.getReceipt();
        for (SparePartInReceipt s:receipt.getSpareParts()){
            if (s.getId() == addedSparePart.getId() && s.getUnitPrice() == addedSparePart.getUnitPrice()){
                s.setQuantity(s.getQuantity() + addedSparePart.getQuantity());
                return;
            }
        }
        receipt.getSpareParts().add(addedSparePart);
        receipt.setTotalPrice(receipt.getTotalPrice() + addedSparePart.getUnitPrice() * addedSparePart.getQuantity());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("receipt") == null){
            request.getSession().setAttribute(
                    "receipt",
                    new Receipt((WarehouseWorker) request.getSession().getAttribute("warehouseWorker"))
            );
        }
        String supplierKeyword = (String) request.getParameter("supplierKeyword");
        request.setAttribute("supplierKeyword", supplierKeyword);
        String sparePartKeyword = (String) request.getParameter("sparePartKeyword");
        request.setAttribute("sparePartKeyword", sparePartKeyword);
        request.setAttribute(
                "suppliers",
                 supplierKeyword == null?
                        getAllSuppliers():getAllSuppliersByNameContains(supplierKeyword)
        );
        request.setAttribute(
                "spareParts",
                sparePartKeyword == null?
                        getAllSpareParts():getAllSparePartsByNameContains(sparePartKeyword)
        );
        request.getRequestDispatcher("NewReceiptFrame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Supplier:
        String selectedSupplierId = (String) request.getParameter("selectedSupplier");
        if (selectedSupplierId != null){
            int id = Integer.parseInt(selectedSupplierId);
            ((Receipt) request.getSession().getAttribute("receipt")).setSupplier(supplierDAO.getById(id));
        }
        // Spare part:
        String selectedSparePartId = (String) request.getParameter("selectedSparePart");
        if (selectedSparePartId != null){
            int id = Integer.parseInt(selectedSparePartId);
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float unitPrice = Float.parseFloat(request.getParameter("price"));
            addToReceipt(
                    new SparePartInReceipt(
                            quantity,
                            unitPrice,
                            sparePartDAO.getById(id),
                            (Receipt) request.getSession().getAttribute("receipt")
                    )
            );
        }
        doGet(request, response);
    }
}
