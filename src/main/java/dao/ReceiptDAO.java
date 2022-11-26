package dao;

import dao.statement.Query;
import dao.type.Insertable;
import dao.type.Readable;
import model.Receipt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReceiptDAO extends BaseDAO implements Readable<Receipt>, Insertable<Receipt> {
    private final SupplierDAO supplierDAO = new SupplierDAO();
    private final WarehouseWorkerDAO warehouseWorkerDAO = new WarehouseWorkerDAO();
    @Override
    public Receipt insert(Receipt receipt) {
        try(PreparedStatement preparedStatement =
                    getConnection().prepareStatement(
                            "INSERT INTO tbl_receipt " +
                                    "(`total_price`, `created_time`, `supplier_id`, `creator_id`) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?);",
                            PreparedStatement.RETURN_GENERATED_KEYS
                    )
        ){
            preparedStatement.setFloat(1, receipt.getTotalPrice());
            preparedStatement.setTimestamp(2, receipt.getCreatedTime());
            preparedStatement.setInt(3, receipt.getSupplier().getId());
            preparedStatement.setInt(4, receipt.getCreator().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                return getById(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Receipt> getAll() {
        return null;
    }

    @Override
    public List<Receipt> getByNameContains(String keyword) {
        return null;
    }

    @Override
    public Receipt getById(int id) {
        Receipt res = null;
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getById(
                                     "tbl_receipt",
                                     id
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                res = new Receipt(
                        rs.getInt("id"),
                        supplierDAO.getById(rs.getInt("supplier_id")),
                        rs.getFloat("total_price"),
                        rs.getTimestamp("created_time"),
                        warehouseWorkerDAO.getById(rs.getInt("creator_id"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
