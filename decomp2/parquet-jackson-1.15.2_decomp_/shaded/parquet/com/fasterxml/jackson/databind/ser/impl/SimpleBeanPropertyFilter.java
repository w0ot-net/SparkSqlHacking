package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.node.ObjectNode;
import shaded.parquet.com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import shaded.parquet.com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import shaded.parquet.com.fasterxml.jackson.databind.ser.PropertyFilter;
import shaded.parquet.com.fasterxml.jackson.databind.ser.PropertyWriter;

public class SimpleBeanPropertyFilter implements BeanPropertyFilter, PropertyFilter {
   protected SimpleBeanPropertyFilter() {
   }

   public static SimpleBeanPropertyFilter serializeAll() {
      return SimpleBeanPropertyFilter.SerializeExceptFilter.INCLUDE_ALL;
   }

   public static SimpleBeanPropertyFilter filterOutAll() {
      return SimpleBeanPropertyFilter.FilterExceptFilter.EXCLUDE_ALL;
   }

   /** @deprecated */
   @Deprecated
   public static SimpleBeanPropertyFilter serializeAll(Set properties) {
      return new FilterExceptFilter(properties);
   }

   public static SimpleBeanPropertyFilter filterOutAllExcept(Set properties) {
      return new FilterExceptFilter(properties);
   }

   public static SimpleBeanPropertyFilter filterOutAllExcept(String... propertyArray) {
      HashSet<String> properties = new HashSet(propertyArray.length);
      Collections.addAll(properties, propertyArray);
      return new FilterExceptFilter(properties);
   }

   public static SimpleBeanPropertyFilter serializeAllExcept(Set properties) {
      return new SerializeExceptFilter(properties);
   }

   public static SimpleBeanPropertyFilter serializeAllExcept(String... propertyArray) {
      HashSet<String> properties = new HashSet(propertyArray.length);
      Collections.addAll(properties, propertyArray);
      return new SerializeExceptFilter(properties);
   }

   public static PropertyFilter from(final BeanPropertyFilter src) {
      return new PropertyFilter() {
         public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {
            src.serializeAsField(pojo, jgen, prov, (BeanPropertyWriter)writer);
         }

         public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
            src.depositSchemaProperty((BeanPropertyWriter)writer, propertiesNode, provider);
         }

         public void depositSchemaProperty(PropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
            src.depositSchemaProperty((BeanPropertyWriter)writer, objectVisitor, provider);
         }

         public void serializeAsElement(Object elementValue, JsonGenerator jgen, SerializerProvider prov, PropertyWriter writer) throws Exception {
            throw new UnsupportedOperationException();
         }
      };
   }

   protected boolean include(BeanPropertyWriter writer) {
      return true;
   }

   protected boolean include(PropertyWriter writer) {
      return true;
   }

   protected boolean includeElement(Object elementValue) {
      return true;
   }

   /** @deprecated */
   @Deprecated
   public void serializeAsField(Object bean, JsonGenerator jgen, SerializerProvider provider, BeanPropertyWriter writer) throws Exception {
      if (this.include(writer)) {
         writer.serializeAsField(bean, jgen, provider);
      } else if (!jgen.canOmitFields()) {
         writer.serializeAsOmittedField(bean, jgen, provider);
      }

   }

   /** @deprecated */
   @Deprecated
   public void depositSchemaProperty(BeanPropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
      if (this.include(writer)) {
         writer.depositSchemaProperty(propertiesNode, provider);
      }

   }

   /** @deprecated */
   @Deprecated
   public void depositSchemaProperty(BeanPropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
      if (this.include(writer)) {
         writer.depositSchemaProperty(objectVisitor, provider);
      }

   }

   public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
      if (this.include(writer)) {
         writer.serializeAsField(pojo, jgen, provider);
      } else if (!jgen.canOmitFields()) {
         writer.serializeAsOmittedField(pojo, jgen, provider);
      }

   }

   public void serializeAsElement(Object elementValue, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
      if (this.includeElement(elementValue)) {
         writer.serializeAsElement(elementValue, jgen, provider);
      }

   }

   /** @deprecated */
   @Deprecated
   public void depositSchemaProperty(PropertyWriter writer, ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
      if (this.include(writer)) {
         writer.depositSchemaProperty(propertiesNode, provider);
      }

   }

   public void depositSchemaProperty(PropertyWriter writer, JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) throws JsonMappingException {
      if (this.include(writer)) {
         writer.depositSchemaProperty(objectVisitor, provider);
      }

   }

   public static class FilterExceptFilter extends SimpleBeanPropertyFilter implements Serializable {
      private static final long serialVersionUID = 1L;
      static final FilterExceptFilter EXCLUDE_ALL = new FilterExceptFilter(Collections.emptySet());
      protected final Set _propertiesToInclude;

      public FilterExceptFilter(Set properties) {
         this._propertiesToInclude = properties;
      }

      protected boolean include(BeanPropertyWriter writer) {
         return this._propertiesToInclude.contains(writer.getName());
      }

      protected boolean include(PropertyWriter writer) {
         return this._propertiesToInclude.contains(writer.getName());
      }
   }

   public static class SerializeExceptFilter extends SimpleBeanPropertyFilter implements Serializable {
      private static final long serialVersionUID = 1L;
      static final SerializeExceptFilter INCLUDE_ALL = new SerializeExceptFilter(Collections.emptySet());
      protected final Set _propertiesToExclude;

      public SerializeExceptFilter(Set properties) {
         this._propertiesToExclude = properties;
      }

      protected boolean include(BeanPropertyWriter writer) {
         return !this._propertiesToExclude.contains(writer.getName());
      }

      protected boolean include(PropertyWriter writer) {
         return !this._propertiesToExclude.contains(writer.getName());
      }
   }
}
