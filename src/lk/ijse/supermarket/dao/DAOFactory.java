package lk.ijse.supermarket.dao;

import lk.ijse.supermarket.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.supermarket.dao.custom.impl.ItemDAOImpl;
import lk.ijse.supermarket.dao.custom.impl.OrderDAOImpl;
import lk.ijse.supermarket.dao.custom.impl.OrderDetailsDAOImpl;

public class DAOFactory {
        private static DAOFactory daoFactory;

        private DAOFactory(){

        }
        public static DAOFactory getDaoFactory(){
            if(daoFactory == null){
                daoFactory = new DAOFactory();
            }
            return daoFactory;
        }

        public enum DAOTypes{
            CUSTOMER,ITEM,ORDER,ORDERDETAILS
        }

        public SuperDAO getDAO(DAOTypes types){
            switch (types){
                case CUSTOMER:
                    return new CustomerDAOImpl();  //SuperDAO superDAP =new CustomerDAOImpl()
                case ITEM:
                    return new ItemDAOImpl();  //SuperDAO superDAP =new ItemDAOImpl()
                case ORDER:
                    return new OrderDAOImpl();  //SuperDAO superDAP =new OrderDAOImpl()
                case ORDERDETAILS:
                    return new OrderDetailsDAOImpl();  //SuperDAO superDAP =new OrderDetailsDAOImpl()
                default:
                    return null;
            }

        }
}
