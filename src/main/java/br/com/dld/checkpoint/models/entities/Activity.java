package br.com.dld.checkpoint.models.entities;

import br.com.dld.checkpoint.models.enums.ActivityType;
import lombok.Getter;
import lombok.Setter;

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
 @author David Duarte */
@Entity
@Getter
@Setter
public class Activity implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountId")
	private Account accountId;
}
