package ArogyaSetuPlus.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ArogyaSetuPlus.app.bindings.ChildInputs;
import ArogyaSetuPlus.app.bindings.CitizenRegistrationInputs;
import ArogyaSetuPlus.app.bindings.DcSummaryReport;
import ArogyaSetuPlus.app.bindings.EducationInputs;
import ArogyaSetuPlus.app.bindings.IncomeInputs;
import ArogyaSetuPlus.app.bindings.PlanDetailsOutputs;
import ArogyaSetuPlus.app.bindings.PlanInputs;
import ArogyaSetuPlus.app.entity.CitizenRegistrationEntity;
import ArogyaSetuPlus.app.entity.DcChildrenEntity;
import ArogyaSetuPlus.app.entity.DcEducationEntity;
import ArogyaSetuPlus.app.entity.DcIncomeEntity;
import ArogyaSetuPlus.app.entity.DcPlanDetailsEntity;
import ArogyaSetuPlus.app.entity.DcSelectedPlanDetailsEntity;
import ArogyaSetuPlus.app.repository.ICitizenRegistrationRepository;
import ArogyaSetuPlus.app.repository.IDcChildrenRepository;
import ArogyaSetuPlus.app.repository.IDcEducationRepository;
import ArogyaSetuPlus.app.repository.IDcIncomeRepository;
import ArogyaSetuPlus.app.repository.IDcPlanRepository;
import ArogyaSetuPlus.app.repository.IPlanDetailsRepository;

@Service
public class DcMgmtService implements IDcMgmtService {

    @Autowired
    private ICitizenRegistrationRepository regRepo;
    @Autowired
    private IDcIncomeRepository incomeRepo;
    @Autowired
    private IDcPlanRepository planRepo;
    @Autowired
    private IDcEducationRepository educationRepo;
    @Autowired
    private IDcChildrenRepository childRepo;
    @Autowired
    private IPlanDetailsRepository planDetailsRepo;

    @Override
    public List<PlanDetailsOutputs> showAllPlans() {
	List<DcPlanDetailsEntity> planDetailsEntity = planDetailsRepo.findAll();
	List<PlanDetailsOutputs> planOutputs = new ArrayList<>();
	planDetailsEntity.forEach(planDet -> {
	    PlanDetailsOutputs output = new PlanDetailsOutputs();
	    output.setPlanCode(planDet.getPlanCode());
	    output.setPlanName(planDet.getPlanName());
	    output.setPlanDescription(planDet.getPlanDescription());
	    planOutputs.add(output);
	});
	return planOutputs;
    }

    @Override
    public PlanDetailsOutputs showPlanDetails(String planCode) {
	if (planCode == null || planCode.trim().isEmpty()) {
	    throw new IllegalArgumentException("Invalid Plan Code: Please enter a valid code.");
	}
	DcPlanDetailsEntity PlanDetails = planDetailsRepo.findByPlanCode(planCode);
	if (PlanDetails == null) {
	    throw new IllegalArgumentException("Plan Code not found: " + planCode);
	}
	PlanDetailsOutputs output = new PlanDetailsOutputs();
	BeanUtils.copyProperties(PlanDetails, output);
	return output;
    }

    @Override
    public String savePlanDetails(PlanInputs plan) {
	if (plan == null) {
	    return "Invalid Plan Details: Plan data cannot be null.";
	}
	DcSelectedPlanDetailsEntity planEntity = new DcSelectedPlanDetailsEntity();
	BeanUtils.copyProperties(plan, planEntity);
	DcPlanDetailsEntity PlanDetails = planDetailsRepo.findByPlanCode(plan.getPlanCode());
	if (PlanDetails == null) {
	    throw new IllegalArgumentException("Invalid Plan Code: Plan does not exist.");
	}
	planEntity.setPlanName(PlanDetails.getPlanName());
	planEntity.setPlanDescription(PlanDetails.getPlanDescription());
	planRepo.save(planEntity);
	return "Plan Details Saved Successfully for " + planEntity.getAppId() + ".";
    }

    @Override
    public PlanInputs getSavedPlanDetails(Integer appId) {
	DcSelectedPlanDetailsEntity planEntity = planRepo.findByAppId(appId);
	PlanInputs inputs = new PlanInputs();
	BeanUtils.copyProperties(planEntity, inputs);
	return inputs;
    }

    @Override
    public String updatePlan(PlanInputs plan) {
	if (!plan.equals(null)) {
	    System.out.println("DcMgmtService.updatePlan()");
	    DcSelectedPlanDetailsEntity planEntity = planRepo.findByAppId(plan.getAppId());
	    DcPlanDetailsEntity PlanDetails = planDetailsRepo.findByPlanCode(plan.getPlanCode());
	    planEntity.setPlanName(PlanDetails.getPlanName());
	    planEntity.setPlanDescription(PlanDetails.getPlanDescription());
	    BeanUtils.copyProperties(plan, planEntity);
	    planRepo.save(planEntity);
	    return "Updatation of Plan is Succesful " + plan.getAppId() + ".";
	}
	return "Something Bad Happend at our end. Please Try Again After Sometime.";
    }

    @Override
    public String saveIncomeDetails(IncomeInputs income) {
	if (!income.equals(null)) {
	    DcIncomeEntity incomeEntity = new DcIncomeEntity();
	    BeanUtils.copyProperties(income, incomeEntity);
	    incomeRepo.save(incomeEntity);
	    return "Income Details Saved Succesfully for " + income.getAppId() + ".";
	} else {
	    return "Something Bad Happend at our end. Please Try Again After Sometime.";
	}
    }

