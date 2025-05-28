package ArogyaSetuPlus.app.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.CitizenRegistrationEntity;

public interface IAppRegRepository extends JpaRepository<CitizenRegistrationEntity, Integer> {
    
    public Optional<CitizenRegistrationEntity> findByAppId(Integer appId);

}
