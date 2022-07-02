package lk.ijse.supermarket.bo.custom.impl;

import lk.ijse.supermarket.bo.custom.PurchaseOrderBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.dao.custom.OrderDAO;
import lk.ijse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.supermarket.db.DBConnection;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.dto.OrderDTO;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import lk.ijse.supermarket.entity.Customer;
import lk.ijse.supermarket.entity.Item;
import lk.ijse.supermarket.entity.OrderDetails;
import lk.ijse.supermarket.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    /*CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();*/

    //Exposed the object creation logic

    CustomerDAO customerDAO =(CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO =(OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        /*Transaction*/

        Connection connection = DBConnection.getDbConnection().getConnection();

        /*if order id already exist*/
        if (orderDAO.exist(dto.getOrderId())) {

        }

        connection.setAutoCommit(false);

       // CrudDAO<OrderDTO, String> orderDAO1 = new OrderDAOImpl();
        boolean save = orderDAO.save(new Orders(dto.getOrderId(), dto.getOrderDate(),dto.getCustomerId()));

        if (!save) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        // stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty,total) VALUES (?,?,?,?,?)");

        for (OrderDetailDTO detailDTO : dto.getOrderDetails()) {

            boolean save1 = orderDetailsDAO.save(new OrderDetails(detailDTO.getOid(),detailDTO.getItemCode(),detailDTO.getQty(),detailDTO.getUnitPrice(),detailDTO.getTotal()));

            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//                //Search & Update Item
            // ItemDTO item = findItem(detail.getItemCode());

            ItemDTO item = searchItem(detailDTO.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detailDTO.getQty());

            //update item

            boolean update = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice()));

            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;

     //   return false;
    }
    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer ent = customerDAO.search(id);
        return new CustomerDTO(ent.getId(),ent.getName(),ent.getAddress(),ent.getCity());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item ent = itemDAO.search(code);
        return new ItemDTO(ent.getCode(),ent.getDescription(),ent.getQtyOnHand(),ent.getUnitPrice());
    }

    @Override
    public boolean checkItemAvailable(String code) throws SQLException, ClassNotFoundException {
       return itemDAO.exist(code);
    }

    @Override
    public boolean checkCustomerAvailable(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
       return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        for (Customer ent : all) {
            allCustomers.add(new CustomerDTO(ent.getId(),ent.getName(),ent.getAddress(),ent.getCity()));
        }
        return allCustomers;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        for (Item ent : all) {
            allItems.add(new ItemDTO(ent.getCode(),ent.getDescription(),ent.getQtyOnHand(),ent.getUnitPrice()));
        }
           return allItems;
    }

}


