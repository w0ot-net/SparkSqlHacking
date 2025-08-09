package org.glassfish.jersey.message.internal;

import java.util.Collection;
import org.glassfish.jersey.internal.PropertiesDelegate;

public final class TracingAwarePropertiesDelegate implements PropertiesDelegate {
   private final PropertiesDelegate propertiesDelegate;
   private TracingLogger tracingLogger;

   public TracingAwarePropertiesDelegate(PropertiesDelegate propertiesDelegate) {
      this.propertiesDelegate = propertiesDelegate;
   }

   public void removeProperty(String name) {
      if (TracingLogger.PROPERTY_NAME.equals(name)) {
         this.tracingLogger = null;
      }

      this.propertiesDelegate.removeProperty(name);
   }

   public void setProperty(String name, Object object) {
      if (TracingLogger.PROPERTY_NAME.equals(name)) {
         this.tracingLogger = (TracingLogger)object;
      }

      this.propertiesDelegate.setProperty(name, object);
   }

   public Object getProperty(String name) {
      return this.tracingLogger != null && TracingLogger.PROPERTY_NAME.equals(name) ? this.tracingLogger : this.propertiesDelegate.getProperty(name);
   }

   public Collection getPropertyNames() {
      return this.propertiesDelegate.getPropertyNames();
   }
}
