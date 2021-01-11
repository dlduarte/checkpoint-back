package br.com.dld.checkpoint.model;

import br.com.dld.checkpoint.model.enums.ActivityType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Column
    private LocalDate reference;

    @Column
    private LocalTime beginning;

    @Column
    private LocalTime ended;

    @Column
    private LocalDateTime creation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public LocalDate getReference() {
        return reference;
    }

    public void setReference(LocalDate reference) {
        this.reference = reference;
    }

    public LocalTime getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalTime beginning) {
        this.beginning = beginning;
    }

    public LocalTime getEnded() {
        return ended;
    }

    public void setEnded(LocalTime ended) {
        this.ended = ended;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }
}
