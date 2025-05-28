package ArogyaSetuPlus.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ArogyaSetuPlus.app.bindings.EligibilityDetailsOutput;
import ArogyaSetuPlus.app.service.IEligibilityDetailsMgmtService;

@Controller
@RequestMapping("eligible")
@CrossOrigin(origins = "http://localhost:3000")
public class EligibilityDeterminationController {
    @Autowired
    private IEligibilityDetailsMgmtService eligiblityService;
    @GetMapping("/check-Eligible/{appId}")
    public ResponseEntity<?> finalizeApproval(@PathVariable Integer appId) {
	try {
	    EligibilityDetailsOutput eligibleOutput = eligiblityService.determineEligibility(appId);
	    return new ResponseEntity<EligibilityDetailsOutput>(eligibleOutput, HttpStatus.ACCEPTED);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }

}
