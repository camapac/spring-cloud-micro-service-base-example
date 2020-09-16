package com.paytech.vita.oauth2.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4654714485581551649L;

	@Version
	protected Long version;

	@CreationTimestamp
	protected LocalDateTime createdOn;

	@UpdateTimestamp
	protected LocalDateTime updatedOn;
	
	protected boolean enabled;
	
	
	public boolean isEnabled() {
		return enabled;
	}

}