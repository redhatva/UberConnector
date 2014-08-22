package org.mule.modules.uber;
import java.util.List;
import java.util.*;

import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

import java.util.List;
import org.mule.common.metadata.*;
import org.mule.common.metadata.datatype.DataType;
import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import org.mule.api.annotations.Query;
import org.mule.api.annotations.param.Default;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="uber", schemaVersion="1.0", friendlyName="Uber")
public class UberConnector
{
    /**
     * Configurable
     */
    @Configurable
    @Default("https://api.uber.com")
    private String serviceUrl;
    
    @Configurable
    @Default("v1")
    private String versionId;
    
    @Configurable
    @Optional
    @Default("value")
    private String clientId;
    
    @Configurable
    @Optional
    @Default("value")
    private String serverToken;
    
    @Configurable
    @Optional
    @Default("value")
    private String secret;
    
    private UberClient client;
    
    @Start
    public void init()
    {
    	setClient(new UberClient(this));
    }
    
    @MetaDataKeyRetriever
    public List<MetaDataKey> getMetaDataKeys() throws Exception {
        return null;
    }

    @MetaDataRetriever
    public MetaData getMetaData(MetaDataKey key) throws Exception {
		return null;	
    }

    /**
     * Description for query
     *
     * {@sample.xml ../../../doc/uber-connector.xml.sample uber:query}
     *
     *  @param query The dsql query
     *  @return List of elements that match the criteria
     */
    @Processor
    public List<Object> query(@Query String query) {
        //TODO
        /*
         * MESSAGE PROCESSOR CODE GOES HERE
         */
        return null;
    }
    
    public void setServiceUrl(String serviceURL)
    {
    	this.serviceUrl = serviceURL;
    }
    
    public String getServiceUrl()
    {
    	return this.serviceUrl;
    }
    
    public void setClientId(String clientID)
    {
    	this.clientId = clientID;
    }
    
    public String getClientId()
    {
    	return this.clientId;
    }
    
    public void setServerToken(String serverToken)
    {
    	this.serverToken = serverToken;
    }
    
    public String getServerToken()
    {
    	return this.serverToken;
    }
    
    public void setSecret(String secret)
    {
    	this.secret = secret;
    }
    
    public String getSecret()
    {
    	return this.secret;
    }
    
    public void setVersionId(String versionId)
    {
    	this.versionId = versionId;
    }
    
    public String getVersionId()
    {
    	return this.versionId;
    }
    
    public UberClient getClient()
    {
    	return this.client;
    }
    
    public void setClient(UberClient client)
    {
    	this.client = client;
    }

    /**
     * Custom processor
     *
     * {@sample.xml ../../../doc/uber-connector.xml.sample uber:getProducts}
     *
     * @param map A Map of Latitude and Longitudes
     * @return String of JSON response
     */
    @Processor
    public String getProducts(@FriendlyName("Lat/Long") @Default("#[payload]") Map<String, Double> coordinates) throws UberException
    {
    	return getClient().getProducts(coordinates);
    }

    @Processor
    public String getEstimatedPrice(@FriendlyName("Lat/Long") @Default("#[payload]") Map<String, Double> coordinates) throws UberException
    {
    	return getClient().getPriceEstimate(coordinates);
    }

    @Processor
    public String getEstimatedTime(@FriendlyName("Lat/Long") @Default("#[payload]") Map<String, Double> coordinates) throws UberException
    {
    	return getClient().getTimeEstimate(coordinates);
    }
}