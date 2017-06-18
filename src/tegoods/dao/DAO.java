package tegoods.dao;

import java.sql.SQLException;
import java.util.List;
import tegoods.model.Product;

/**
 *
 * @author igor
 */
public interface DAO {
    void insert(Product p) throws  SQLException;
    void update(Product p) throws  SQLException;
    void delete(int id) throws  SQLException;
    public List<Product> search(String regExp, boolean considerPrice,
            double priceMin, double priceMax) throws  SQLException;
    List<Product> reloadLastSearchResults() throws  SQLException;
    List<Product> getList() throws  SQLException;
    
}
