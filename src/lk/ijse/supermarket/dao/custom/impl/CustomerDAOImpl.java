package lk.ijse.supermarket.dao.custom.impl;

import lk.ijse.supermarket.dao.SQLUtil;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class  CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst= SQLUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();

        while (rst.next()){
            allCustomers.add(new Customer( rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return allCustomers;

    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
      return SQLUtil.executeUpdate("INSERT INTO Customer (id,name, address,city) VALUES (?,?,?,?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getCity());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("UPDATE Customer SET name=?, address=?,city=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getCity(),entity.getId());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Customer WHERE id=?",id);
        if(rst.next()){
          return new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
         return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.executeQuery("SELECT id FROM Customer WHERE id=?",id).next();

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

       return SQLUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
                String id = rst.getString("id");
                int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
                return String.format("C00-%03d", newCustomerId);
            } else {
                return "C00-001";
            }

    }
}
