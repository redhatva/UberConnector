package org.mule.modules.uber;

import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UberClient {
	private Client client;
	private WebResource apiResource;
	private UberConnector connector;
	private static Logger logger = Logger.getLogger(UberClient.class);
	
	public UberClient(UberConnector connector)
	{
		setConnector(connector);
		
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);		
		this.apiResource = this.client.resource(getConnector().getServiceUrl() + "/" + getConnector().getVersionId());
	}
	
	public String getPriceEstimate(Map<String, Double> coords) throws UberException
	{
		Double start_latitude = 0.00;
		Double start_longitude = 0.00;

		Double end_latitude = 0.00;
		Double end_longitude = 0.00;
		
		if (coords != null && coords.size() == 4)
		{
			for(Map.Entry<String, Double> entry : coords.entrySet())
			{
				if (entry.getKey().equalsIgnoreCase("start_latitude"))
				{
					start_latitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("start_longitude"))
				{
					start_longitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("end_latitude"))
				{
					end_latitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("end_longitude"))
				{
					end_longitude = entry.getValue();
				}
				
			}

			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("start_latitude", start_latitude.toString());
			params.add("start_longitude", start_longitude.toString());
			params.add("end_latitude", end_latitude.toString());
			params.add("end_longitude", end_longitude.toString());
			params.add("server_token",getConnector().getServerToken());
			
			WebResource webResource = getApiResource().path("estimates").path("price")
									.queryParams(params);

			return execute(webResource, "GET");
		}
		else {
			throw new UberException("Failed to supply start/end latitude and longitude as a map");
		}
	}

	public String getTimeEstimate(Map<String, Double> coords) throws UberException
	{
		Double start_latitude = 0.00;
		Double start_longitude = 0.00;

		Double end_latitude = 0.00;
		Double end_longitude = 0.00;
		
		if (coords != null && coords.size() == 4)
		{
			for(Map.Entry<String, Double> entry : coords.entrySet())
			{
				if (entry.getKey().equalsIgnoreCase("start_latitude"))
				{
					start_latitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("start_longitude"))
				{
					start_longitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("end_latitude"))
				{
					end_latitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("end_longitude"))
				{
					end_longitude = entry.getValue();
				}
				
			}

			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("start_latitude", start_latitude.toString());
			params.add("start_longitude", start_longitude.toString());
			params.add("end_latitude", end_latitude.toString());
			params.add("end_longitude", end_longitude.toString());
			params.add("server_token",getConnector().getServerToken());
			
			WebResource webResource = getApiResource().path("estimates").path("time")
									.queryParams(params);

			return execute(webResource, "GET");
		}
		else {
			throw new UberException("Failed to supply start/end latitude and longitude as a map");
		}
	}
	
	public String getProducts(Map<String, Double> coords) throws UberException
	{
		Double latitude = 0.00;
		Double longitude = 0.00;
		
		if (coords != null && coords.size() == 2)
		{
			for(Map.Entry<String, Double> entry : coords.entrySet())
			{
				if (entry.getKey().equalsIgnoreCase("latitude"))
				{
					latitude = entry.getValue();
				}
				
				if (entry.getKey().equalsIgnoreCase("longitude"))
				{
					longitude = entry.getValue();
				}
			}

			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("latitude", latitude.toString());
			params.add("longitude", longitude.toString());
			params.add("server_token",getConnector().getServerToken());
			
			WebResource webResource = getApiResource().path("products")
									.queryParams(params);

			return execute(webResource, "GET");
		}
		else {
			throw new UberException("Failed to supply latitude and longitude as a map");
		}
	}
	
	private String execute(WebResource webResource, String method) throws UberException
	{
		ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).method(method, ClientResponse.class);

		if(clientResponse.getStatus() == 200) {
			return clientResponse.getEntity(String.class);
		} else if (clientResponse.getStatus() == 401) {
			throw new UberException(String.format("Service returned 401 status - %s", 
						clientResponse.getEntity(String.class)));
		} else {
			throw new UberException(String.format("Service returned unknonw status - %s",
                    clientResponse.getStatus(), clientResponse.getEntity(String.class)));
		}
	}
	
	public void setConnector(UberConnector connector)
	{
		this.connector = connector;
	}
	
	public UberConnector getConnector()
	{
		return this.connector;
	}
	
	public WebResource getApiResource()
	{
		return this.apiResource;
	}
	
	public void setApiResource(WebResource apiResource)
	{
		this.apiResource = apiResource;
	}
}
