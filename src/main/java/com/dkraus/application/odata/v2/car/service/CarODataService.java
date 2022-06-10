package com.dkraus.application.odata.v2.car.service;

import org.springframework.stereotype.Component;

import com.dkraus.application.odata.v2.framework.AbstractODataServiceFactory;
import com.dkraus.application.odata.v2.services.ODataServices;

@Component
public class CarODataService extends AbstractODataServiceFactory {

	@Override
	protected ODataServices getODataEntityClassPath() {
		return ODataServices.CAR_SERVICE;
	}

	
	
}
