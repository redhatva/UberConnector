package org.mule.modules.uber;

import org.mule.api.MuleEvent;
import org.mule.construct.Flow;
import org.mule.tck.FunctionalTestCase;
import org.mule.tck.AbstractMuleTestCase;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.construct.Flow;

@SuppressWarnings("deprecation")
public class UberConnectorTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "uber-config.xml";
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testForProducts() throws Exception
    {
        Flow flow = lookupFlowConstruct("testProducts");
        MuleEvent event = AbstractMuleTestCase.getTestEvent(null);
        MuleEvent responseEvent = flow.process(event);
        //assertEquals(expect, responseEvent.getMessage().getPayload());
        assertNotNull(responseEvent.getMessage().getPayload());
        
        //runFlowAndExpect("testFlow", "Another string");
    }
    
    @SuppressWarnings("deprecation")
	@Test
    public void testForEstiamtedPrice() throws Exception
    {
        Flow flow = lookupFlowConstruct("testEstimatedPrice");
        MuleEvent event = AbstractMuleTestCase.getTestEvent(null);
        MuleEvent responseEvent = flow.process(event);
        //assertEquals(expect, responseEvent.getMessage().getPayload());
        assertNotNull(responseEvent.getMessage().getPayload());
        
        //runFlowAndExpect("testFlow", "Another string");
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testForEstiamtedTime() throws Exception
    {
        Flow flow = lookupFlowConstruct("testEstimatedTime");
        MuleEvent event = AbstractMuleTestCase.getTestEvent(null);
        MuleEvent responseEvent = flow.process(event);
        //assertEquals(expect, responseEvent.getMessage().getPayload());
        assertNotNull(responseEvent.getMessage().getPayload());
        
        //runFlowAndExpect("testFlow", "Another string");
    }
    
    protected Flow lookupFlowConstruct(String name)
    {
        return (Flow) AbstractMuleTestCase.muleContext.getRegistry().lookupFlowConstruct(name);
    }
}
