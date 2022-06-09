package com.dkraus.odata.annotations.odata.odata;

import java.util.List;
import java.util.Map;

public interface ODataResponseConverter<T> {

	List<Map<String, Object>> convertToODataResponse(List<T> objectsToConvert);
	
}
