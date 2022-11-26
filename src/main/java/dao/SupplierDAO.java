package dao;

import dao.statement.Query;
import dao.type.Readable;
import model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO extends BaseDAO implements Readable<Supplier> {
    @Override
    public List<Supplier> getAll() {
        List<Supplier> res = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getAll("tbl_supplier")
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(
                        new Supplier(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("address")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<Supplier> getByNameContains(String keyword) {
        List<Supplier> res = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getByNameContains(
                                     "tbl_supplier",
                                     keyword
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(
                        new Supplier(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("phone"),
                                rs.getString("address")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public Supplier getById(int id) {
        Supplier res = null;
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getById(
                                     "tbl_supplier",
                                     id
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                res = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address")
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
