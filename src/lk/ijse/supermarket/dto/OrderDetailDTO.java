package lk.ijse.supermarket.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetailDTO{
       private String oid;
       private String itemCode;
       private int qty;
       private BigDecimal unitPrice;
       private BigDecimal total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oid, String itemCode, int qty, BigDecimal unitPrice, BigDecimal total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
