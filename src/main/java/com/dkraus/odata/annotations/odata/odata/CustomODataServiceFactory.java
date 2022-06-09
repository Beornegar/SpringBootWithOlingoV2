package com.dkraus.odata.annotations.odata.odata;

import java.util.Arrays;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.springframework.stereotype.Component;

import com.dkraus.odata.annotations.odata.entity.car.CarProvider;

/**
 * Defines the content and functionality of a single OData-Service by adding {@link OdataProvider} to it.
 * Has to be referenced in the {@link ODataServletRegistrationConfiguration}. 
 * 
 * @author Dennis
 */
@Component
public class CustomODataServiceFactory extends ODataServiceFactory {

	@Override
	public ODataService createService(final ODataContext ctx) throws ODataException {

		EdmProvider edmProvider = new ExtendedAnnotationEdmProvider("com.dkraus.odata.annotations.odata.entity");
		ODataSingleProcessor singleProcessor = new ODataServiceProxy(ctx, Arrays.asList(new CarProvider()));

		return createODataSingleProcessorService(edmProvider, singleProcessor);
	}
}


