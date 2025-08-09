package org.yaml.snakeyaml.introspector;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.util.PlatformFeatureDetector;

public class PropertyUtils {
   private final Map propertiesCache;
   private final Map readableProperties;
   private BeanAccess beanAccess;
   private boolean allowReadOnlyProperties;
   private boolean skipMissingProperties;
   private final PlatformFeatureDetector platformFeatureDetector;

   public PropertyUtils() {
      this(new PlatformFeatureDetector());
   }

   PropertyUtils(PlatformFeatureDetector platformFeatureDetector) {
      this.propertiesCache = new HashMap();
      this.readableProperties = new HashMap();
      this.beanAccess = BeanAccess.DEFAULT;
      this.allowReadOnlyProperties = false;
      this.skipMissingProperties = false;
      this.platformFeatureDetector = platformFeatureDetector;
      if (!platformFeatureDetector.isIntrospectionAvailable()) {
         this.beanAccess = BeanAccess.FIELD;
      }

   }

   protected Map getPropertiesMap(Class type, BeanAccess bAccess) {
      if (this.propertiesCache.containsKey(type)) {
         return (Map)this.propertiesCache.get(type);
      } else {
         Map<String, Property> properties = new LinkedHashMap();
         boolean inaccessableFieldsExist = false;
         if (bAccess == BeanAccess.FIELD) {
            for(Class<?> c = type; c != null; c = c.getSuperclass()) {
               for(Field field : c.getDeclaredFields()) {
                  int modifiers = field.getModifiers();
                  if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && !properties.containsKey(field.getName())) {
                     properties.put(field.getName(), new FieldProperty(field));
                  }
               }
            }
         } else {
            inaccessableFieldsExist = MethodProperty.addPublicFields(type, properties);
         }

         if (properties.isEmpty() && inaccessableFieldsExist) {
            throw new YAMLException("No JavaBean properties found in " + type.getName());
         } else {
            this.propertiesCache.put(type, properties);
            return properties;
         }
      }
   }

   public Set getProperties(Class type) {
      return this.getProperties(type, this.beanAccess);
   }

   public Set getProperties(Class type, BeanAccess bAccess) {
      if (this.readableProperties.containsKey(type)) {
         return (Set)this.readableProperties.get(type);
      } else {
         Set<Property> properties = this.createPropertySet(type, bAccess);
         this.readableProperties.put(type, properties);
         return properties;
      }
   }

   protected Set createPropertySet(Class type, BeanAccess bAccess) {
      Set<Property> properties = new TreeSet();

      for(Property property : this.getPropertiesMap(type, bAccess).values()) {
         if (property.isReadable() && (this.allowReadOnlyProperties || property.isWritable())) {
            properties.add(property);
         }
      }

      return properties;
   }

   public Property getProperty(Class type, String name) {
      return this.getProperty(type, name, this.beanAccess);
   }

   public Property getProperty(Class type, String name, BeanAccess bAccess) {
      Map<String, Property> properties = this.getPropertiesMap(type, bAccess);
      Property property = (Property)properties.get(name);
      if (property == null && this.skipMissingProperties) {
         property = new MissingProperty(name);
      }

      if (property == null) {
         throw new YAMLException("Unable to find property '" + name + "' on class: " + type.getName());
      } else {
         return property;
      }
   }

   public void setBeanAccess(BeanAccess beanAccess) {
      if (this.platformFeatureDetector.isRunningOnAndroid() && beanAccess != BeanAccess.FIELD) {
         throw new IllegalArgumentException("JVM is Android - only BeanAccess.FIELD is available");
      } else {
         if (this.beanAccess != beanAccess) {
            this.beanAccess = beanAccess;
            this.propertiesCache.clear();
            this.readableProperties.clear();
         }

      }
   }

   public void setAllowReadOnlyProperties(boolean allowReadOnlyProperties) {
      if (this.allowReadOnlyProperties != allowReadOnlyProperties) {
         this.allowReadOnlyProperties = allowReadOnlyProperties;
         this.readableProperties.clear();
      }

   }

   public boolean isAllowReadOnlyProperties() {
      return this.allowReadOnlyProperties;
   }

   public void setSkipMissingProperties(boolean skipMissingProperties) {
      if (this.skipMissingProperties != skipMissingProperties) {
         this.skipMissingProperties = skipMissingProperties;
         this.readableProperties.clear();
      }

   }

   public boolean isSkipMissingProperties() {
      return this.skipMissingProperties;
   }
}
