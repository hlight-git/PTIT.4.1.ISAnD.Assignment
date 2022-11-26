package dao;

import dao.statement.Query;
import dao.type.Readable;
import model.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO extends BaseDAO implements Readable<Service> {
    public List<Service> getAll() {
        List<Service> res = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getAll("tbl_sellable_object", "tbl_service")
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(
                        new Service(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getFloat("unit_price"),
                                rs.getString("calculation_unit"),
                                rs.getString("description")
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

    public List<Service> getByNameContains(String keyword) {
        List<Service> res = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getByNameContains(
                                     "tbl_sellable_object",
                                     "tbl_service",
                                     keyword
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(
                        new Service(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getFloat("unit_price"),
                                rs.getString("calculation_unit"),
                                rs.getString("description")
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
    public Service getById(int id) {
        Service res = null;
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getById(
                                     "tbl_sellable_object",
                                     "tbl_service",
                                     id
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                res = new Service(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("unit_price"),
                        rs.getString("calculation_unit"),
                        rs.getString("description")
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