    @Override
    public IncomeInputs fetchIncomeDetails(Integer appId) {
	if (appId == null) {
	    throw new IllegalArgumentException("Invalid App ID: Please provide a valid Application ID.");
	}
	DcIncomeEntity incomeEntity = incomeRepo.findByAppId(appId);
	IncomeInputs inputs = new IncomeInputs();
	BeanUtils.copyProperties(incomeEntity, inputs);
	return inputs;
    }

    @Override
    public String updateIncomeDetails(IncomeInputs inputs) {
	if (!inputs.equals(null)) {
	    DcIncomeEntity incomeEntity = incomeRepo.findByAppId(inputs.getAppId());
	    BeanUtils.copyProperties(inputs, incomeEntity);
	    incomeRepo.save(incomeEntity);
	    return "Income Details Updated Succesfully for " + inputs.getAppId() + ".";
	} else {
	    return "Something Bad Happend at our end. Please Try Again After Sometime.";
	}
    }

    @Override
    public String saveEducationDetails(EducationInputs education) {
	if (!education.equals(null)) {
	    DcEducationEntity educationEntity = new DcEducationEntity();
	    BeanUtils.copyProperties(education, educationEntity);
	    educationRepo.save(educationEntity);
	    return "Education Details Saved Successfully " + education.getAppId() + ".";
	} else {
	    return "Something Bad Happend at our end. Please Try Again After Sometime.";
	}
    }

    @Override
    public EducationInputs fetchEducationDetails(Integer appId) {
	if (appId.equals(null)) {
	    throw new IllegalArgumentException("Please Enter Valid AppId ");
	}
	DcEducationEntity educationEntity = educationRepo.findByAppId(appId);
	EducationInputs inputs = new EducationInputs();
	BeanUtils.copyProperties(educationEntity, inputs);
	return inputs;
    }

    @Override
    public String updateEducationDetails(EducationInputs eduInputs) {
	if (!eduInputs.equals(null)) {
	    DcEducationEntity educationEntity = educationRepo.findByAppId(eduInputs.getAppId());
	    BeanUtils.copyProperties(eduInputs, educationEntity);
	    educationRepo.save(educationEntity);
	    return "Updation of Education Details Succesfully for " + eduInputs.getAppId() + ".";
	} else {
	    return "Something Bad Happend at our end. Please Try Again After Sometime.";
	}
    }

    @Override
    public String saveChildrenDetails(List<ChildInputs> children) {

	if (children == null || children.isEmpty()) {
	    return "No children details provided.";
	}
	List<DcChildrenEntity> childEntities = new ArrayList<>();
	children.forEach(child -> {
	    DcChildrenEntity childEntity = new DcChildrenEntity();
	    BeanUtils.copyProperties(child, childEntity);
	    childEntities.add(childEntity);
	});
	childRepo.saveAll(childEntities);
	return "Children Details Saved Successfully " + children.get(0).getAppId();
    }

    @Override
    public List<ChildInputs> fetchChildDetails(Integer appId) {
	List<DcChildrenEntity> childEntities = childRepo.findAllByAppId(appId);
	List<ChildInputs> childInputsList = new ArrayList<>();
	childEntities.forEach(child -> {
	    ChildInputs input = new ChildInputs();
	    BeanUtils.copyProperties(child, input);
	    childInputsList.add(input);
	});
	return childInputsList;
    }

    @Override
    public String updateChildDetails(List<ChildInputs> childInputs) {
	if (childInputs == null || childInputs.isEmpty()) {
	    return "No child details provided for update.";
	}
	if (childInputs.isEmpty()) {
	    return "No child details provided for update.";
	}
	Integer appId = childInputs.get(0).getAppId();
	List<DcChildrenEntity> existingChildren = childRepo.findAllByAppId(appId);
	if (existingChildren.isEmpty()) {
	    return "No existing child records found for the given Application ID.";
	}
	List<DcChildrenEntity> updatedChildren = new ArrayList<>();
	for (int i = 0; i < existingChildren.size(); i++) {
	    DcChildrenEntity existingChild = existingChildren.get(i);
	    ChildInputs input = childInputs.get(i);

	    BeanUtils.copyProperties(input, existingChild);
	    updatedChildren.add(existingChild);
	}

	childRepo.saveAll(updatedChildren);
	return "Child details updated successfully.";
    }

    @Override
    public DcSummaryReport showDCSummaryReport(Integer appId) {
	if (appId == null) {
	    throw new IllegalArgumentException("Invalid App ID: Please provide a valid Application ID.");
	}
	Optional<CitizenRegistrationEntity> optCitizenEntity = regRepo.findByAppId(appId);
	CitizenRegistrationEntity CitizenEntity = optCitizenEntity.get();
	CitizenRegistrationInputs citizenInputs = new CitizenRegistrationInputs();
	BeanUtils.copyProperties(CitizenEntity, citizenInputs);

	DcSelectedPlanDetailsEntity planEntity = planRepo.findByAppId(appId);

	DcIncomeEntity incomeEntity = incomeRepo.findByAppId(appId);

	DcEducationEntity educationEntity = educationRepo.findByAppId(appId);

	List<DcChildrenEntity> childsList = childRepo.findAllByAppId(appId);

	DcSummaryReport report = new DcSummaryReport();

	report.setCitizenDetails(citizenInputs);

	report.setPlanDetails(planEntity);

	report.setIncomeDetails(incomeEntity);

	report.setEduDetails(educationEntity);

	report.setChildDetails(childsList);

	return report;

    }

}
