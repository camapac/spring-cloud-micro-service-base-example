package com.paytech.vita.oauth2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "permission")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseWithIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3089544930319978421L;
	private String name;
	private String description;
}
