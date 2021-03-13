package br.com.dld.checkpoint.repositories;

import br.com.dld.checkpoint.entities.RecoverPassword;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author David Duarte
 */
public interface RecoverPasswordRepository extends JpaRepository<RecoverPassword, Long> {

    Optional<RecoverPassword> findFirstByAccountId_Id(long id);
    Optional<RecoverPassword> findFirstByCode(String code);
}
