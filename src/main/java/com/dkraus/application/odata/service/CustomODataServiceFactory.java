package com.dkraus.application.odata.service;

import java.util.Arrays;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dkraus.application.odata.entity.car.ODataCarProvider;
import com.dkraus.application.odata.service.framework.ExtendedAnnotationEdmProvider;
import com.dkraus.application.odata.service.framework.ODataServiceProxy;
import com.dkraus.application.odata.service.interfaces.OdataProvider;

/**
 * Defines the content and functionality of a single OData-Service by adding {@link OdataProvider} to it.
 * Has to be referenced in the {@link ODataServletRegistrationConfiguration}. 
 * 
 * @author Dennis
 */
@Component
public class CustomODataServiceFactory extends ODataServiceFactory {

	@Value("${odata.service.entity.path:com.dkraus.application.odata.entity}")
	private String odataEntityPackagePath;
	
	private final ODataCarProvider oDataCarProvider;
	
	@Autowired
	CustomODataServiceFactory(ODataCarProvider oDataCarProvider) {
		this.oDataCarProvider = oDataCarProvider;
		
	}
	
	@Override
	public ODataService createService(final ODataContext ctx) throws ODataException {

		EdmProvider edmProvider = new ExtendedAnnotationEdmProvider(odataEntityPackagePath);
		ODataSingleProcessor singleProcessor = new ODataServiceProxy(ctx, Arrays.asList(oDataCarProvider));

		return createODataSingleProcessorService(edmProvider, singleProcessor);
	}
}


