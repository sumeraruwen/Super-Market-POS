package lk.ijse.supermarket.bo;

import lk.ijse.supermarket.bo.custom.impl.CustomerBOImpl;
import lk.ijse.supermarket.bo.custom.impl.ItemBOImpl;
import lk.ijse.supermarket.bo.custom.impl.OrderDetailBOImpl;
import lk.ijse.supermarket.bo.custom.impl.PurchaseOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory(){
        if(boFactory== null){
            boFactory=new BOFactory();
        }
    return boFactory;
    }

    public enum BOTypes{
        CUSTOMER, ITEM, PURCHASE_ORDER,ORDER_DETAIL
    }


    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();    //Super bo = new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();      //Super bo = new ItemBOImpl();
            case PURCHASE_ORDER:
                return new PurchaseOrderBOImpl();    //Super bo = new PurcahseOrderBOImpl();
            case ORDER_DETAIL:
                return new OrderDetailBOImpl();
            default:
                return null;
        }

    }




}
