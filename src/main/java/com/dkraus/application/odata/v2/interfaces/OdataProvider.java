package com.dkraus.application.odata.v2.interfaces;

import java.lang.reflect.ParameterizedType;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
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
    
    protected int getIntegerKeyValue(KeyPredicate key) throws ODataException {
		EdmProperty property = key.getProperty();
		EdmSimpleType type = (EdmSimpleType) property.getType();
		return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), Integer.class);
	}
    
}
