package ArogyaSetuPlus.app.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ArogyaSetuPlus.app.bindings.EligibilityDetailsOutput;
import ArogyaSetuPlus.app.entity.CitizenRegistrationEntity;
import ArogyaSetuPlus.app.entity.CoTriggersEntity;
import ArogyaSetuPlus.app.entity.DcChildrenEntity;
import ArogyaSetuPlus.app.entity.DcEducationEntity;
import ArogyaSetuPlus.app.entity.DcIncomeEntity;
import ArogyaSetuPlus.app.entity.DcSelectedPlanDetailsEntity;
import ArogyaSetuPlus.app.entity.EligibilityDetailsEntity;
import ArogyaSetuPlus.app.repository.ICitizenRegistrationRepository;
import ArogyaSetuPlus.app.repository.ICoTriggerRepository;
import ArogyaSetuPlus.app.repository.IDcChildrenRepository;
import ArogyaSetuPlus.app.repository.IDcEducationRepository;
import ArogyaSetuPlus.app.repository.IDcIncomeRepository;
import ArogyaSetuPlus.app.repository.IDcPlanRepository;
import ArogyaSetuPlus.app.repository.IEligibilityDeterminationRepository;

@Service
public class EligiblityDetailsMgmtService implements IEligibilityDetailsMgmtService {
    @Autowired
    private IEligibilityDeterminationRepository eligibilityRepo;
    @Autowired
    private ICoTriggerRepository triggerRepo;
    @Autowired
    private ICitizenRegistrationRepository citizenRepo;
    @Autowired
    private IDcIncomeRepository incomeRepo;
    @Autowired
    private IDcPlanRepository planRepo;
    @Autowired
    private IDcEducationRepository educationRepo;
    @Autowired
    private IDcChildrenRepository childRepo;

    @Override
    public EligibilityDetailsOutput determineEligibility(Integer appId) {
	Optional<CitizenRegistrationEntity> optCitizenEntity = citizenRepo.findByAppId(appId);
	CitizenRegistrationEntity citizenEntity =  optCitizenEntity.get();
	DcSelectedPlanDetailsEntity planEntity = planRepo.findByAppId(appId);
	int citizenAge = Period.between(citizenEntity.getDob(), LocalDate.now()).getYears();
	String planCode = planEntity.getPlanCode();
	EligibilityDetailsOutput eligibleOutput = applyPlanConditions(appId, planCode, citizenAge);
	eligibleOutput.setAppId(appId);
	eligibleOutput.setPlanholderName(citizenEntity.getFullName());
	eligibleOutput.setChiiNumber(citizenEntity.getChiiNumber());
	EligibilityDetailsEntity eligibleEntity = new EligibilityDetailsEntity();
	eligibleEntity.setAppId(eligibleOutput.getAppId());
	eligibleEntity.setPlanHolderName(eligibleOutput.getPlanholderName());
	eligibleEntity.setChiiNumber(eligibleOutput.getChiiNumber());
	eligibleEntity.setPlanCode(eligibleOutput.getPlanCode());
	eligibleEntity.setPlanStartDate(eligibleOutput.getPlanStartDate());
	eligibleEntity.setPlanEndDate(eligibleOutput.getPlanEndDate());
	eligibleEntity.setBenifitAmount(eligibleOutput.getBenifitAmount());
	eligibleEntity.setPlanStatus(eligibleOutput.getPlanStatus());
	eligibleEntity.setDenialReason(eligibleOutput.getDenialReason());
	eligibilityRepo.save(eligibleEntity);

	
	//CoTrigeer Entity
	
	CoTriggersEntity coTrigger = new CoTriggersEntity();
	coTrigger.setAppId(appId);
	coTrigger.setTriggerStatus("Pending");
	triggerRepo.save(coTrigger);
	
	return eligibleOutput;
	

    }

