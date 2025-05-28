package ArogyaSetuPlus.app.bindings;

import java.util.List;

import ArogyaSetuPlus.app.entity.DcChildrenEntity;
import ArogyaSetuPlus.app.entity.DcEducationEntity;
import ArogyaSetuPlus.app.entity.DcIncomeEntity;
import ArogyaSetuPlus.app.entity.DcSelectedPlanDetailsEntity;
import lombok.Data;

@Data
public class DcSummaryReport {

    // private PlanInputs planInputs;
    private CitizenRegistrationInputs citizenDetails;
    private DcSelectedPlanDetailsEntity planDetails;
    private DcIncomeEntity incomeDetails;
    private DcEducationEntity eduDetails;
    private List<DcChildrenEntity> childDetails;
}
