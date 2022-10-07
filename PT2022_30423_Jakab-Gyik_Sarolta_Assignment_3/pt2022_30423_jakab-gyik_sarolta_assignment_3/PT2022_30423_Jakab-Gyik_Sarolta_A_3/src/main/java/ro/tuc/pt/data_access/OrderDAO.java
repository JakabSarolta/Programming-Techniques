package ro.tuc.pt.data_access;

import ro.tuc.pt.model.Order;

import java.util.logging.Logger;
/**
 *
 * Tool to access the order table from the database. It extends AbstractDAO class and uses reflection technique.
 * It is a model of the abstract class.
 *
 */
public class OrderDAO extends AbstractDAO<Order>{
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
}
