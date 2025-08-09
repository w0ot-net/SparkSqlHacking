package com.codahale.metrics.jvm;

import com.codahale.metrics.Gauge;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Set;
import javax.management.JMException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.QueryExp;

public class JmxAttributeGauge implements Gauge {
   private final MBeanServerConnection mBeanServerConn;
   private final ObjectName objectName;
   private final String attributeName;

   public JmxAttributeGauge(ObjectName objectName, String attributeName) {
      this(ManagementFactory.getPlatformMBeanServer(), objectName, attributeName);
   }

   public JmxAttributeGauge(MBeanServerConnection mBeanServerConn, ObjectName objectName, String attributeName) {
      this.mBeanServerConn = mBeanServerConn;
      this.objectName = objectName;
      this.attributeName = attributeName;
   }

   public Object getValue() {
      try {
         return this.mBeanServerConn.getAttribute(this.getObjectName(), this.attributeName);
      } catch (JMException | IOException var2) {
         return null;
      }
   }

   private ObjectName getObjectName() throws IOException {
      if (this.objectName.isPattern()) {
         Set<ObjectName> foundNames = this.mBeanServerConn.queryNames(this.objectName, (QueryExp)null);
         if (foundNames.size() == 1) {
            return (ObjectName)foundNames.iterator().next();
         }
      }

      return this.objectName;
   }
}
