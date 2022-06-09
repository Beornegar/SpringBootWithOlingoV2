package com.dkraus.application.odata.service.interfaces;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

public abstract class OdataProvider extends ODataSingleProcessor{
//public abstract class OdataProvider extends ODataSingleProcessor{    
    public abstract String getName();

    public abstract ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType, EdmEntitySet entitySet) throws ODataException;

    public abstract ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType, EdmEntitySet entitySet) throws ODataException;

}
