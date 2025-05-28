package ArogyaSetuPlus.app.service;

import ArogyaSetuPlus.app.bindings.EligibilityDetailsOutput;

public interface IEligibilityDetailsMgmtService {

    public EligibilityDetailsOutput determineEligibility(Integer appId);

}
