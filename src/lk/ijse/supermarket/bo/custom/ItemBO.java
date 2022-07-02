package lk.ijse.supermarket.bo.custom;

import lk.ijse.supermarket.bo.SuperBO;
import lk.ijse.supermarket.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {

     ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException ;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException ;

     boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;

     boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

     boolean existItem(String code) throws SQLException, ClassNotFoundException ;

     String generateNewItemCode() throws SQLException, ClassNotFoundException ;

}
