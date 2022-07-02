package lk.ijse.supermarket.bo.custom;

import lk.ijse.supermarket.bo.SuperBO;
import lk.ijse.supermarket.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;

     boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

     boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

     boolean existCustomers(String id) throws SQLException, ClassNotFoundException;

     boolean deleteCustomers(String id ) throws SQLException, ClassNotFoundException;

     String generateNewCustomerID() throws SQLException, ClassNotFoundException ;


}
