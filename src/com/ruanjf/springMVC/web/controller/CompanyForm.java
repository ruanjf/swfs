package com.ruanjf.springMVC.web.controller;

import com.ruanjf.springMVC.persistent.Company;

public class CompanyForm extends Company {
	
	private String principalName;

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

}
