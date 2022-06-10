package com.dkraus.application.odata.v2.car.config;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.dkraus.application.odata.v2.car.service.CarODataService;
import com.dkraus.application.odata.v2.framework.AbstractODataServiceFactory;

@Component
@ApplicationPath("/odata/v2/car/")
public class CarODataServiceJerseyConfig extends ResourceConfig {

	public CarODataServiceJerseyConfig(CarODataService serviceFactory) {
        ODataApplication oDataApplication = new ODataApplication();
        oDataApplication
                .getClasses()
                .forEach( c -> {
                    if ( !ODataRootLocator.class.isAssignableFrom(c)) {
                        register(c);
                    }
                });
        register(new ODataServiceRootLocator(serviceFactory));
    }

    @Path("/")
    public static class ODataServiceRootLocator extends ODataRootLocator {

        private AbstractODataServiceFactory serviceFactory;

        @Inject
        public ODataServiceRootLocator (AbstractODataServiceFactory serviceFactory) {
            this.serviceFactory = serviceFactory;
        }

        @Override
        public ODataServiceFactory getServiceFactory() {
            return this.serviceFactory;
        }
    }
	
}
