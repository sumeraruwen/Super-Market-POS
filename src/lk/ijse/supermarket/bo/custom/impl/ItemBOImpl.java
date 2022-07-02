package lk.ijse.supermarket.bo.custom.impl;

import lk.ijse.supermarket.bo.custom.ItemBO;
import lk.ijse.supermarket.dao.DAOFactory;
import lk.ijse.supermarket.dao.custom.ItemDAO;
import lk.ijse.supermarket.dto.ItemDTO;
import lk.ijse.supermarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
   // private ItemDAO itemDAO = new ItemDAOImpl();
  private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Item item:all){
            allItems.add(new ItemDTO(item.getCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitPrice()));
        }
        return allItems;
    }
    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getQtyOnHand(),dto.getUnitPrice()));
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }
}
