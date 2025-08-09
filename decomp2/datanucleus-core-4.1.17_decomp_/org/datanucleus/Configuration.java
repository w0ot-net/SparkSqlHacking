package org.datanucleus;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.plugin.ConfigurationElement;
import org.datanucleus.properties.BooleanPropertyValidator;
import org.datanucleus.properties.FrequentlyAccessedProperties;
import org.datanucleus.properties.IntegerPropertyValidator;
import org.datanucleus.properties.PropertyStore;
import org.datanucleus.properties.PropertyValidator;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.PersistenceUtils;

public class Configuration extends PropertyStore implements Serializable {
   private static final long serialVersionUID = 1483628590934722025L;
   private NucleusContext nucCtx;
   private Map propertyMappings = new HashMap();
   private Map defaultProperties = new HashMap();
   private Map propertyValidators = new HashMap();
   private volatile Map managerOverrideableProperties;
   private FrequentlyAccessedProperties defaultFrequentProperties = new FrequentlyAccessedProperties();

   public Configuration(NucleusContext nucCtx) {
      this.nucCtx = nucCtx;
      this.frequentProperties.setDefaults(this.defaultFrequentProperties);
      nucCtx.applyDefaultProperties(this);
      ConfigurationElement[] propElements = nucCtx.getPluginManager().getConfigurationElementsForExtension("org.datanucleus.persistence_properties", (String)null, (String)null);
      if (propElements != null) {
         for(int i = 0; i < propElements.length; ++i) {
            String name = propElements[i].getAttribute("name");
            String intName = propElements[i].getAttribute("internal-name");
            String value = propElements[i].getAttribute("value");
            String datastoreString = propElements[i].getAttribute("datastore");
            String validatorName = propElements[i].getAttribute("validator");
            boolean datastore = datastoreString != null && datastoreString.equalsIgnoreCase("true");
            String mgrOverrideString = propElements[i].getAttribute("manager-overrideable");
            boolean mgrOverride = mgrOverrideString != null && mgrOverrideString.equalsIgnoreCase("true");
            this.addDefaultProperty(name, intName, value, validatorName, datastore, mgrOverride);
         }
      }

   }

   public Set getSupportedProperties() {
      return this.propertyMappings.keySet();
   }

   public Map getDatastoreProperties() {
      Map<String, Object> props = new HashMap();

      for(String name : this.properties.keySet()) {
         if (this.isPropertyForDatastore(name)) {
            props.put(name, this.properties.get(name));
         }
      }

      return props;
   }

   public void removeDatastoreProperties() {
      Iterator<String> propKeyIter = this.properties.keySet().iterator();

      while(propKeyIter.hasNext()) {
         String name = (String)propKeyIter.next();
         if (this.isPropertyForDatastore(name)) {
            propKeyIter.remove();
         }
      }

   }

   private boolean isPropertyForDatastore(String name) {
      PropertyMapping mapping = (PropertyMapping)this.propertyMappings.get(name.toLowerCase(Locale.ENGLISH));
      return mapping != null ? mapping.datastore : false;
   }

   public String getInternalNameForProperty(String name) {
      PropertyMapping mapping = (PropertyMapping)this.propertyMappings.get(name.toLowerCase(Locale.ENGLISH));
      return mapping != null && mapping.internalName != null ? mapping.internalName : name;
   }

   public Map getManagerOverrideableProperties() {
      Map<String, Object> props = this.managerOverrideableProperties;
      if (props != null) {
         return props;
      } else {
         props = new LinkedHashMap();

         for(Map.Entry entry : this.propertyMappings.entrySet()) {
            PropertyMapping mapping = (PropertyMapping)entry.getValue();
            if (mapping.managerOverride) {
               String propName = mapping.internalName != null ? mapping.internalName.toLowerCase(Locale.ENGLISH) : mapping.name.toLowerCase(Locale.ENGLISH);
               props.put(propName, this.getProperty(propName));
            } else if (mapping.internalName != null) {
               PropertyMapping intMapping = (PropertyMapping)this.propertyMappings.get(mapping.internalName.toLowerCase(Locale.ENGLISH));
               if (intMapping != null && intMapping.managerOverride) {
                  props.put(mapping.name.toLowerCase(Locale.ENGLISH), this.getProperty(mapping.internalName));
               }
            }
         }

         props = Collections.unmodifiableMap(props);
         this.managerOverrideableProperties = props;
         return props;
      }
   }

