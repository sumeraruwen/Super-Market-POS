package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.OrderDAO;
import lk.ijse.supermarket.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",entity.getOid(),entity.getDate(),entity.getCustomerID());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeUpdate("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",entity.getOid(),entity.getDate(),entity.getCustomerID());

    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeQuery("SELECT oid FROM `Orders` WHERE oid=?",oid).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }
}
