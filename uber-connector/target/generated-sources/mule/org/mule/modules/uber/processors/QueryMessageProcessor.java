
package org.mule.modules.uber.processors;

import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultListMetaDataModel;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.common.metadata.key.property.TypeDescribingProperty;
import org.mule.common.metadata.util.MetaDataQueryFilter;
import org.mule.common.query.DsqlQuery;
import org.mule.common.query.dsql.parser.MuleDsqlParser;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;
import org.mule.modules.uber.UberConnector;
import org.mule.modules.uber.adapters.UberConnectorProcessAdapter;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * QueryMessageProcessor invokes the {@link org.mule.modules.uber.UberConnector#query(java.lang.String)} method in {@link UberConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2014-08-22T12:43:37-04:00", comments = "Build UNNAMED.1967.45d0eb0")
public class QueryMessageProcessor
    extends DevkitBasedMessageProcessor
    implements MessageProcessor, OperationMetaDataEnabled
{

    protected Object query;
    protected String _queryType;

    public QueryMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets query
     * 
     * @param value Value to set
     */
    public void setQuery(Object value) {
        this.query = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(UberConnectorProcessAdapter.class, false, event);
            final String _transformedQuery = ((String) evaluateAndTransform(getMuleContext(), event, QueryMessageProcessor.class.getDeclaredField("_queryType").getGenericType(), null, query));
            Object resultPayload;
            final ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    UberConnector connector = ((UberConnector) object);
                    String trimmedQuery = (_transformedQuery);
                    if ((trimmedQuery!= null)&&(_transformedQuery).startsWith("dsql:")) {
                        trimmedQuery = (_transformedQuery).substring(5);
                        MuleDsqlParser parser = new MuleDsqlParser();
                        DsqlQuery q = parser.parse(trimmedQuery);
                        return ((UberConnector) object).query(_transformedQuery);
                    } else {
                        return ((UberConnector) object).query(_transformedQuery);
                    }
                }

            }
            , this, event);
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        try {
            MuleDsqlParser parser = new MuleDsqlParser();
            String queryStr = ((String)(query));
            if ((queryStr!= null)&&queryStr.startsWith("dsql:")) {
                queryStr = queryStr.substring(5);
                DsqlQuery q = parser.parse(queryStr);
                Result<MetaData> result = auxOutputMetaData(null, q.getTypes().get(0).getName());
                MetaDataQueryFilter filter = new MetaDataQueryFilter(result.get(), q.getFields());
                return new DefaultResult<MetaData>(filter.doFilter());
            } else {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE));
            }
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "Failed on parsing and getting query metadata");
        }
    }

    public Result<MetaData> auxOutputMetaData(MetaData inputMetadata, String key) {
        if (((key) == null)||((key).toString() == null)) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error retrieving metadata from parameter: key at processor query at module UberConnector");
        }
        DefaultMetaDataKey metaDataKey = new DefaultMetaDataKey((key).toString(), null);
        metaDataKey.addProperty(new TypeDescribingProperty(TypeDescribingProperty.TypeScope.OUTPUT, "query"));
        Result<MetaData> genericMetaData = getGenericMetaData(metaDataKey);
        if ((Result.Status.FAILURE).equals(genericMetaData.getStatus())) {
            return genericMetaData;
        }
        return new DefaultResult<MetaData>(new DefaultMetaData(new DefaultListMetaDataModel(genericMetaData.get().getPayload())));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(UberConnector.class, true, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at UberConnector at query retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}
