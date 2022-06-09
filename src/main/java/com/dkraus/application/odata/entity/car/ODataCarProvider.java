package com.dkraus.application.odata.entity.car;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties.ODataEntityProviderPropertiesBuilder;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkraus.application.database.entities.Car;
import com.dkraus.application.database.service.CarBusinessService;
import com.dkraus.application.odata.service.interfaces.ODataResponseConverter;
import com.dkraus.application.odata.service.interfaces.OdataProvider;

@Service
public class ODataCarProvider extends OdataProvider implements ODataResponseConverter<Car> {

	private final CarBusinessService carService;

	@Autowired
	public ODataCarProvider(CarBusinessService carService) {
		this.carService = carService;
	}

	@Override
	public String getName() {
		return "ODataCarSet";
	}

	@Override
	public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType, EdmEntitySet entitySet)
			throws ODataException {

		URI serviceRoot = getContext().getPathInfo().getServiceRoot();
		ODataEntityProviderPropertiesBuilder builder = EntityProviderWriteProperties.serviceRoot(serviceRoot);
		EntityProviderWriteProperties properties = builder.build();

		return EntityProvider.writeFeed(contentType, entitySet, convertToODataResponse(carService.findAll()),
				properties);
	}

	@Override
	public ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType, EdmEntitySet entitySet)
			throws ODataException {

		if (uriInfo.getNavigationSegments().isEmpty()) {

			int id = getKeyValue(uriInfo.getKeyPredicates().get(0));
			Map<String, Object> data = convertToODataResponse(carService.findById(id));

			if (data != null) {
				URI serviceRoot = getContext().getPathInfo().getServiceRoot();
				ODataEntityProviderPropertiesBuilder propertiesBuilder = EntityProviderWriteProperties
						.serviceRoot(serviceRoot);

				return EntityProvider.writeEntry(contentType, entitySet, data, propertiesBuilder.build());
			}
		}

		throw new ODataNotImplementedException();
	}

	private int getKeyValue(KeyPredicate key) throws ODataException {
		EdmProperty property = key.getProperty();
		EdmSimpleType type = (EdmSimpleType) property.getType();
		return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), Integer.class);
	}

	@Override
	public List<Map<String, Object>> convertToODataResponse(List<Car> objectsToConvert) {

		List<Map<String, Object>> oDataContent = new ArrayList<>();

		for (Car car : objectsToConvert) {
			oDataContent.add(convertToODataResponse(car));
		}

		return oDataContent;
	}


	public Map<String, Object> convertToODataResponse(Car car) {

		Map<String, Object> data = new HashMap<>();

		data.put("Id", car.getId());
		data.put("Type", car.getType());
		data.put("BigDecimal", null);
		data.put("BigInteger", null);
		data.put("Timestamp", null);
		data.put("LongObject", null);
		data.put("Longe", null);
		data.put("IntObject", null);
		data.put("Inte", null);
		data.put("BoolE", car.isUsed());

		return data;
	}
}
