package com.dkraus.application.odata.service.config;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.dkraus.application.odata.service.CustomODataServiceFactory;

@Component
@ApplicationPath("/odata2/")
public class ODataJerseyConfig extends ResourceConfig {

	public ODataJerseyConfig(CustomODataServiceFactory serviceFactory) {
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

        private CustomODataServiceFactory serviceFactory;

        @Inject
        public ODataServiceRootLocator (CustomODataServiceFactory serviceFactory) {
            this.serviceFactory = serviceFactory;
        }

        @Override
        public ODataServiceFactory getServiceFactory() {
            return this.serviceFactory;
        }
    }
	
}
