package lk.ijse.supermarket.bo.custom;

import lk.ijse.supermarket.bo.SuperBO;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
     ArrayList<OrderDetailDTO> getAllOrderDetail() throws SQLException, ClassNotFoundException ;



}