    private EligibilityDetailsOutput applyPlanConditions(Integer appId, String planCode, int citizenAge) {
	if (appId == null) {
	    throw new RuntimeException("Invalid Application Id");
	}
	if (planCode == null) {
	    throw new RuntimeException("Invalid PlanCode");

	}
	if (citizenAge < 1) {
	    throw new RuntimeException("Invalid CitizenAge");

	}
	EligibilityDetailsOutput eligibleOutput = new EligibilityDetailsOutput();
	eligibleOutput.setPlanCode(planCode);

	//incomeDetails
	DcIncomeEntity incomeEntity = incomeRepo.findByAppId(appId);
	Double empIncome = incomeEntity.getEmpIncome();
	Double propertyIncome = incomeEntity.getPropertyIncome();

	//snap plan
	if (planCode.equalsIgnoreCase("SNAP")) {
	    if (empIncome <= 1275000.00) {
		eligibleOutput.setPlanStatus("Approved");
		eligibleOutput.setBenifitAmount(3000000.0);
	    } else {
		eligibleOutput.setPlanStatus("Denied");
		eligibleOutput.setDenialReason("High Income");
	    }
	}

	//CCAP
	if (planCode.equalsIgnoreCase("CCAP")) {
	    Boolean kidsAgeCondition = true;
	    int kidsCount = 0;
	    List<DcChildrenEntity> childEntites = childRepo.findAllByAppId(appId);
	    if (!childEntites.isEmpty()) {
		for (DcChildrenEntity child : childEntites) {
		    kidsCount++;
		    int kidAge = Period.between(child.getDateofBirth(), LocalDate.now()).getYears();
		    if (kidAge > 16) {
			kidsAgeCondition = false;
			break;
		    } //if

		} //for
	    } //if
	    if (empIncome <= 1275000 && kidsCount <= 2 && kidsAgeCondition) {
		eligibleOutput.setPlanStatus("Approved");
		eligibleOutput.setBenifitAmount(3000000.0);
	    } else {
		eligibleOutput.setPlanStatus("Denied");
		eligibleOutput.setDenialReason("CCAP Conditions are Failed");
	    }
	}

	// MEDAID
	if (planCode.equalsIgnoreCase("MEDAID")) {
	    if (empIncome <= 24200 && propertyIncome == 0) {
		eligibleOutput.setPlanStatus("Approved");
		eligibleOutput.setBenifitAmount(300000.00);
	    } else {
		eligibleOutput.setPlanStatus("Denied");
		eligibleOutput.setDenialReason("MEDAID Plan Conditions are Failed");
	    }
	} //if

	// Medcare

	if (planCode.equalsIgnoreCase("MEDCARE")) {
	    if (citizenAge >= 65) {
		eligibleOutput.setPlanStatus("Approved");
		eligibleOutput.setBenifitAmount(3000000.00);
	    } else {
		eligibleOutput.setPlanStatus("Denied");
		eligibleOutput.setDenialReason("MEDCARE Plan Conditions are Failed");
	    } //if else

	} //MEDCARE

	//CAJW Plan
	if (planCode.equalsIgnoreCase("CAJW")) {
	    DcEducationEntity Education = educationRepo.findByAppId(appId);
	    if (empIncome == 0 && propertyIncome == 0
		    && Education.getHighestQualification().equalsIgnoreCase("B.Tech")) {
		eligibleOutput.setPlanStatus("Approved");
		eligibleOutput.setBenifitAmount(3000000.00);
	    } else {
		eligibleOutput.setPlanStatus("Denied");
		eligibleOutput.setDenialReason("CAJW Plan Conditions are Failed");
	    } //if else
	}

	if (planCode.equalsIgnoreCase("QHP")) {
	    if (citizenAge > 1) {
		eligibleOutput.setPlanStatus("Approved");
		eligibleOutput.setBenifitAmount(3000000.00);
	    } else {
		eligibleOutput.setPlanStatus("Denied");
		eligibleOutput.setDenialReason("QHP Plan Conditions are Failed");
	    }
	}

	if (eligibleOutput.getPlanStatus().equalsIgnoreCase("Approved")) {
	    eligibleOutput.setPlanStartDate(LocalDate.now());
	    eligibleOutput.setPlanEndDate(LocalDate.now().plusYears(2));
	}
	return eligibleOutput;

    }
}
