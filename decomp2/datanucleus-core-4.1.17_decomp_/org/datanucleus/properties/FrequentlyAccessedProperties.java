package org.datanucleus.properties;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.NucleusLogger;

public class FrequentlyAccessedProperties {
   private static Map fieldMap = new HashMap();
   private FrequentlyAccessedProperties defaults;
   private Boolean reachabilityAtCommit = null;
   private Boolean detachOnClose = null;
   private Boolean detachAllOnCommit = null;
   private String level2CacheStoreMode = null;
   private String level2CacheRetrieveMode = null;
   private Boolean serialiseRead = null;
   private Boolean optimisticTransaction = null;

   public void setDefaults(FrequentlyAccessedProperties defaults) {
      this.defaults = defaults;
   }

   public void setProperty(String property, Object value) {
      if (property != null) {
         Field f = (Field)fieldMap.get(property.toLowerCase(Locale.ENGLISH));
         if (f != null) {
            try {
               if (value == null) {
                  f.set(this, (Object)null);
               } else {
                  if (f.getType() == Boolean.class) {
                     if (value instanceof Boolean) {
                        f.set(this, value);
                     } else if (value instanceof String) {
                        Boolean boolVal = Boolean.valueOf((String)value);
                        f.set(this, boolVal);
                     }
                  } else {
                     f.set(this, String.valueOf(value));
                  }

               }
            } catch (Exception e) {
               throw new NucleusUserException("Failed to set property: " + property + "=" + value + ": " + e, e);
            }
         }
      }
   }

   public Boolean getReachabilityAtCommit() {
      return this.reachabilityAtCommit == null && this.defaults != null ? this.defaults.getReachabilityAtCommit() : this.reachabilityAtCommit;
   }

   public Boolean getDetachOnClose() {
      return this.detachOnClose == null && this.defaults != null ? this.defaults.getDetachOnClose() : this.detachOnClose;
   }

   public Boolean getDetachAllOnCommit() {
      return this.detachAllOnCommit == null && this.defaults != null ? this.defaults.getDetachAllOnCommit() : this.detachAllOnCommit;
   }

   public String getLevel2CacheStoreMode() {
      return this.level2CacheStoreMode == null && this.defaults != null ? this.defaults.getLevel2CacheStoreMode() : this.level2CacheStoreMode;
   }

   public String getLevel2CacheRetrieveMode() {
      return this.level2CacheRetrieveMode == null && this.defaults != null ? this.defaults.getLevel2CacheRetrieveMode() : this.level2CacheRetrieveMode;
   }

   public Boolean getSerialiseRead() {
      return this.serialiseRead == null && this.defaults != null ? this.defaults.getSerialiseRead() : this.serialiseRead;
   }

   public Boolean getOptimisticTransaction() {
      return this.optimisticTransaction == null && this.defaults != null ? this.defaults.getOptimisticTransaction() : this.optimisticTransaction;
   }

   private static void addField(String propertyName, String fieldName) throws NoSuchFieldException, SecurityException {
      Field f = FrequentlyAccessedProperties.class.getDeclaredField(fieldName);
      f.setAccessible(true);
      fieldMap.put(propertyName.toLowerCase(Locale.ENGLISH), f);
   }

   static {
      try {
         addField("datanucleus.persistenceByReachabilityAtCommit", "reachabilityAtCommit");
         addField("datanucleus.DetachOnClose", "detachOnClose");
         addField("datanucleus.DetachAllOnCommit", "detachAllOnCommit");
         addField("datanucleus.cache.level2.storeMode", "level2CacheStoreMode");
         addField("datanucleus.cache.level2.retrieveMode", "level2CacheRetrieveMode");
         addField("datanucleus.SerializeRead", "serialiseRead");
         addField("datanucleus.Optimistic", "optimisticTransaction");
      } catch (Exception e) {
         NucleusLogger.GENERAL.error("Failed to set up frequently accessed properties: " + e, e);
      }

   }
}
