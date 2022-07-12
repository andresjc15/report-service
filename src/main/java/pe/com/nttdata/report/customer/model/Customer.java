package pe.com.nttdata.report.customer.model;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {

    private Long id;
    private String address;
    private Integer dni;
    private Integer phone;
    private String email;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
