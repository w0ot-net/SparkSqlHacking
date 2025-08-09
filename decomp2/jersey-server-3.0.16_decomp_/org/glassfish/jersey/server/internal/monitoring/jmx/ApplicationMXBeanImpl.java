package org.glassfish.jersey.server.internal.monitoring.jmx;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.monitoring.ApplicationInfo;
import org.glassfish.jersey.server.monitoring.ApplicationMXBean;

public class ApplicationMXBeanImpl implements ApplicationMXBean {
   private final String applicationName;
   private final String applicationClass;
   private final Map configurationProperties;
   private final Date startTime;
   private final Set providers = new HashSet();
   private final Set registeredClasses = new HashSet();
   private final Set registeredInstances = new HashSet();

   public ApplicationMXBeanImpl(ApplicationInfo applicationInfo, MBeanExposer mBeanExposer, String parentName) {
      for(Class provider : applicationInfo.getProviders()) {
         this.providers.add(provider.getName());
      }

      for(Class registeredClass : applicationInfo.getRegisteredClasses()) {
         this.registeredClasses.add(registeredClass.toString());
      }

      for(Object registeredInstance : applicationInfo.getRegisteredInstances()) {
         this.registeredInstances.add(registeredInstance.getClass().getName());
      }

      ResourceConfig resourceConfig = applicationInfo.getResourceConfig();
      this.applicationName = resourceConfig.getApplicationName();
      this.applicationClass = resourceConfig.getApplication().getClass().getName();
      this.configurationProperties = new HashMap();

      for(Map.Entry entry : resourceConfig.getProperties().entrySet()) {
         Object value = entry.getValue();

         String stringValue;
         try {
            stringValue = value == null ? "[null]" : value.toString();
         } catch (Exception e) {
            stringValue = LocalizationMessages.PROPERTY_VALUE_TOSTRING_THROWS_EXCEPTION(e.getClass().getName(), e.getMessage());
         }

         this.configurationProperties.put(entry.getKey(), stringValue);
      }

      this.startTime = new Date(applicationInfo.getStartTime().getTime());
      mBeanExposer.registerMBean(this, parentName + ",global=Configuration");
   }

   public String getApplicationName() {
      return this.applicationName;
   }

   public String getApplicationClass() {
      return this.applicationClass;
   }

   public Map getProperties() {
      return this.configurationProperties;
   }

   public Date getStartTime() {
      return this.startTime;
   }

   public Set getRegisteredClasses() {
      return this.registeredClasses;
   }

   public Set getRegisteredInstances() {
      return this.registeredInstances;
   }

   public Set getProviderClasses() {
      return this.providers;
   }
}
