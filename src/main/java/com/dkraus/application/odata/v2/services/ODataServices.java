package com.dkraus.application.odata.v2.services;

/**
 * Contains all existing OData-Services and their package where the entites can be found.
 * 
 * @author Dennis
 */
public enum ODataServices {

	CAR_SERVICE("com.dkraus.application.odata.v2.car");
	
	private final String ENTITY_PACKAGE;
	
	ODataServices(String packagePath) {
		this.ENTITY_PACKAGE = packagePath;
	}

	public String getENTITY_PACKAGE() {
		return ENTITY_PACKAGE;
	}
}
