package com.packt.cardatabase.domain;

import lombok.Data;

@Data
public class AccountCredentials {
	private String userid;
	private String password;
	private String username;
	private String role;
}
