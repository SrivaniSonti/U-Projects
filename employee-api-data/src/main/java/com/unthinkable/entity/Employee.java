package com.unthinkable.entity;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	
	@Id
	@ApiModelProperty(notes= "ID of the Employee", name = "id", required = true)
	private String id;
	
	@ApiModelProperty(notes= "Name of the Employee", name = "employeeName", required = true)
	private String employeeName;
	
	@ApiModelProperty(notes= "Employee Phone number", name = "employeePhoneNumber")
	private String employeePhoneNumber;
	
    @ManyToOne()
    @ApiModelProperty(notes= "Department of the Employee", name = "department", required = true)
    private Department department;
    
}
