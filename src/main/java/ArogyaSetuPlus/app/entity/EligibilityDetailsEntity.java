package ArogyaSetuPlus.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ELIGIBILITY_DETERMINATION")
public class EligibilityDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer edTraceId;
    private Integer appId;
    @Column(length = 30)
    private String planHolderName;
    @Column(length = 30)
    private String chiiNumber;
    @Column(length = 30)
    private String planCode;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benifitAmount;
    @Column(length = 15)
    private String planStatus;
    @Column(length = 30)
    private String denialReason;

}
