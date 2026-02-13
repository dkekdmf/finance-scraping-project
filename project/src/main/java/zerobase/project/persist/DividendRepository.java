package zerobase.project.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import zerobase.project.model.Dividend;
import zerobase.project.persist.entity.DividenedEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<DividenedEntity,Long> {

    List<DividenedEntity> findAllByCompanyId(Long companyId);
    @Transactional
    void deleteAllByCompnayId(Long id);
    boolean existsByCompanyIdAndDate(Long companyId, LocalDateTime date);

}
