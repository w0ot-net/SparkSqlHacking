package org.datanucleus.properties;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class PropertyStore {
   protected Map properties = new HashMap();
   protected FrequentlyAccessedProperties frequentProperties = new FrequentlyAccessedProperties();

   protected void setPropertyInternal(String name, Object value) {
      this.properties.put(name.toLowerCase(Locale.ENGLISH), value);
      this.frequentProperties.setProperty(name, value);
   }

   public Object getProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? this.properties.get(name.toLowerCase(Locale.ENGLISH)) : null;
   }

   public boolean hasProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH));
   }

   public boolean hasPropertyNotNull(String name) {
      return this.getProperty(name) != null;
   }

   public int getIntProperty(String name) {
      Object obj = this.getProperty(name);
      if (obj != null) {
         if (obj instanceof Number) {
            return ((Number)obj).intValue();
         } else if (obj instanceof String) {
            Integer intVal = Integer.valueOf((String)obj);
            this.setPropertyInternal(name, intVal);
            return intVal;
         } else {
            throw new PropertyTypeInvalidException(name, "int");
         }
      } else {
         return 0;
      }
   }

   public boolean getBooleanProperty(String name) {
      return this.getBooleanProperty(name, false);
   }

   public boolean getBooleanProperty(String name, boolean resultIfNotSet) {
      Object obj = this.getProperty(name);
      if (obj != null) {
         if (obj instanceof Boolean) {
            return (Boolean)obj;
         } else if (obj instanceof String) {
            Boolean boolVal = Boolean.valueOf((String)obj);
            this.setPropertyInternal(name, boolVal);
            return boolVal;
         } else {
            throw new PropertyTypeInvalidException(name, "boolean");
         }
      } else {
         return resultIfNotSet;
      }
   }

   public Boolean getBooleanObjectProperty(String name) {
      Object obj = this.getProperty(name);
      if (obj != null) {
         if (obj instanceof Boolean) {
            return (Boolean)obj;
         } else if (obj instanceof String) {
            Boolean boolVal = Boolean.valueOf((String)obj);
            this.setPropertyInternal(name, boolVal);
            return boolVal;
         } else {
            throw new PropertyTypeInvalidException(name, "Boolean");
         }
      } else {
         return null;
      }
   }

   public String getStringProperty(String name) {
      Object obj = this.getProperty(name);
      if (obj != null) {
         if (obj instanceof String) {
            return (String)obj;
         } else {
            throw new PropertyTypeInvalidException(name, "String");
         }
      } else {
         return null;
      }
   }

   public FrequentlyAccessedProperties getFrequentProperties() {
      return this.frequentProperties;
   }
}
