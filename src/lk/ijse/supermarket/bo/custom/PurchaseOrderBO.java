package lk.ijse.supermarket.bo.custom;

import lk.ijse.supermarket.bo.SuperBO;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBO extends SuperBO {

     boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

     CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException ;

     ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException ;

     boolean checkItemAvailable(String code) throws SQLException, ClassNotFoundException ;

     boolean checkCustomerAvailable(String id) throws SQLException, ClassNotFoundException ;

     String generateNewOrderId() throws SQLException, ClassNotFoundException ;

     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;

     ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;


}
