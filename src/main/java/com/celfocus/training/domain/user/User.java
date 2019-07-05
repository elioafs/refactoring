package com.celfocus.training.domain.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String name;
	private Date birthDate;
	private boolean isSenior;

}
