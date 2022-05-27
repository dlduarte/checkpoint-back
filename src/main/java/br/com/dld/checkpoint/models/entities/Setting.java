package br.com.dld.checkpoint.models.entities;

import br.com.dld.checkpoint.models.enums.SettingKey;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author David Duarte
 */
@Entity
@Getter
@Setter
public class Setting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SettingKey settingKey;

    @Column(nullable = false)
    private String currentValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account accountId;

    public boolean isValid() {
        return currentValue != null && !"".equals(currentValue.trim());
    }
}
