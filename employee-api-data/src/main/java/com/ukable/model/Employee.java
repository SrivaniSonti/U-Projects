package com.ukable.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@ApiModelProperty(notes = "ID of the Employee", name = "id", required = true)
	private String id;

	@ApiModelProperty(notes = "Name of the Employee", name = "employeeName", required = true)
	private String employeeName;

	@ApiModelProperty(notes = "Employee Phone number", name = "employeePhoneNumber")
	private String employeePhoneNumber;

	@ManyToOne()
	@ApiModelProperty(notes = "Department of the Employee", name = "department", hidden = true)
	private Department department;
	
	public Employee(String id, String employeeName, String employeePhoneNumber, Department department) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeePhoneNumber = employeePhoneNumber;
		this.department = department;
	}

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
