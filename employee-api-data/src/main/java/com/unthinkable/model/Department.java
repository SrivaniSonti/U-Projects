package com.unthinkable.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Department {

	public Department(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@ApiModelProperty(notes = "ID of the Department", name = "id", required = true)
	private String id;

	@ApiModelProperty(notes = "Name of the Department", name = "name", required = true)
	private String name;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	@ApiModelProperty(hidden = true)
	private String createdBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	@ApiModelProperty(hidden = true)
	protected Date createdDate;

	@LastModifiedBy
	@Column(name = "modified_by")
	@ApiModelProperty(hidden = true)
	private String modifiedBy;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	@Column(name = "modified_date")
	@ApiModelProperty(hidden = true)
	 protected Date lastModifiedDate;

}