   public Set getManagedOverrideablePropertyNames() {
      Set<String> propNames = new HashSet();

      for(PropertyMapping mapping : this.propertyMappings.values()) {
         if (mapping.managerOverride) {
            propNames.add(mapping.name);
         }
      }

      return propNames;
   }

   public String getPropertyNameWithInternalPropertyName(String propName, String propPrefix) {
      if (propName == null) {
         return null;
      } else {
         for(PropertyMapping mapping : this.propertyMappings.values()) {
            if (mapping.internalName != null && mapping.internalName.toLowerCase().equals(propName.toLowerCase()) && mapping.name.startsWith(propPrefix)) {
               return mapping.name;
            }
         }

         return null;
      }
   }

   public String getCaseSensitiveNameForPropertyName(String propName) {
      if (propName == null) {
         return null;
      } else {
         for(PropertyMapping mapping : this.propertyMappings.values()) {
            if (mapping.name.toLowerCase().equals(propName.toLowerCase())) {
               return mapping.name;
            }
         }

         return propName;
      }
   }

   public void setDefaultProperties(Map props) {
      if (props != null && props.size() > 0) {
         for(Map.Entry entry : props.entrySet()) {
            PropertyMapping mapping = (PropertyMapping)this.propertyMappings.get(((String)entry.getKey()).toLowerCase(Locale.ENGLISH));
            Object propValue = entry.getValue();
            if (mapping != null && mapping.validatorName != null && propValue instanceof String) {
               propValue = this.getValueForPropertyWithValidator((String)propValue, mapping.validatorName);
            }

            this.defaultProperties.put(((String)entry.getKey()).toLowerCase(Locale.ENGLISH), propValue);
            this.defaultFrequentProperties.setProperty((String)entry.getKey(), propValue);
         }
      }

   }

   public void addDefaultBooleanProperty(String name, String internalName, Boolean value, boolean datastore, boolean managerOverrideable) {
      this.addDefaultProperty(name, internalName, value != null ? "" + value : null, BooleanPropertyValidator.class.getName(), datastore, managerOverrideable);
   }

   public void addDefaultIntegerProperty(String name, String internalName, Integer value, boolean datastore, boolean managerOverrideable) {
      this.addDefaultProperty(name, internalName, value != null ? "" + value : null, IntegerPropertyValidator.class.getName(), datastore, managerOverrideable);
   }

   public void addDefaultProperty(String name, String internalName, String value, String validatorName, boolean datastore, boolean managerOverrideable) {
      this.managerOverrideableProperties = null;
      this.propertyMappings.put(name.toLowerCase(Locale.ENGLISH), new PropertyMapping(name, internalName, validatorName, datastore, managerOverrideable));
      String storedName = internalName != null ? internalName.toLowerCase(Locale.ENGLISH) : name.toLowerCase(Locale.ENGLISH);
      if (!this.defaultProperties.containsKey(storedName)) {
         Object propValue = System.getProperty(name);
         if (propValue == null) {
            propValue = value;
         }

         if (propValue != null) {
            if (validatorName != null) {
               propValue = this.getValueForPropertyWithValidator(value, validatorName);
            }

            this.defaultProperties.put(storedName, propValue);
            this.defaultFrequentProperties.setProperty(storedName, propValue);
         }
      }

   }

   protected Object getValueForPropertyWithValidator(String value, String validatorName) {
      if (validatorName.equals(BooleanPropertyValidator.class.getName())) {
         return Boolean.valueOf(value);
      } else {
         return validatorName.equals(IntegerPropertyValidator.class.getName()) ? Integer.valueOf(value) : value;
      }
   }

   public boolean hasProperty(String name) {
      if (this.properties.containsKey(name.toLowerCase(Locale.ENGLISH))) {
         return true;
      } else {
         return this.defaultProperties.containsKey(name.toLowerCase(Locale.ENGLISH));
      }
   }

   public Object getProperty(String name) {
      return this.properties.containsKey(name.toLowerCase(Locale.ENGLISH)) ? super.getProperty(name) : this.defaultProperties.get(name.toLowerCase(Locale.ENGLISH));
   }

   public synchronized void setPropertiesUsingFile(String filename) {
      if (filename != null) {
         Properties props = null;

         try {
            props = PersistenceUtils.setPropertiesUsingFile(filename);
            this.setPropertyInternal("datanucleus.propertiesFile", filename);
         } catch (NucleusUserException nue) {
            this.properties.remove("datanucleus.propertiesFile");
            throw nue;
         }

         if (props != null && !props.isEmpty()) {
            this.setPersistenceProperties(props);
         }

      }
   }

