package com.dkraus.application.odata.service.interfaces;

import java.util.List;
import java.util.Map;

import com.dkraus.application.database.entities.DatabaseEntity;

public interface ODataResponseConverter<T extends DatabaseEntity> {

	List<Map<String, Object>> convertToODataResponse(List<T> objectsToConvert);
	
	Map<String, Object> convertToODataResponse(T objectToConvert);
	
}
