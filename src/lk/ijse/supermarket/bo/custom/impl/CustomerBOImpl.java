package lk.ijse.supermarket.bo.custom.impl;

import lk.ijse.supermarket.bo.custom.CustomerBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.supermarket.dto.CustomerDTO;
import lk.ijse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer:all){
            allCustomers.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getCity()));
        }
        return allCustomers;

    }

    @Override
    public boolean saveCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getCity()));
    }

    @Override
    public boolean updateCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getCity()));
    }

    @Override
    public boolean existCustomers(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean deleteCustomers(String id ) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }


}
