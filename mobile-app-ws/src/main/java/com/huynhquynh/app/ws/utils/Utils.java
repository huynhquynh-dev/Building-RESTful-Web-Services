package com.huynhquynh.app.ws.utils;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class Utils {

	public String generateID() {
		 return UUID.randomUUID().toString();
	}
}
