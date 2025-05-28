package ArogyaSetuPlus.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ArogyaSetuPlus.app.bindings.ChildInputs;
import ArogyaSetuPlus.app.bindings.DcSummaryReport;
import ArogyaSetuPlus.app.bindings.EducationInputs;
import ArogyaSetuPlus.app.bindings.IncomeInputs;
import ArogyaSetuPlus.app.bindings.PlanDetailsOutputs;
import ArogyaSetuPlus.app.bindings.PlanInputs;
import ArogyaSetuPlus.app.service.IDcMgmtService;

@RestController
@RequestMapping("/dataCollection")
@CrossOrigin(origins = "http://localhost:3000")
public class DataCollectionOperationController {
    @Autowired
    private IDcMgmtService dataService;

    @GetMapping("/planDetails")
    public ResponseEntity<?> displayPlanNames() {
	try {
	    List<PlanDetailsOutputs> planDetails = dataService.showAllPlans();
	    return new ResponseEntity<List<PlanDetailsOutputs>>(planDetails, HttpStatus.OK);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
    }

    @GetMapping("/fetchPlanInfo/{planCode}")
    public ResponseEntity<?> showPlanDetails(@PathVariable String planCode) {
	try {
	    PlanDetailsOutputs planDetails = dataService.showPlanDetails(planCode);
	    return new ResponseEntity<PlanDetailsOutputs>(planDetails, HttpStatus.ACCEPTED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
    }

    @PostMapping("/savePlan")
    public ResponseEntity<String> savePlanDetails(@RequestBody PlanInputs plan) {
	try {
	    String msg = dataService.savePlanDetails(plan);
	    return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @GetMapping("/fetchSavedPlan/{appId}")
    public ResponseEntity<?> fetchSelectedPlanDetails(@PathVariable Integer appId) {
	try {
	    PlanInputs inputs = dataService.getSavedPlanDetails(appId);
	    return new ResponseEntity<PlanInputs>(inputs, HttpStatus.FOUND);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
    }

    @PutMapping("/updatePlan")
    public ResponseEntity<String> updateSelectedPlan(@RequestBody PlanInputs plan) {
	try {
	    String msg = dataService.updatePlan(plan);
	    return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
    }

    @PostMapping("/saveIncome")
    public ResponseEntity<String> saveIncomeDetais(@RequestBody IncomeInputs income) {
	try {
	    String msg = dataService.saveIncomeDetails(income);
	    return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @GetMapping("/fetchIncomeDetails/{appId}")
    public ResponseEntity<?> fetchSavedIncomeDetails(@PathVariable Integer appId) {
	try {
	    IncomeInputs inputs = dataService.fetchIncomeDetails(appId);
	    return new ResponseEntity<IncomeInputs>(inputs, HttpStatus.FOUND);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @PutMapping("/updateIncome")
    public ResponseEntity<String> updateIncomePlan(@RequestBody IncomeInputs inputs) {
	try {
	    String msg = dataService.updateIncomeDetails(inputs);
	    return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
    }

    @PostMapping("/saveEducation")
    public ResponseEntity<?> saveEducationDetais(@RequestBody EducationInputs education) {
	try {
	    String msg = dataService.saveEducationDetails(education);
	    return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @GetMapping("/fetchEducation/{appId}")
    public ResponseEntity<?> fetchEducationDetails(@PathVariable Integer appId) {
	try {
	    EducationInputs eduInputs = dataService.fetchEducationDetails(appId);
	    return new ResponseEntity<EducationInputs>(eduInputs, HttpStatus.FOUND);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @PutMapping("/updateEducation")
    public ResponseEntity<String> updateEducation(@RequestBody EducationInputs inputs) {
	try {
	    String msg = dataService.updateEducationDetails(inputs);
	    return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
    }

    @PostMapping("/saveChild")
    public ResponseEntity<String> saveChildrenDetails(@RequestBody List<ChildInputs> child) {
	try {
	    String msg = dataService.saveChildrenDetails(child);
	    return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @GetMapping("/fetchChild/{appId}")
    public ResponseEntity<?> fetchChildDetails(@PathVariable Integer appId) {
	try {
	    List<ChildInputs> childInputs = dataService.fetchChildDetails(appId);
	    return new ResponseEntity<List<ChildInputs>>(childInputs, HttpStatus.FOUND);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @PutMapping("/updateChild")
    public ResponseEntity<?> updateChildDetails(@RequestBody List<ChildInputs> childInputs) {
	try {
	    String msg = dataService.updateChildDetails(childInputs);
	    return new ResponseEntity<String>(msg, HttpStatus.CREATED);

	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

    @PostMapping("/show_Report/{appId}")
    public ResponseEntity<?> showDcReport(@PathVariable Integer appId) {
	System.out.println("DataCollectionOperationController.showDcReport()");
	try {
	    System.out.println("DataCollectionOperationController.showDcReport()" + appId);
	    DcSummaryReport report = dataService.showDCSummaryReport(appId);
	    return new ResponseEntity<DcSummaryReport>(report, HttpStatus.OK);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.REQUEST_TIMEOUT);
	}
    }

}
