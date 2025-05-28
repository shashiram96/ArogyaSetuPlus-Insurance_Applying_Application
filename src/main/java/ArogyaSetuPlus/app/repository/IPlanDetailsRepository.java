package ArogyaSetuPlus.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.DcPlanDetailsEntity;

public interface IPlanDetailsRepository extends JpaRepository<DcPlanDetailsEntity, String> {
    
    public DcPlanDetailsEntity findByPlanCode(String planCode);

}
