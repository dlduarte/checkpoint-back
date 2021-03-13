package br.com.dld.checkpoint.services;

import br.com.dld.checkpoint.config.security.auth.AuthenticationFacade;
import br.com.dld.checkpoint.dto.setting.SettingDto;
import br.com.dld.checkpoint.entities.Setting;
import br.com.dld.checkpoint.entities.enums.SettingKey;
import br.com.dld.checkpoint.forms.setting.SettingForm;
import br.com.dld.checkpoint.repositories.SettingRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David Duarte
 */
@Service
public class SettingService {

    @Autowired
    private AuthenticationFacade facade;

    @Autowired
    private SettingRepository settingsRepository;

    public List<SettingDto> findAll() throws Exception, RuntimeException {

        List<Setting> realList = settingsRepository
                .findAllByAccountId_Id(facade
                        .getAccount()
                        .getId()
                );

        List<Setting> list = new ArrayList();
        for (SettingKey s : SettingKey.values()) {
            Optional<Setting> optional = realList
                    .stream()
                    .filter(item -> item
                    .getSettingKey()
                    .equals(s)
                    )
                    .findFirst();

            if (optional.isPresent()) {
                list.add(optional.get());
            } else {
                Setting setting = new Setting();
                setting.setSettingKey(s);

                list.add(setting);
            }
        }

        return list
                .stream()
                .map(SettingDto::new)
                .collect(Collectors.toList());
    }

    public Optional<Setting> find(SettingKey settings) throws Exception, RuntimeException {
        return settingsRepository
                .findByAccountId_IdAndSettingKey(facade
                        .getAccount()
                        .getId(),
                        settings
                );
    }

    @Transactional
    public Setting save(SettingForm form) throws Exception, RuntimeException {

        Optional<Setting> optSetting = find(form.getKey());

        Setting setting;
        if (optSetting.isPresent()) {
            setting = optSetting.get();
            setting.setCurrentValue(form.getValue());
            setting.setAccountId(facade.getAccount());
        } else {
            setting = form.convert(facade.getAccount());
        }

        return settingsRepository.save(setting);
    }
}
