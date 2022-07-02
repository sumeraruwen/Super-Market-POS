package lk.ijse.supermarket.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerDTO implements Serializable {
       private String Id;
      private String name;
       private String address;
     private String city;

     public CustomerDTO() {
     }

     public CustomerDTO(String id, String name, String address, String city) {
          Id = id;
          this.name = name;
          this.address = address;
          this.city = city;
     }

     public String getId() {
          return Id;
     }

     public void setId(String id) {
          Id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     @Override
     public String toString() {
          return "CustomerDTO{" +
                  "Id='" + Id + '\'' +
                  ", name='" + name + '\'' +
                  ", address='" + address + '\'' +
                  ", city='" + city + '\'' +
                  '}';
     }
}
