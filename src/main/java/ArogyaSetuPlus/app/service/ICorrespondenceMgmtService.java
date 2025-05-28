package ArogyaSetuPlus.app.service;

import ArogyaSetuPlus.app.bindings.CoTriggerSummary;

public interface ICorrespondenceMgmtService {
    
    public CoTriggerSummary processPendingTriggers() throws Exception;

}
