package ArogyaSetuPlus.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.DcSelectedPlanDetailsEntity;

public interface IDcPlanRepository extends JpaRepository<DcSelectedPlanDetailsEntity, Integer> {
    
    public DcSelectedPlanDetailsEntity findByAppId(Integer caseNo);

}
