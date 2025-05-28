package ArogyaSetuPlus.app.service;

import java.util.List;

import ArogyaSetuPlus.app.bindings.ChildInputs;
import ArogyaSetuPlus.app.bindings.DcSummaryReport;
import ArogyaSetuPlus.app.bindings.EducationInputs;
import ArogyaSetuPlus.app.bindings.IncomeInputs;
import ArogyaSetuPlus.app.bindings.PlanDetailsOutputs;
import ArogyaSetuPlus.app.bindings.PlanInputs;

public interface IDcMgmtService {
    
    public List<PlanDetailsOutputs> showAllPlans();
    
    public PlanDetailsOutputs showPlanDetails(String planCode);
    
    public String savePlanDetails(PlanInputs plan);
    
    public PlanInputs getSavedPlanDetails(Integer appId);

    public String updatePlan(PlanInputs plan);
    
    public String saveIncomeDetails(IncomeInputs income);

    public IncomeInputs fetchIncomeDetails(Integer appId);

    public String updateIncomeDetails(IncomeInputs inputs);

    public String saveEducationDetails(EducationInputs eduInputs);
    
    public EducationInputs fetchEducationDetails(Integer appId);
    
    public String updateEducationDetails(EducationInputs eduInputs);

    public String saveChildrenDetails(List<ChildInputs> children);
    
    public List<ChildInputs> fetchChildDetails(Integer appId);
    
    public String updateChildDetails(List<ChildInputs> childInputs);

    public DcSummaryReport showDCSummaryReport(Integer appId);

}
