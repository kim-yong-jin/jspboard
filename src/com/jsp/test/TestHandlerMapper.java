package com.jsp.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsp.dispatcher.HandlerMapper;

public class TestHandlerMapper {
	
	private HandlerMapper handlerMapper;
	
	@Before
	public void init() throws Exception{
		handlerMapper = new HandlerMapper();
	}
	
	
	
	@Test
	public void test1() {
		fail("Not yet implemented");
	}
	
	
	@After
	public void complete() {
		
		
	}
	
}
