
package org.mule.modules.uber.adapters;

import javax.annotation.Generated;
import org.mule.api.devkit.capability.Capabilities;
import org.mule.api.devkit.capability.ModuleCapability;
import org.mule.modules.uber.UberConnector;


/**
 * A <code>UberConnectorCapabilitiesAdapter</code> is a wrapper around {@link UberConnector } that implements {@link org.mule.api.Capabilities} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.5.1", date = "2014-08-22T12:43:37-04:00", comments = "Build UNNAMED.1967.45d0eb0")
public class UberConnectorCapabilitiesAdapter
    extends UberConnector
    implements Capabilities
{


    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(ModuleCapability capability) {
        if (capability == ModuleCapability.LIFECYCLE_CAPABLE) {
            return true;
        }
        return false;
    }

}
