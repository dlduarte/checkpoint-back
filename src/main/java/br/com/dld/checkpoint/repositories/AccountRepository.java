package br.com.dld.checkpoint.repositories;

import br.com.dld.checkpoint.models.entities.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author David Duarte
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmailOrUsername(String email, String username);
}
