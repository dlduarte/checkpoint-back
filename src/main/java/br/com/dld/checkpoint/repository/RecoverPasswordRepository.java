package br.com.dld.checkpoint.repository;

import br.com.dld.checkpoint.model.RecoverPassword;
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
