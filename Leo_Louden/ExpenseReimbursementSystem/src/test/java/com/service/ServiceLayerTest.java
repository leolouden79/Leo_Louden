package com.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.daolayer.DAO;
import com.service.ServiceLayer;

public class ServiceLayerTest {

	@Test
	public void SessionsHashTest() {
		
		String result = DAO.getHash("username", "password");
		System.out.println(result);
		assertThat(DAO.getHash("username", "password"), notNullValue());
		
	}

}
