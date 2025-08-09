package org.apache.derby.iapi.services.jmx;

import org.apache.derby.mbeans.ManagementMBean;
import org.apache.derby.shared.common.error.StandardException;

public interface ManagementService extends ManagementMBean {
   String DERBY_JMX_DOMAIN = "org.apache.derby";

   Object registerMBean(Object var1, Class var2, String var3) throws StandardException;

   void unregisterMBean(Object var1);

   String quotePropertyValue(String var1);
}
