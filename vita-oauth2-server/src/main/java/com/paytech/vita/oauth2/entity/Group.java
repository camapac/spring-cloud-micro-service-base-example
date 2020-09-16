package com.paytech.vita.oauth2.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "group")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseWithIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4472040153544766137L;
	private String name;
	private String description;
	@Column(name = "parent_id")
	private Long parentId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permission_group", joinColumns = {
			@JoinColumn(name = "group_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private List<Permission> permissions;

}
