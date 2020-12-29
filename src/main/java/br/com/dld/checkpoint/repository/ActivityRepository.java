package br.com.dld.checkpoint.repository;

import br.com.dld.checkpoint.model.Activity;
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

    Optional<Activity> findFirstByReferenceOrderByCreationDesc(LocalDate reference);

    Optional<Activity> findFirstByReferenceAndBeginning(LocalDate reference, LocalTime beginning);

    List<Activity> findAllByReferenceOrderByCreation(LocalDate reference);

    @Query(nativeQuery = true,
            value = "SELECT name, type, TIMEDIFF(SUM(ended), SUM(beginning)) AS total"
            + " FROM activity WHERE reference = :reference"
            + " GROUP BY type, name ORDER BY creation")
    List<Object[]> getSummary(@Param("reference") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate reference);
}
