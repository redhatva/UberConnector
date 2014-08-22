
package org.mule.modules.uber.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.mule.modules.uber.processors.GetEstimatedTimeMessageProcessor;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser.ParseDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.5.1", date = "2014-08-22T12:43:37-04:00", comments = "Build UNNAMED.1967.45d0eb0")
public class GetEstimatedTimeDefinitionParser
    extends AbstractDevkitBasedDefinitionParser
{

    private static Logger logger = LoggerFactory.getLogger(GetEstimatedTimeDefinitionParser.class);

    private BeanDefinitionBuilder getBeanDefinitionBuilder(ParserContext parserContext) {
        try {
            return BeanDefinitionBuilder.rootBeanDefinition(GetEstimatedTimeMessageProcessor.class.getName());
        } catch (NoClassDefFoundError noClassDefFoundError) {
            String muleVersion = "";
            try {
                muleVersion = MuleManifest.getProductVersion();
            } catch (Exception _x) {
                logger.error("Problem while reading mule version");
            }
            logger.error(("Cannot launch the mule app, the @Processor [get-estimated-time] within the connector [uber] is not supported in mule "+ muleVersion));
            throw new BeanDefinitionParsingException(new Problem(("Cannot launch the mule app, the @Processor [get-estimated-time] within the connector [uber] is not supported in mule "+ muleVersion), new Location(parserContext.getReaderContext().getResource()), null, noClassDefFoundError));
        }
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = getBeanDefinitionBuilder(parserContext);
        builder.addConstructorArgValue("getEstimatedTime");
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        parseMapWithDefaultAndSetProperty(element, builder, "coordinates", "coordinates", "coordinate", "#[payload]", new ParseDelegate<String>() {


            public String parse(Element element) {
                return element.getTextContent();
            }

        }
        );
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
