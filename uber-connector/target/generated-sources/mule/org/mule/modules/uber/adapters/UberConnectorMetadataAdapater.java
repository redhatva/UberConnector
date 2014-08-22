
package org.mule.modules.uber.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.uber.UberConnector;


/**
 * A <code>UberConnectorMetadataAdapater</code> is a wrapper around {@link UberConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2014-08-22T12:43:37-04:00", comments = "Build UNNAMED.1967.45d0eb0")
public class UberConnectorMetadataAdapater
    extends UberConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "Uber";
    private final static String MODULE_VERSION = "1.0.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.5.1";
    private final static String DEVKIT_BUILD = "UNNAMED.1967.45d0eb0";
    private final static String MIN_MULE_VERSION = "3.5.0";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

    public String getMinMuleVersion() {
        return MIN_MULE_VERSION;
    }

}
