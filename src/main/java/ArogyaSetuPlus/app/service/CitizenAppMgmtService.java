package ArogyaSetuPlus.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ArogyaSetuPlus.app.bindings.CitizenRegistrationInputs;
import ArogyaSetuPlus.app.entity.CitizenRegistrationEntity;
import ArogyaSetuPlus.app.repository.ICitizenRegistrationRepository;
	

@Service
public class CitizenAppMgmtService implements ICitizenRegistrationMgmtService {
    @Autowired
    private ICitizenRegistrationRepository registrationRepo;
    @Autowired
    //private RestTemplate template;
    private WebClient webClient;
    @Value("${ar.CHII-web-url}")
    private String endpointUrl;
    @Value("${ar.targetName}")
    private String targetName;

    @Override
    public String registerCitizenApplcation(CitizenRegistrationInputs Inputs) throws Exception {
	try {
	    String stateName = webClient.get().uri(endpointUrl, Inputs.getChiiNumber()).retrieve()
		    .bodyToMono(String.class).block();
	    CitizenRegistrationEntity entity = new CitizenRegistrationEntity();
	    BeanUtils.copyProperties(Inputs, entity);
	    entity.setStateName(stateName);
	    //entity.setCHIINumber(Inputs.getChiiNumber());
	    int appId = registrationRepo.save(entity).getAppId();
	    if (appId > 0) {
		return "Citizen Registration Successful with Reference Number " + appId;
	    } else {
		return "Sorry! We have little problem at our end ";
	    }

	} catch (DataIntegrityViolationException e) {
	        return "FAILED: Duplicate phone number, email, or CHII number.";
	    } catch (Exception e) {
	        return "FAILED: " + e.getMessage();
	    }
}
}
