package ArogyaSetuPlus.app.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanInputs {
    private Integer appId;
    private String planCode;
    private LocalDate startDate;
    private LocalDate endDate;    
}
