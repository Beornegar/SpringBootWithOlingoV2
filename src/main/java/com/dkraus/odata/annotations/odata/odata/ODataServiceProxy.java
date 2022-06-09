package com.dkraus.odata.annotations.odata.odata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

public class ODataServiceProxy extends ODataSingleProcessor {

	private final Map<String, OdataProvider> odataProvider = new HashMap<>();

	public ODataServiceProxy(final ODataContext odataContext, List<OdataProvider> providers) {

		for(OdataProvider provider : providers) {
			provider.setContext(odataContext);
			odataProvider.put(provider.getName(), provider);
		}
		
	}

	@Override
	public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {

		EdmEntitySet entitySet;

		if (uriInfo.getNavigationSegments().isEmpty()) {
			entitySet = uriInfo.getStartEntitySet();

			OdataProvider provider = odataProvider.get(entitySet.getName());
			if (Objects.isNull(provider)) {
				throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
			}

			return provider.readEntitySet(uriInfo, contentType, entitySet);

		} else if (uriInfo.getNavigationSegments().size() == 1) {
			throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
		}

		throw new ODataNotImplementedException();
	}

	@Override
	public ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType) throws ODataException {

		if (uriInfo.getNavigationSegments().isEmpty()) {
			EdmEntitySet entitySet = uriInfo.getStartEntitySet();

			OdataProvider provider = odataProvider.get(entitySet.getName());
			if (Objects.isNull(provider)) {
				throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
			}

			return provider.readEntity(uriInfo, contentType, entitySet);

		} else if (uriInfo.getNavigationSegments().size() == 1) {
			throw new ODataNotImplementedException();
		}

		throw new ODataNotImplementedException();
	}

}
