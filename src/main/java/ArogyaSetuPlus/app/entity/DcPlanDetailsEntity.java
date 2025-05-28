package ArogyaSetuPlus.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DC_PLAN_DETAILS")
@Data
public class DcPlanDetailsEntity {
    @Id
    @Column(name = "PLAN_CODE", length = 10)
    private String planCode;
    @Column(name = "PLAN_NAME", length = 50)
    private String planName;
    @Column(name = "PLAN_DESCRIPTION")
    private String planDescription;
}
