package com.dkraus.application.odata.service.interfaces;

import java.lang.reflect.ParameterizedType;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

public abstract class OdataProvider<T extends ODataEntity> extends ODataSingleProcessor{

    public abstract ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType, EdmEntitySet entitySet) throws ODataException;

    public abstract ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType, EdmEntitySet entitySet) throws ODataException;

    public String getName() {
    	Class<T> persistentClass = (Class<T>)
    			   ((ParameterizedType)getClass().getGenericSuperclass())
    			      .getActualTypeArguments()[0];
    	
    	return persistentClass.getSimpleName() + "Set";
    }
    
}
