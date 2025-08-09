package org.glassfish.jersey.internal.config;

import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Feature;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.spi.ExternalConfigurationModel;

public class SystemPropertiesConfigurationModel implements ExternalConfigurationModel {
   private static final Logger LOGGER = Logger.getLogger(SystemPropertiesConfigurationModel.class.getName());
   private static final Map converters = new HashMap();
   private final Map properties = new HashMap();
   private final AtomicBoolean gotProperties = new AtomicBoolean(false);
   private final List propertyClassNames;

   public SystemPropertiesConfigurationModel(List propertyClassNames) {
      this.propertyClassNames = propertyClassNames;
   }

   protected List getPropertyClassNames() {
      return this.propertyClassNames;
   }

   public Object as(String name, Class clazz) {
      if (converters.get(clazz) == null) {
         throw new IllegalArgumentException("Unsupported class type");
      } else {
         return name != null && clazz != null && this.hasProperty(name) ? clazz.cast(((Function)converters.get(clazz)).apply(getSystemProperty(name))) : null;
      }
   }

   public Optional getOptionalProperty(String name, Class clazz) {
      return Optional.of(this.as(name, clazz));
   }

   public ExternalConfigurationModel mergeProperties(Map inputProperties) {
      inputProperties.forEach((k, v) -> this.properties.put(k, v));
      return this;
   }

   public Void getConfig() {
      return null;
   }

   public boolean isProperty(String name) {
      String property = getSystemProperty(name);
      return property != null && ("0".equals(property) || "1".equals(property) || "true".equalsIgnoreCase(property) || "false".equalsIgnoreCase(property));
   }

   public RuntimeType getRuntimeType() {
      return null;
   }

   public Map getProperties() {
      Boolean allowSystemPropertiesProvider = (Boolean)this.as("jersey.config.allowSystemPropertiesProvider", Boolean.class);
      if (!Boolean.TRUE.equals(allowSystemPropertiesProvider)) {
         LOGGER.finer(LocalizationMessages.WARNING_PROPERTIES());
         return this.properties;
      } else {
         if (this.gotProperties.compareAndSet(false, true)) {
            try {
               ((Properties)AccessController.doPrivileged(PropertiesHelper.getSystemProperties())).forEach((k, v) -> this.properties.put(String.valueOf(k), v));
            } catch (SecurityException var3) {
               LOGGER.warning(LocalizationMessages.SYSTEM_PROPERTIES_WARNING());
               return this.getExpectedSystemProperties();
            }
         }

         return this.properties;
      }
   }

   private Map getExpectedSystemProperties() {
      Map<String, Object> result = new HashMap();

      for(String propertyClass : this.getPropertyClassNames()) {
         mapFieldsToProperties(result, (Class)AccessController.doPrivileged(ReflectionHelper.classForNamePA(propertyClass)));
      }

      return result;
   }

   private static void mapFieldsToProperties(Map properties, Class clazz) {
      if (clazz != null) {
         Field[] fields = (Field[])AccessController.doPrivileged(ReflectionHelper.getDeclaredFieldsPA(clazz));

         for(Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType().isAssignableFrom(String.class)) {
               String propertyValue = getPropertyNameByField(field);
               if (propertyValue != null) {
                  String value = getSystemProperty(propertyValue);
                  if (value != null) {
                     properties.put(propertyValue, value);
                  }
               }
            }
         }

      }
   }

   private static String getPropertyNameByField(Field field) {
      return (String)AccessController.doPrivileged(() -> {
         try {
            return (String)field.get((Object)null);
         } catch (IllegalAccessException e) {
            LOGGER.warning(e.getLocalizedMessage());
            return null;
         }
      });
   }

   private static String getSystemProperty(String name) {
      return (String)AccessController.doPrivileged(PropertiesHelper.getSystemProperty(name));
   }

   public Object getProperty(String name) {
      return getSystemProperty(name);
   }

   public Collection getPropertyNames() {
      return ((Properties)AccessController.doPrivileged(PropertiesHelper.getSystemProperties())).stringPropertyNames();
   }

   public boolean isEnabled(Feature feature) {
      return false;
   }

   public boolean isEnabled(Class featureClass) {
      return false;
   }

   public boolean isRegistered(Object component) {
      return false;
   }

   public boolean isRegistered(Class componentClass) {
      return false;
   }

   public Map getContracts(Class componentClass) {
      return null;
   }

   public Set getClasses() {
      return null;
   }

   public Set getInstances() {
      return null;
   }

   private boolean hasProperty(String name) {
      return this.getProperty(name) != null;
   }

   static {
      converters.put(String.class, (Function)(s) -> s);
      converters.put(Integer.class, (Function)(s) -> Integer.valueOf(s));
      converters.put(Long.class, (Function)(s) -> Long.parseLong(s));
      converters.put(Boolean.class, (Function)(s) -> s.equalsIgnoreCase("1") ? true : Boolean.parseBoolean(s));
   }
}
