package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

public class UnwrappingBeanSerializer extends BeanSerializerBase implements Serializable {
   private static final long serialVersionUID = 1L;
   protected final NameTransformer _nameTransformer;

   public UnwrappingBeanSerializer(BeanSerializerBase src, NameTransformer transformer) {
      super(src, transformer);
      this._nameTransformer = transformer;
   }

   public UnwrappingBeanSerializer(UnwrappingBeanSerializer src, ObjectIdWriter objectIdWriter) {
      super(src, (ObjectIdWriter)objectIdWriter);
      this._nameTransformer = src._nameTransformer;
   }

   public UnwrappingBeanSerializer(UnwrappingBeanSerializer src, ObjectIdWriter objectIdWriter, Object filterId) {
      super(src, (ObjectIdWriter)objectIdWriter, (Object)filterId);
      this._nameTransformer = src._nameTransformer;
   }

   protected UnwrappingBeanSerializer(UnwrappingBeanSerializer src, Set toIgnore) {
      this(src, (Set)toIgnore, (Set)null);
   }

   protected UnwrappingBeanSerializer(UnwrappingBeanSerializer src, Set toIgnore, Set toInclude) {
      super(src, (Set)toIgnore, (Set)toInclude);
      this._nameTransformer = src._nameTransformer;
   }

   protected UnwrappingBeanSerializer(UnwrappingBeanSerializer src, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
      super(src, (BeanPropertyWriter[])properties, (BeanPropertyWriter[])filteredProperties);
      this._nameTransformer = src._nameTransformer;
   }

   public JsonSerializer unwrappingSerializer(NameTransformer transformer) {
      return new UnwrappingBeanSerializer(this, transformer);
   }

   public boolean isUnwrappingSerializer() {
      return true;
   }

   public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
      return new UnwrappingBeanSerializer(this, objectIdWriter);
   }

   public BeanSerializerBase withFilterId(Object filterId) {
      return new UnwrappingBeanSerializer(this, this._objectIdWriter, filterId);
   }

   protected BeanSerializerBase withByNameInclusion(Set toIgnore, Set toInclude) {
      return new UnwrappingBeanSerializer(this, toIgnore, toInclude);
   }

   protected BeanSerializerBase withProperties(BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
      return new UnwrappingBeanSerializer(this, properties, filteredProperties);
   }

   protected BeanSerializerBase asArraySerializer() {
      return this;
   }

   public final void serialize(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.assignCurrentValue(bean);
      if (this._objectIdWriter != null) {
         this._serializeWithObjectId(bean, gen, provider, false);
      } else {
         if (this._propertyFilterId != null) {
            this.serializeFieldsFiltered(bean, gen, provider);
         } else {
            this.serializeFields(bean, gen, provider);
         }

      }
   }

   public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      if (provider.isEnabled(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS)) {
         provider.reportBadDefinition(this.handledType(), "Unwrapped property requires use of type information: cannot serialize without disabling `SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS`");
      }

      gen.assignCurrentValue(bean);
      if (this._objectIdWriter != null) {
         this._serializeWithObjectId(bean, gen, provider, typeSer);
      } else {
         if (this._propertyFilterId != null) {
            this.serializeFieldsFiltered(bean, gen, provider);
         } else {
            this.serializeFields(bean, gen, provider);
         }

      }
   }

   public String toString() {
      return "UnwrappingBeanSerializer for " + this.handledType().getName();
   }
}
