package ArogyaSetuPlus.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public Integer registerCitizenApplcation(CitizenRegistrationInputs Inputs) {

	String stateName = webClient.get().uri(endpointUrl, Inputs.getChiiNumber()).retrieve().bodyToMono(String.class).block();
	CitizenRegistrationEntity entity = new CitizenRegistrationEntity();
	BeanUtils.copyProperties(Inputs, entity);
	entity.setStateName(stateName);
	//entity.setCHIINumber(Inputs.getChiiNumber());
	int appId = registrationRepo.save(entity).getAppId();
	return appId;

    }
}
