package com.johnbryce.cs.security;

import com.johnbryce.cs.service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
	private ClientService clientService;
	private long timeStamp;
}
