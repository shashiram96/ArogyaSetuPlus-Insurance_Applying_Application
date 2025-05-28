package ArogyaSetuPlus.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {
    
    public DcIncomeEntity findByAppId(Integer appId);

}
