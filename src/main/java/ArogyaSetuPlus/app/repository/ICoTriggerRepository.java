package ArogyaSetuPlus.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.CoTriggersEntity;

public interface ICoTriggerRepository extends JpaRepository<CoTriggersEntity, Integer> {
    
    public List<CoTriggersEntity> findByTriggerStatus(String status);

    public CoTriggersEntity findByAppId(Integer appId);
}
