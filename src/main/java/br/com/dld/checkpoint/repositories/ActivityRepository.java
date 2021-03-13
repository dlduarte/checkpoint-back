package br.com.dld.checkpoint.repositories;

import br.com.dld.checkpoint.entities.Activity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author David Duarte
 */
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findFirstByAccountId_IdAndReferenceOrderByCreationDesc(long accountId, LocalDate reference);

    Optional<Activity> findFirstByAccountId_IdAndReferenceAndBeginning(long accountId, LocalDate reference, LocalTime beginning);

    List<Activity> findAllByAccountId_IdAndReferenceOrderByCreation(long accountId, LocalDate reference);

    @Query(nativeQuery = true,
            value = "SELECT name, type, reference, SEC_TO_TIME(SUM(TIME_TO_SEC(ended)) - SUM(TIME_TO_SEC(beginning))) AS total"
            + " FROM activity WHERE accountId = :accountId AND reference = :reference GROUP BY name, type"
            + " ORDER BY FIELD(type, 'PAID', 'UNPAID', 'JUSTIFIED_ABSENCE', 'UNEXCUSED_ABSENCE'), beginning")
    List<Object[]> getSummary(@Param("accountId") long accountId,
            @Param("reference") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reference);
}
