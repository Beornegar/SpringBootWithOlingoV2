package com.dkraus.application.odata.service.interfaces;

import java.util.List;
import java.util.Map;

public interface ODataResponseConverter<T> {

	List<Map<String, Object>> convertToODataResponse(List<T> objectsToConvert);
	
}
