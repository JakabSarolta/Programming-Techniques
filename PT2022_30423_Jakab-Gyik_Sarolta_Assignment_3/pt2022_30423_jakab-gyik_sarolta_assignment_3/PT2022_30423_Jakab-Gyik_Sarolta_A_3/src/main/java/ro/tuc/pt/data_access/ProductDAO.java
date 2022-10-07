package ro.tuc.pt.data_access;

import ro.tuc.pt.model.Product;

import java.util.logging.Logger;
/**
 *
 * Tool to access the product table from the database. It extends AbstractDAO class and uses reflection technique.
 * It is a model of the abstract class.
 *
 */
public class ProductDAO extends AbstractDAO<Product>{
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    /*private static final String insertStatementString = "INSERT INTO student (name,address,email,age)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM student where id = ?";

    public Product findById(int studentId) {
        Product toReturn = null;
        int productId = 0;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, studentId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            float price = rs.getFloat("price");
            int current_stock = rs.getInt("current_stock");
            toReturn = new Product(productId, name, price, current_stock);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public Product insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setString(2, String.valueOf(product.getPrice()));
            insertStatement.setString(3, String.valueOf(product.getCurrent_stock()));
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return product;
    }*/
}
