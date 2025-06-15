
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
	    String msg  = registrationService.registerCitizenApplcation(inputs);
	    if (msg.startsWith("Citizen Registration Successful")) {
                return new ResponseEntity<>(msg, HttpStatus.OK);
            } else if (msg.startsWith("FAILED: Duplicate")) {
                return new ResponseEntity<>(msg, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
	}
    }
}
