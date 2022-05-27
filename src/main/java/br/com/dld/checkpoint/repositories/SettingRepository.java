package br.com.dld.checkpoint.repositories;

import br.com.dld.checkpoint.models.entities.Setting;
import br.com.dld.checkpoint.models.enums.SettingKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author David Duarte
 */
public interface SettingRepository extends JpaRepository<Setting, Long> {

    List<Setting> findAllByAccountId_Id(long accountId);

    Optional<Setting> findByAccountId_IdAndSettingKey(long accountId, SettingKey settingsKey);
}
