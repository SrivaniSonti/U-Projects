package com.unthinkable.department;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Department {
	
	@Id
	@ApiModelProperty(notes= "ID of the Department", name = "id", required = true)
	private String id;
	
	@ApiModelProperty(notes= "Name of the Employee", name = "name", required = true)
	private String name;
	
}
