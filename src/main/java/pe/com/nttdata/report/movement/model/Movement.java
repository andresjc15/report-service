package pe.com.nttdata.report.movement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.nttdata.report.account.model.Account;
import pe.com.nttdata.report.customer.model.Customer;
import pe.com.nttdata.report.customer.model.EnterpriseCustomer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {

    private Long id;
    private Long numberMovement;
    private BigDecimal amount;
    private BigDecimal beforeAmount;
    private BigDecimal afterAmount;
    private String address;
    private LocalDateTime time;
    private String description;
    private Account account;
    private Customer customer;
    private boolean canceled;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
