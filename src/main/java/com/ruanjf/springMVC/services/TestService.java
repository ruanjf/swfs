package com.ruanjf.springMVC.services;

import java.util.List;

import com.ruanjf.springMVC.persistent.Test;

public interface TestService {

	public abstract Test getTest(String id);

	public abstract List<Test> getTests();

	public abstract void addTest(Test test);
	
}
