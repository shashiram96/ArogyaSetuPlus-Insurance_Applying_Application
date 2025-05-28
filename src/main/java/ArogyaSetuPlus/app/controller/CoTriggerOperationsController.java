package ArogyaSetuPlus.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ArogyaSetuPlus.app.bindings.CoTriggerSummary;
import ArogyaSetuPlus.app.service.ICorrespondenceMgmtService;

@RestController
@RequestMapping("/trigger")
@CrossOrigin(origins = "http://localhost:3000")
public class CoTriggerOperationsController {
    @Autowired
    private ICorrespondenceMgmtService triggerService;
    @GetMapping("/activate")
    public ResponseEntity<?> processandUpdateTrigger() throws Exception {
	try {
	    CoTriggerSummary summary = triggerService.processPendingTriggers();
	    return new ResponseEntity<CoTriggerSummary>(summary, HttpStatus.OK);
	} catch (Exception e) {
	    return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

}
