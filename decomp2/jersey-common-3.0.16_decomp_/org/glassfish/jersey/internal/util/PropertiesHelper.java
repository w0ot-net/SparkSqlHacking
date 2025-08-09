package org.glassfish.jersey.internal.util;

import jakarta.ws.rs.RuntimeType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.internal.LocalizationMessages;

public final class PropertiesHelper {
   private static final Logger LOGGER = Logger.getLogger(PropertiesHelper.class.getName());
   private static final boolean METAINF_SERVICES_LOOKUP_DISABLE_DEFAULT = false;
   private static final boolean JAXRS_SERVICE_LOADING_ENABLE_DEFAULT = true;
   private static final String RUNTIME_SERVER_LOWER;
   private static final String RUNTIME_CLIENT_LOWER;

   public static PrivilegedAction getSystemProperties() {
      return new PrivilegedAction() {
         public Properties run() {
            return System.getProperties();
         }
      };
   }

   public static PrivilegedAction getSystemProperty(final String name) {
      return new PrivilegedAction() {
         public String run() {
            return System.getProperty(name);
         }
      };
   }

   public static PrivilegedAction getSystemProperty(final String name, final String def) {
      return new PrivilegedAction() {
         public String run() {
            return System.getProperty(name, def);
         }
      };
   }

   public static Object getValue(Map properties, String key, Object defaultValue, Map legacyMap) {
      return getValue(properties, (RuntimeType)null, (String)key, (Object)defaultValue, legacyMap);
   }

   public static Object getValue(Map properties, RuntimeType runtimeType, String key, Object defaultValue, Map legacyMap) {
      return getValue(properties, runtimeType, key, defaultValue, defaultValue.getClass(), legacyMap);
   }

   public static Object getValue(Map properties, String key, Object defaultValue, Class type, Map legacyMap) {
      return getValue(properties, (RuntimeType)null, key, defaultValue, type, legacyMap);
   }

   public static Object getValue(Map properties, RuntimeType runtimeType, String key, Object defaultValue, Class type, Map legacyMap) {
      T value = (T)getValue(properties, runtimeType, key, type, legacyMap);
      if (value == null) {
         value = defaultValue;
      }

      return value;
   }

   public static Object getValue(Map properties, String key, Class type, Map legacyMap) {
      return getValue(properties, (RuntimeType)null, (String)key, (Class)type, legacyMap);
   }

   public static Object getValue(Map properties, RuntimeType runtimeType, String key, Class type, Map legacyMap) {
      Object value = null;
      if (runtimeType != null) {
         String runtimeAwareKey = getPropertyNameForRuntime(key, runtimeType);
         if (key.equals(runtimeAwareKey)) {
            runtimeAwareKey = key + "." + toLowerCase(runtimeType);
         }

         value = properties.get(runtimeAwareKey);
      }

      if (value == null) {
         value = properties.get(key);
      }

      if (value == null) {
         value = getLegacyFallbackValue(properties, legacyMap, key);
      }

      return value == null ? null : convertValue(value, type);
   }

   public static String getPropertyNameForRuntime(String key, RuntimeType runtimeType) {
      if (runtimeType != null && key.startsWith("jersey.config")) {
         RuntimeType[] types = RuntimeType.values();

         for(RuntimeType type : types) {
            if (key.startsWith("jersey.config." + toLowerCase(type))) {
               return key;
            }
         }

         return key.replace("jersey.config", "jersey.config." + toLowerCase(runtimeType));
      } else {
         return key;
      }
   }

   private static Object getLegacyFallbackValue(Map properties, Map legacyFallbackMap, String key) {
      if (legacyFallbackMap != null && legacyFallbackMap.containsKey(key)) {
         String fallbackKey = (String)legacyFallbackMap.get(key);
         Object value = properties.get(fallbackKey);
         if (value != null && LOGGER.isLoggable(Level.CONFIG)) {
            LOGGER.config(LocalizationMessages.PROPERTIES_HELPER_DEPRECATED_PROPERTY_NAME(fallbackKey, key));
         }

         return value;
      } else {
         return null;
      }
   }

   public static Object convertValue(Object value, Class type) {
      if ((type.equals(Integer.class) || type.equals(Integer.TYPE)) && Number.class.isInstance(value)) {
         Integer number2Int = ((Number)value).intValue();
         return number2Int;
      } else if ((type.equals(Long.class) || type.equals(Long.TYPE)) && Number.class.isInstance(value)) {
         Long number2Long = ((Number)value).longValue();
         return number2Long;
      } else if (type.isInstance(value)) {
         return type.cast(value);
      } else {
         Constructor constructor = (Constructor)AccessController.doPrivileged(ReflectionHelper.getStringConstructorPA(type));
         if (constructor != null) {
            try {
               return type.cast(constructor.newInstance(value));
            } catch (Exception var6) {
            }
         }

         Method valueOf = (Method)AccessController.doPrivileged(ReflectionHelper.getValueOfStringMethodPA(type));
         if (valueOf != null) {
            try {
               return type.cast(valueOf.invoke((Object)null, value));
            } catch (Exception var5) {
            }
         }

         if (LOGGER.isLoggable(Level.WARNING)) {
            LOGGER.warning(LocalizationMessages.PROPERTIES_HELPER_GET_VALUE_NO_TRANSFORM(String.valueOf(value), value.getClass().getName(), type.getName()));
         }

         return null;
      }
   }

   public static boolean isMetaInfServicesEnabled(Map properties, RuntimeType runtimeType) {
      boolean disableMetaInfServicesLookup = false;
      if (properties != null) {
         disableMetaInfServicesLookup = (Boolean)CommonProperties.getValue(properties, runtimeType, "jersey.config.disableMetainfServicesLookup", false, Boolean.class);
      }

      return !disableMetaInfServicesLookup;
   }

   public static boolean isJaxRsServiceLoadingEnabled(Map properties) {
      boolean enableServicesLoading = true;
      if (properties != null) {
         enableServicesLoading = (Boolean)CommonProperties.getValue(properties, "jakarta.ws.rs.loadServices", (Object)true);
      }

      return enableServicesLoading;
   }

   public static boolean isProperty(Map properties, String name) {
      return properties.containsKey(name) && isProperty(properties.get(name));
   }

   public static boolean isProperty(Object value) {
      if (value instanceof Boolean) {
         return (Boolean)Boolean.class.cast(value);
      } else {
         return value != null && Boolean.parseBoolean(value.toString());
      }
   }

   public static boolean isPropertyOrNotSet(Object value) {
      if (value instanceof Boolean) {
         return (Boolean)Boolean.class.cast(value);
      } else {
         return value != null && ("".equals(value.toString()) || Boolean.parseBoolean(value.toString()));
      }
   }

   private static String toLowerCase(RuntimeType runtimeType) {
      switch (runtimeType) {
         case CLIENT:
            return RUNTIME_CLIENT_LOWER;
         default:
            return RUNTIME_SERVER_LOWER;
      }
   }

   private PropertiesHelper() {
   }

   static {
      RUNTIME_SERVER_LOWER = RuntimeType.SERVER.name().toLowerCase(Locale.ROOT);
      RUNTIME_CLIENT_LOWER = RuntimeType.CLIENT.name().toLowerCase(Locale.ROOT);
   }
}