   public Map getPersistencePropertiesDefaults() {
      return Collections.unmodifiableMap(this.defaultProperties);
   }

   public Map getPersistenceProperties() {
      return Collections.unmodifiableMap(this.properties);
   }

   public Set getPropertyNamesWithPrefix(String prefix) {
      Set<String> propNames = null;

      for(String name : this.properties.keySet()) {
         if (name.startsWith(prefix.toLowerCase(Locale.ENGLISH))) {
            if (propNames == null) {
               propNames = new HashSet();
            }

            propNames.add(name);
         }
      }

      return propNames;
   }

   public void setPersistenceProperties(Map props) {
      for(Map.Entry entry : props.entrySet()) {
         Object keyObj = entry.getKey();
         if (keyObj instanceof String) {
            String key = (String)keyObj;
            this.setProperty(key, entry.getValue());
         }
      }

   }

   public void setProperty(String name, Object value) {
      if (name != null) {
         String propertyName = name.trim();
         PropertyMapping mapping = (PropertyMapping)this.propertyMappings.get(propertyName.toLowerCase(Locale.ENGLISH));
         if (mapping != null) {
            if (mapping.validatorName != null) {
               this.validatePropertyValue(mapping.internalName != null ? mapping.internalName : propertyName, value, mapping.validatorName);
               if (value != null && value instanceof String) {
                  value = this.getValueForPropertyWithValidator((String)value, mapping.validatorName);
               }
            }

            if (mapping.internalName != null) {
               this.setPropertyInternal(mapping.internalName, value);
            } else {
               this.setPropertyInternal(mapping.name, value);
            }

            if (propertyName.equals("datanucleus.propertiesFile")) {
               this.setPropertiesUsingFile((String)value);
            }
         } else {
            this.setPropertyInternal(propertyName, value);
            if (this.propertyMappings.size() > 0) {
               NucleusLogger.PERSISTENCE.info(Localiser.msg("008015", propertyName));
            }
         }
      }

   }

   public void validatePropertyValue(String name, Object value) {
      String validatorName = null;
      PropertyMapping mapping = (PropertyMapping)this.propertyMappings.get(name.toLowerCase(Locale.ENGLISH));
      if (mapping != null) {
         validatorName = mapping.validatorName;
      }

      if (validatorName != null) {
         this.validatePropertyValue(name, value, validatorName);
      }

   }

   private void validatePropertyValue(String name, Object value, String validatorName) {
      if (validatorName != null) {
         PropertyValidator validator = (PropertyValidator)this.propertyValidators.get(validatorName);
         if (validator == null) {
            try {
               validator = (PropertyValidator)this.nucCtx.getPluginManager().createExecutableExtension("org.datanucleus.persistence_properties", (String)"name", (String)name, "validator", (Class[])null, (Object[])null);
               if (validator == null) {
                  Class validatorCls = this.nucCtx.getClassLoaderResolver(this.getClass().getClassLoader()).classForName(validatorName);
                  validator = (PropertyValidator)validatorCls.newInstance();
               }

               if (validator != null) {
                  this.propertyValidators.put(validatorName, validator);
               }
            } catch (Exception e) {
               NucleusLogger.PERSISTENCE.warn("Error creating validator of type " + validatorName, e);
            }
         }

         if (validator != null) {
            boolean validated = validator.validate(name, value);
            if (!validated) {
               throw new IllegalArgumentException(Localiser.msg("008012", name, value));
            }
         }

      }
   }

   public synchronized boolean equals(Object obj) {
      if (obj != null && obj instanceof Configuration) {
         if (obj == this) {
            return true;
         } else {
            Configuration config = (Configuration)obj;
            if (this.properties == null) {
               if (config.properties != null) {
                  return false;
               }
            } else if (!this.properties.equals(config.properties)) {
               return false;
            }

            if (this.defaultProperties == null) {
               if (config.defaultProperties != null) {
                  return false;
               }
            } else if (!this.defaultProperties.equals(config.defaultProperties)) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (this.properties != null ? this.properties.hashCode() : 0) ^ (this.defaultProperties != null ? this.defaultProperties.hashCode() : 0);
   }

   static class PropertyMapping implements Serializable {
      private static final long serialVersionUID = 9004376979051886506L;
      String name;
      String internalName;
      String validatorName;
      boolean datastore;
      boolean managerOverride;

      public PropertyMapping(String name, String intName, String validator, boolean datastore, boolean managerOverride) {
         this.name = name;
         this.internalName = intName;
         this.validatorName = validator;
         this.datastore = datastore;
         this.managerOverride = managerOverride;
      }
   }
}
