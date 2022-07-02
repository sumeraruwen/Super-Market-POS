package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.supermarket.entity.Customer;
import lk.ijse.supermarket.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
       // return null;

        //new
        ResultSet rst= SQLUtil.executeQuery("SELECT * FROM OrderDetails");
        ArrayList<OrderDetails> allOrders = new ArrayList<>();

        while (rst.next()){
            allOrders.add(new OrderDetails(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getBigDecimal(4),rst.getBigDecimal(5)));
        }
        return allOrders;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeUpdate("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty,total) VALUES (?,?,?,?,?)",entity.getOid(),entity.getItemCode(),entity.getUnitPrice(),entity.getQty(),entity.getTotal());
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
