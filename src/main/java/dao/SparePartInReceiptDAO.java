package dao;

import dao.type.Insertable;
import model.SparePartInReceipt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SparePartInReceiptDAO extends BaseDAO implements Insertable<SparePartInReceipt> {
    @Override
    public SparePartInReceipt insert(SparePartInReceipt sparePartInReceipt) {
        try(PreparedStatement preparedStatement =
                    getConnection().prepareStatement(
                            "INSERT INTO tbl_spare_part_in_receipt " +
                                    "(`quantity`, `unit_price`, `spare_part_id`, `receipt_id`) " +
                                    "VALUES " +
                                    "(?, ?, ?, ?);"
                    )
        ){
            preparedStatement.setInt(1, sparePartInReceipt.getQuantity());
            preparedStatement.setFloat(2, sparePartInReceipt.getUnitPrice());
            preparedStatement.setInt(3, sparePartInReceipt.getSparePart().getId());
            preparedStatement.setInt(4, sparePartInReceipt.getReceipt().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
