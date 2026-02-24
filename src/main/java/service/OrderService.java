package service;

import util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void createOrder(int partId, int quantity) {

        String updatePart = "UPDATE parts SET quantity = quantity - ? WHERE id = ?";
        String insertOrder = "INSERT INTO orders(part_id, quantity, status, created_at) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(updatePart);
                 PreparedStatement stmt2 = conn.prepareStatement(insertOrder)) {

                stmt1.setInt(1, quantity);
                stmt1.setInt(2, partId);
                stmt1.executeUpdate();

                stmt2.setInt(1, partId);
                stmt2.setInt(2, quantity);
                stmt2.setString(3, "CREATED");
                stmt2.setString(4, LocalDateTime.now().toString());
                stmt2.executeUpdate();

                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                logger.error("Ошибка транзакции", e);
            }

        } catch (Exception e) {
            logger.error("Ошибка соединения", e);
        }
    }
}
