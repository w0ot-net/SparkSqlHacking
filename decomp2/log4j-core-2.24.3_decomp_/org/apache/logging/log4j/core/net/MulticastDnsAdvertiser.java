package org.apache.logging.log4j.core.net;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;

@Plugin(
   name = "MulticastDns",
   category = "Core",
   elementType = "advertiser",
   printObject = false
)
public class MulticastDnsAdvertiser implements Advertiser {
   protected static final Logger LOGGER = StatusLogger.getLogger();
   private static final int MAX_LENGTH = 255;
   private static final int DEFAULT_PORT = 4555;
   private static Object jmDNS = initializeJmDns();
   private static Class jmDNSClass;
   private static Class serviceInfoClass;

   public Object advertise(final Map properties) {
      Map<String, String> truncatedProperties = new HashMap();

      for(Map.Entry entry : properties.entrySet()) {
         if (((String)entry.getKey()).length() <= 255 && ((String)entry.getValue()).length() <= 255) {
            truncatedProperties.put((String)entry.getKey(), (String)entry.getValue());
         }
      }

      String protocol = (String)truncatedProperties.get("protocol");
      String zone = "._log4j._" + (protocol != null ? protocol : "tcp") + ".local.";
      String portString = (String)truncatedProperties.get("port");
      int port = Integers.parseInt(portString, 4555);
      String name = (String)truncatedProperties.get("name");
      if (jmDNS != null) {
         boolean isVersion3 = false;

         try {
            jmDNSClass.getMethod("create");
            isVersion3 = true;
         } catch (NoSuchMethodException var13) {
         }

         Object serviceInfo;
         if (isVersion3) {
            serviceInfo = buildServiceInfoVersion3(zone, port, name, truncatedProperties);
         } else {
            serviceInfo = buildServiceInfoVersion1(zone, port, name, truncatedProperties);
         }

         try {
            Method method = jmDNSClass.getMethod("registerService", serviceInfoClass);
            method.invoke(jmDNS, serviceInfo);
         } catch (InvocationTargetException | IllegalAccessException e) {
            LOGGER.warn("Unable to invoke registerService method", e);
         } catch (NoSuchMethodException e) {
            LOGGER.warn("No registerService method", e);
         }

         return serviceInfo;
      } else {
         LOGGER.warn("JMDNS not available - will not advertise ZeroConf support");
         return null;
      }
   }

   public void unadvertise(final Object serviceInfo) {
      if (jmDNS != null) {
         try {
            Method method = jmDNSClass.getMethod("unregisterService", serviceInfoClass);
            method.invoke(jmDNS, serviceInfo);
         } catch (InvocationTargetException | IllegalAccessException e) {
            LOGGER.warn("Unable to invoke unregisterService method", e);
         } catch (NoSuchMethodException e) {
            LOGGER.warn("No unregisterService method", e);
         }
      }

   }

   private static Object createJmDnsVersion1() {
      try {
         return LoaderUtil.newInstanceOf(jmDNSClass);
      } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
         LOGGER.warn("Unable to instantiate JMDNS", e);
         return null;
      }
   }

   private static Object createJmDnsVersion3() {
      try {
         Method jmDNSCreateMethod = jmDNSClass.getMethod("create");
         return jmDNSCreateMethod.invoke((Object)null, (Object[])null);
      } catch (InvocationTargetException | IllegalAccessException e) {
         LOGGER.warn("Unable to invoke create method", e);
      } catch (NoSuchMethodException e) {
         LOGGER.warn("Unable to get create method", e);
      }

      return null;
   }

   private static Object buildServiceInfoVersion1(final String zone, final int port, final String name, final Map properties) {
      Hashtable<String, String> hashtableProperties = new Hashtable(properties);

      try {
         return serviceInfoClass.getConstructor(String.class, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Hashtable.class).newInstance(zone, name, port, 0, 0, hashtableProperties);
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
         LOGGER.warn("Unable to construct ServiceInfo instance", e);
      } catch (NoSuchMethodException e) {
         LOGGER.warn("Unable to get ServiceInfo constructor", e);
      }

      return null;
   }

   private static Object buildServiceInfoVersion3(final String zone, final int port, final String name, final Map properties) {
      try {
         return serviceInfoClass.getMethod("create", String.class, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Map.class).invoke((Object)null, zone, name, port, 0, 0, properties);
      } catch (InvocationTargetException | IllegalAccessException e) {
         LOGGER.warn("Unable to invoke create method", e);
      } catch (NoSuchMethodException e) {
         LOGGER.warn("Unable to find create method", e);
      }

      return null;
   }

   private static Object initializeJmDns() {
      try {
         jmDNSClass = LoaderUtil.loadClass("javax.jmdns.JmDNS");
         serviceInfoClass = LoaderUtil.loadClass("javax.jmdns.ServiceInfo");
         boolean isVersion3 = false;

         try {
            jmDNSClass.getMethod("create");
            isVersion3 = true;
         } catch (NoSuchMethodException var2) {
         }

         return isVersion3 ? createJmDnsVersion3() : createJmDnsVersion1();
      } catch (ExceptionInInitializerError | ClassNotFoundException e) {
         LOGGER.warn("JmDNS or serviceInfo class not found", e);
         return null;
      }
   }
}
