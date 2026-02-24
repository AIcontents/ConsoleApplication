package dao.impl;

import dao.PartDao;
import model.Part;
import util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartDaoImpl implements PartDao {

    private static final Logger logger = LoggerFactory.getLogger(PartDaoImpl.class);

    @Override
    public void save(Part part) {
        String sql = "INSERT INTO parts(name, quantity, price) VALUES(?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, part.getName());
            stmt.setInt(2, part.getQuantity());
            stmt.setDouble(3, part.getPrice());
            stmt.executeUpdate();

        } catch (Exception e) {
            logger.error("Ошибка сохранения запчасти", e);
        }
    }

    @Override
    public List<Part> findByName(String name) {
        List<Part> parts = new ArrayList<>();
        String sql = "SELECT * FROM parts WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                parts.add(new Part(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }

        } catch (Exception e) {
            logger.error("Ошибка поиска", e);
        }

        return parts;
    }

    @Override
    public List<Part> findAll() { return findByName(""); }

    @Override
    public void updateQuantity(int id, int quantity) {
        String sql = "UPDATE parts SET quantity=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            logger.error("Ошибка обновления количества", e);
        }
    }
}
