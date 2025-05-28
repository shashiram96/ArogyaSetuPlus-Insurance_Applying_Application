package ArogyaSetuPlus.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {
    
    public DcEducationEntity findByAppId(Integer appId);

}
