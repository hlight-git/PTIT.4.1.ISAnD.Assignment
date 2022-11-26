package dao;

import dao.statement.Query;
import dao.type.Readable;
import model.SparePart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SparePartDAO extends BaseDAO implements Readable<SparePart> {
    @Override
    public List<SparePart> getAll() {
        List<SparePart> res = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getAll("tbl_sellable_object", "tbl_spare_part")
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(
                        new SparePart(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getFloat("unit_price"),
                                rs.getString("calculation_unit"),
                                rs.getString("description"),
                                rs.getInt("quantity_in_stock")
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
    public List<SparePart> getByNameContains(String keyword) {
        List<SparePart> res = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getByNameContains(
                                     "tbl_sellable_object",
                                     "tbl_spare_part",
                                     keyword
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                res.add(
                        new SparePart(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getFloat("unit_price"),
                                rs.getString("calculation_unit"),
                                rs.getString("description"),
                                rs.getInt("quantity_in_stock")
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
    public SparePart getById(int id) {
        SparePart res = null;
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(
                             Query.getById(
                                     "tbl_sellable_object",
                                     "tbl_spare_part",
                                     id
                             )
                     )
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                res = new SparePart(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("unit_price"),
                        rs.getString("calculation_unit"),
                        rs.getString("description"),
                        rs.getInt("quantity_in_stock")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int updateQuantityInStock(int id, int quantity) {
        try(PreparedStatement preparedStatement =
                getConnection().prepareStatement(
                        "UPDATE tbl_spare_part SET `quantity_in_stock` = ? WHERE id = ?;"
                )
        ){
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
