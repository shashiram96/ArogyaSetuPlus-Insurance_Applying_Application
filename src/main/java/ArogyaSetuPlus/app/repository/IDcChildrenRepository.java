package ArogyaSetuPlus.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ArogyaSetuPlus.app.entity.DcChildrenEntity;

public interface IDcChildrenRepository extends JpaRepository<DcChildrenEntity, Integer> {
    
    public List<DcChildrenEntity> findAllByAppId(Integer appId);

}
