package com.dkraus.odata.annotations.odata.entity.car;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.stereotype.Service;

import com.dkraus.odata.annotations.odata.odata.ODataResponseConverter;
import com.dkraus.odata.annotations.odata.odata.OdataProvider;

@Service
public class CarProvider extends OdataProvider implements ODataResponseConverter<Car> {

	@Override
	public String getName() {
		return "CarSet";
	}

	@Override
	public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType, EdmEntitySet entitySet)
			throws ODataException {

		URI serviceRoot = getContext().getPathInfo().getServiceRoot();
		ODataEntityProviderPropertiesBuilder builder = EntityProviderWriteProperties.serviceRoot(serviceRoot);
		EntityProviderWriteProperties properties = builder.build();

		return EntityProvider.writeFeed(contentType, entitySet,
				convertToODataResponse(Arrays.asList(createCar(1L), createCar(2L))), properties);
	}

	@Override
	public ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType, EdmEntitySet entitySet)
			throws ODataException {

		if (uriInfo.getNavigationSegments().isEmpty()) {

			int id = getKeyValue(uriInfo.getKeyPredicates().get(0));
			List<Map<String, Object>> data = convertToODataResponse(Arrays.asList(createCar(id)));

			if (data != null && data.size() == 1) {
				URI serviceRoot = getContext().getPathInfo().getServiceRoot();
				ODataEntityProviderPropertiesBuilder propertiesBuilder = EntityProviderWriteProperties
						.serviceRoot(serviceRoot);

				return EntityProvider.writeEntry(contentType, entitySet, data.get(0), propertiesBuilder.build());
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
			Map<String, Object> data = new HashMap<>();

			data.put("Id", car.getId());
			data.put("Type", car.getType());
			data.put("BigDecimal", car.getBigDecimal());
			data.put("BigInteger", car.getBigInteger());
			data.put("Timestamp", car.getTimestamp());
			data.put("LongObject", car.getLongObject());
			data.put("Longe", car.getLonge());
			data.put("IntObject", car.getIntObject());
			data.put("Inte", car.getInte());
			data.put("BoolObject", car.getBoolObject());
			data.put("BoolE", car.getBoolE());

			oDataContent.add(data);
		}

		return oDataContent;
	}

	private Car createCar(long id) {
		Car car = new Car();
		car.setId(id);
		car.setType("Type" + id);
		car.setBigDecimal(BigDecimal.ONE);
		car.setBigInteger(BigInteger.ONE);
		car.setBoolE(false);
		car.setBoolObject(Boolean.TRUE);
		car.setTimestamp(Timestamp.from(Instant.now()));
		car.setLongObject(Long.valueOf(1L));
		car.setLongObject(2L);
		car.setInte(3);
		car.setIntObject(Integer.valueOf(4));

		return car;
	}
}
