package ArogyaSetuPlus.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.EligibilityDetailsEntity;

public interface IEligibilityDeterminationRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {
    
    public EligibilityDetailsEntity findByAppId(Integer appId);

}
