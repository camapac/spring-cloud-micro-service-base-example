package com.paytech.vita.oauth2.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseWithIdEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4259189303075050147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
}
