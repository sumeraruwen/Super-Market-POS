package lk.ijse.supermarket.bo.custom.impl;

import lk.ijse.supermarket.bo.custom.OrderDetailBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import lk.ijse.supermarket.entity.Customer;
import lk.ijse.supermarket.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    private OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    public ArrayList<OrderDetailDTO> getAllOrderDetail() throws SQLException, ClassNotFoundException {

        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        ArrayList<OrderDetailDTO> allCustomers = new ArrayList<>();
        for (OrderDetails orderDetails:all){
            allCustomers.add(new OrderDetailDTO(orderDetails.getOid(),orderDetails.getItemCode(),orderDetails.getQty(),orderDetails.getUnitPrice(),orderDetails.getTotal()));
        }
        return allCustomers;

    }


}
