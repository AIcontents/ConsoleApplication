package dao;

import model.Part;
import java.util.List;

public interface PartDao {
    void save(Part part);
    List<Part> findAll();
    List<Part> findByName(String name);
    void updateQuantity(int id, int quantity);
}
