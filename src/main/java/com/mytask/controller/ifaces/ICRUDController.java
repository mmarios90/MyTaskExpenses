package com.mytask.controller.ifaces;

import java.util.List;

import org.hibernate.Session;

public interface ICRUDController {

	public <T> List<T> fetchRows(Session session, String query);
	
}
