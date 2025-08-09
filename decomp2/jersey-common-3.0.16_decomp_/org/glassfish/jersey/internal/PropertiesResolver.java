package org.glassfish.jersey.internal;

import jakarta.ws.rs.core.Configuration;
import org.glassfish.jersey.internal.util.PropertiesHelper;

public interface PropertiesResolver {
   Object resolveProperty(String var1, Class var2);

   Object resolveProperty(String var1, Object var2);

   static PropertiesResolver create(final Configuration configuration, final PropertiesDelegate delegate) {
      return new PropertiesResolver() {
         public Object resolveProperty(String name, Class type) {
            return this.resolveProperty(name, (Object)null, type);
         }

         public Object resolveProperty(String name, Object defaultValue) {
            return this.resolveProperty(name, defaultValue, defaultValue.getClass());
         }

         private Object resolveProperty(String name, Object defaultValue, Class type) {
            Object result = delegate.getProperty(name);
            if (result == null) {
               result = configuration.getProperty(name);
               if (result == null) {
                  result = defaultValue;
               }
            }

            return result == null ? null : PropertiesHelper.convertValue(result, type);
         }
      };
   }
}
