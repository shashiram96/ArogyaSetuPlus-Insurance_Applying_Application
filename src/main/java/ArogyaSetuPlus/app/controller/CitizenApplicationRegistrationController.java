
package ArogyaSetuPlus.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ArogyaSetuPlus.app.bindings.CitizenRegistrationInputs;
import ArogyaSetuPlus.app.service.ICitizenRegistrationMgmtService;

@RestController
@RequestMapping("/citizenReg")
@CrossOrigin(origins = "http://localhost:3000")
public class CitizenApplicationRegistrationController {
    @Autowired
    private ICitizenRegistrationMgmtService registrationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveApplication(@RequestBody CitizenRegistrationInputs inputs) {
	try {
	    int appId = registrationService.registerCitizenApplcation(inputs);
	    if (appId > 0) {
		return new ResponseEntity<Integer>(appId,HttpStatus.OK);
	    } else {
		return new ResponseEntity<String>("Invalid CHII Number or State ", HttpStatus.NOT_FOUND);
	    }
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
	}
    }
}
