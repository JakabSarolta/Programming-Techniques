package ro.tuc.pt.data_access;

import ro.tuc.pt.model.Client;

import java.util.logging.Logger;
/**
 *
 * Tool to access the client table from the database. It extends AbstractDAO class and uses reflection technique.
 * It is a model of the abstract class.
 *
 */
public class ClientDAO extends AbstractDAO<Client>{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());

    /*private static final String insertStatementString = "INSERT INTO student (name,address,email)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM student where id = ?";

    public Client findById(int clientId) {
        Client toReturn = null;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null; //sql commandot csinal
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1, clientId); //? helyebe beteszi a clientid-t
            rs = findStatement.executeQuery(); //executeUpdate() ha insert, delete, update
            rs.next(); //iterates through the lines of the result set

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            toReturn = new Client(clientId, name, address, email);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }*/

    /*public Client insert(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            //insert will return what we inserted, otherwise not
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.setString(3, client.getEmail());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return client;
    }*/
}
