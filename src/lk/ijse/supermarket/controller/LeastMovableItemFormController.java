package lk.ijse.supermarket.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket.dto.OrderDetailDTO;
import lk.ijse.supermarket.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeastMovableItemFormController {
    public TableView<OrderDetailDTO> tblLeastItems;
    public TableColumn colCode;
    public TableColumn colQty;


    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));

        try {
            mostMovableItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void mostMovableItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM OrderDetails ORDER BY qty ASC LIMIT 4");

        ObservableList<OrderDetailDTO> obList2 = FXCollections.observableArrayList();

        while (result.next()) {

            obList2.add(
                    new OrderDetailDTO(
                            result.getString(1),
                            result.getString(2),
                            result.getInt(3),
                            result.getBigDecimal(4),
                            result.getBigDecimal(5)

                    )
            );
        }
        tblLeastItems.setItems(obList2);


    }

}
