package dao;

import dao.type.Readable;
import model.WarehouseWorker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WarehouseWorkerDAO extends BaseDAO implements Readable<WarehouseWorker> {
    @Override
    public List<WarehouseWorker> getAll() {
        return null;
    }

    @Override
    public List<WarehouseWorker> getByNameContains(String keyword) {
        return null;
    }

    @Override
    public WarehouseWorker getById(int id) {
        WarehouseWorker res = null;
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             "SELECT * FROM tbl_member as tb1 " +
                                     "INNER JOIN tbl_employee as tb2 ON tb1.id = tb2.id " +
                                     "INNER JOIN tbl_warehouse_worker as tb3 ON tb1.id = tb3.id;"
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                res = new WarehouseWorker(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("position")
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
