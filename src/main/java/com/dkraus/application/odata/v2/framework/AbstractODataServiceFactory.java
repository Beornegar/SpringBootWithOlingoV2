package com.dkraus.application.odata.v2.framework;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

import com.dkraus.application.odata.v2.interfaces.OdataProvider;
import com.dkraus.application.odata.v2.services.ODataServices;

/**
 * Defines the content and functionality of a single OData-Service by adding
 * {@link OdataProvider} to it. It will automatically find all
 * {@link OdataProvider} belonging to the {@link EdmEntityType}'s found in the
 * given entity class path defined in the {@link ODataServices}.
 * 
 * The service still has to be bound via a {@link ResourceConfig}.
 * 
 * @author Dennis
 */
public abstract class AbstractODataServiceFactory extends ODataServiceFactory {

	private List<OdataProvider<?>> odataProviders;

	protected abstract ODataServices getODataEntityClassPath();

	@Override
	public ODataService createService(final ODataContext ctx) throws ODataException {
		ExtendedAnnotationEdmProvider edmProvider = new ExtendedAnnotationEdmProvider(
				getODataEntityClassPath().getENTITY_PACKAGE());

		List<OdataProvider<?>> odataProvidersOfSameOdataService = getAllODataProvidersInServicePath(
				edmProvider.getAnnotatedClasses());

		ODataSingleProcessor singleProcessor = new ODataServiceProxy(ctx, odataProvidersOfSameOdataService);

		return createODataSingleProcessorService(edmProvider, singleProcessor);
	}

	private List<OdataProvider<?>> getAllODataProvidersInServicePath(List<Class<?>> odataEntityClasses) {
		List<OdataProvider<?>> odataProvidersOfODataService = new ArrayList<>();

		for (Class<?> odataEntityClass : odataEntityClasses) {
			for (OdataProvider<?> odataProvider : odataProviders) {
				Class<?> extendingClassOfProvider = (Class<?>) ((ParameterizedType) odataProvider.getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0];

				if (extendingClassOfProvider.equals(odataEntityClass)) {
					odataProvidersOfODataService.add(odataProvider);
				}
				break;
			}
		}

		if (odataProvidersOfODataService.size() != odataEntityClasses.size()) {
			System.err.println(
					"Not every Odata-Entity has an ODataProvider! Therefore not every entity can be served by the odata service");
		}

		return odataProvidersOfODataService;
	}

	@Autowired
	public final void setOdataProviders(List<OdataProvider<?>> odataProviders) {
		this.odataProviders = odataProviders;
	}

}
