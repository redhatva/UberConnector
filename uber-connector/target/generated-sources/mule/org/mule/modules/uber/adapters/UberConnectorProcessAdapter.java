
package org.mule.modules.uber.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.uber.UberConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>UberConnectorProcessAdapter</code> is a wrapper around {@link UberConnector } that enables custom processing strategies.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2014-08-22T12:43:37-04:00", comments = "Build UNNAMED.1967.45d0eb0")
public class UberConnectorProcessAdapter
    extends UberConnectorLifecycleAdapter
    implements ProcessAdapter<UberConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, UberConnectorCapabilitiesAdapter> getProcessTemplate() {
        final UberConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,UberConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, UberConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, UberConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
