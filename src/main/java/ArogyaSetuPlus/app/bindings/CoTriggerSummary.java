package ArogyaSetuPlus.app.bindings;

import lombok.Data;

@Data
public class CoTriggerSummary {
    private Integer totalTriggers;
    private Integer successTriggers;
    private Integer pendingTriggers;
}
