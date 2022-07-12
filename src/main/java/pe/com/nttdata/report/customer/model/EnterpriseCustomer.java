package pe.com.nttdata.report.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseCustomer extends Customer {

    private String businessName;
    private Long ruc;

}
