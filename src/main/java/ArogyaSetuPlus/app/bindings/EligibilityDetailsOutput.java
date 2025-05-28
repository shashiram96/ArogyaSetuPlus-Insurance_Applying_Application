package ArogyaSetuPlus.app.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligibilityDetailsOutput {
    private Integer appId;
    private String planholderName;
    private String chiiNumber;
    private String planCode;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benifitAmount;
    private String planStatus;
    private String denialReason;

}
