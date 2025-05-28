
package ArogyaSetuPlus.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DC_PLAN")
@Data
public class DcSelectedPlanDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer planId;
    private Integer appId;
    @Column(length = 10)
    private String planCode;
    private String planName;
    private String planDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "Plan_Status", length = 15)
    private String planStatus = "Under Review";
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column(length = 30, updatable = false)
    private String createdBy;
    @Column(length = 30, insertable = false)
    private String updatedBy;
}
