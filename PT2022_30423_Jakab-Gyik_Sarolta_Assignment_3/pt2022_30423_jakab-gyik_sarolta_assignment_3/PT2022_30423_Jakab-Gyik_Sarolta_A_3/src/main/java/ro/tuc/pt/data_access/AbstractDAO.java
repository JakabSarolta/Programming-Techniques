package ro.tuc.pt.data_access;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ro.tuc.pt.connection.ConnectionFactory;

/**
 *
 * Tool to access the database. It is of generic type and uses reflection technique.
 * Each model class can use it.
 *
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * The createSelectQuery method uses a StringBuilder and creates the select SQL query to be executed by
     * other methods that work with the database tables
     * @param field is the criteria of the selection
     * @return the select type SQL query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * The createFindAllQuery method uses a StringBuilder and creates the select query to be executed
     * that returns all the rows of the specified table of the database
     * @return the select * SQL statement string
     */
    private String createFindAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName() + "`");
        return sb.toString();
    }

    /**
     * The createInsertQuery method uses a StringBuilder and creates the insert query to be executed by another method
     * that adds a new client/product/order into the corresponding table of the database
     * @param t is the generic parameter that can be a client, product or order to be inserted
     * @return an INSERT SQL statement string
     * @throws IllegalAccessException when trying to access certain fields declared private
     */
    private String createInsertQuery(T t) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `");
        sb.append(type.getSimpleName() + "` (");
        Field[] fields1 = type.getDeclaredFields();
        for(Field f:fields1) {
            sb.append(f.getName() + ", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(") ");
        sb.append(" VALUES (");
        for (Field s:t.getClass().getDeclaredFields()) {
            s.setAccessible(true);
            if(s.getName().equals("name") || s.getName().equals("address") || s.getName().equals("email")){
                sb.append("'" + s.get(t) + "', ");
            } else{
                sb.append("" + s.get(t) + ", ");
            }
            s.setAccessible(false);
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }

    /**
     * The createUpdateQuery method uses a StringBuilder and creates the update query to be executed by another method
     * that updates a client/product/order in the corresponding table of the database
     * @param t is the generic parameter that can be a client, product or order to be updated
     * @return an UPDATE SQL statement string
     * @throws IllegalAccessException when trying to access certain fields declared private
     */
    private String createUpdateQuery(T t) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Field idField = null;
        sb.append("UPDATE `");
        sb.append(type.getSimpleName() + "` SET ");
        for(Field f:t.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if(f.getName().equals("id")){
                idField = f;
            }
            sb.append(f.getName() + " = '" + f.get(t) + "', ");
            f.setAccessible(false);
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        idField.setAccessible(true);
        sb.append(" WHERE " + idField.getName() + " = " + idField.get(t));
        idField.setAccessible(false);
        return sb.toString();
    }

    /**
     * The createDeleteQuery method uses a StringBuilder and creates the delete query to be executed by another method
     * that deletes a client/product/order from the corresponding table of the database
     * @param t is the generic parameter that can be a client, product or order to be deleted
     * @return a DELETE SQL statement string
     * @throws IllegalAccessException when accessing certain fields declared private
     */
    private String createDeleteQuery(T t) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Field idField = null;
        sb.append("DELETE FROM `");
        sb.append(type.getSimpleName() + "`");
        for(Field f:t.getClass().getDeclaredFields()){
            if(f.getName().equals("id")){
                idField = f;
                break;
            }
        }
        idField.setAccessible(true);
        sb.append(" WHERE " + idField.getName() + " = " + idField.get(t));
        idField.setAccessible(false);
        return sb.toString();
    }

    /**
     * The findAll method creates the connection to the database and executes the select query to get
     * a resultSet with all the rows of the specified table of the database
     * In the end, it closes the connection.
     * @return On success, the result set, otherwise, null
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * The findById method creates the connection to the database. Then, it executes the created query
     * and get in the resultSet local variable the row with the corresponding id from the specific table.
     * In the end, it closes the connection.
     * @param id the id field of the instance to find
     * @return On success, the result set, otherwise, null
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            try{
                return createObjects(resultSet).get(0);
            } catch(IndexOutOfBoundsException i){
                //JOptionPane.showMessageDialog(null, "Client not found!");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0) //ha nincsenek parameterei
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance(); //uj class obj Client client = new Client();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type); //megkapja a fieldnek a getter/setterokat
                    Method method = propertyDescriptor.getWriteMethod(); //megkapja a fieldnek a setterjet
                    method.invoke(instance, value); //meghivja a settert
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * The insert method creates the connection to the database. Then, it executes the created insert query.
     * In the end, it closes the connection.
     * @param t the generic parameter that could be a client, product or order
     * @return The instance that was inserted successfully
     * @throws IllegalAccessException when trying to access certain fields declared private
     */
    public T insert(T t) throws IllegalAccessException {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String query = createInsertQuery(t);
        try {
            insertStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            /*Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length - 1; i++){
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fields[i].getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                insertStatement.setObject(i, method.invoke(t));
            }*/
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:insert " + e.getMessage());
        } /*catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }*/ finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return t;
    }

    /**
     * The update method creates the connection to the database. Then, it executes the created update query.
     * In the end, it closes the connection.
     * @param t the generic parameter that could be a client, product or order
     * @return The instance that was updated successfully
     * @throws IllegalAccessException when trying to access certain fields declared private
     */
    public T update(T t) throws IllegalAccessException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String query = createUpdateQuery(t);
        try {
            updateStatement = dbConnection.prepareStatement(query);

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return t;
    }

    /**
     * The delete method creates the connection to the database. Then, it executes the created update query.
     * In the end, it closes the connection.
     * @param t the generic parameter that could be a client, product or order
     * @return The instance that was deleted successfully
     * @throws IllegalAccessException when trying to access certain fields declared private
     */
    public T delete(T t) throws IllegalAccessException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        String query = createDeleteQuery(t);
        try {
            deleteStatement = dbConnection.prepareStatement(query);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return t;
    }
}

