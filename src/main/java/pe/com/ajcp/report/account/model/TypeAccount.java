package pe.com.ajcp.report.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAccount {

    private Long id;
    private String name;
    private String sub;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
