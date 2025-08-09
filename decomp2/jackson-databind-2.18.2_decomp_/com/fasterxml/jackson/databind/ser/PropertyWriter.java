package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class PropertyWriter extends ConcreteBeanPropertyBase implements Serializable {
   private static final long serialVersionUID = 1L;

   protected PropertyWriter(PropertyMetadata md) {
      super(md);
   }

   protected PropertyWriter(BeanPropertyDefinition propDef) {
      super(propDef.getMetadata());
   }

   protected PropertyWriter(PropertyWriter base) {
      super((ConcreteBeanPropertyBase)base);
   }

   public abstract String getName();

   public abstract PropertyName getFullName();

   public Annotation findAnnotation(Class acls) {
      A ann = (A)this.getAnnotation(acls);
      if (ann == null) {
         ann = (A)this.getContextAnnotation(acls);
      }

      return ann;
   }

   public abstract Annotation getAnnotation(Class var1);

   public abstract Annotation getContextAnnotation(Class var1);

   public abstract void serializeAsField(Object var1, JsonGenerator var2, SerializerProvider var3) throws Exception;

   public abstract void serializeAsOmittedField(Object var1, JsonGenerator var2, SerializerProvider var3) throws Exception;

   public abstract void serializeAsElement(Object var1, JsonGenerator var2, SerializerProvider var3) throws Exception;

   public abstract void serializeAsPlaceholder(Object var1, JsonGenerator var2, SerializerProvider var3) throws Exception;

   public abstract void depositSchemaProperty(JsonObjectFormatVisitor var1, SerializerProvider var2) throws JsonMappingException;

   /** @deprecated */
   @Deprecated
   public abstract void depositSchemaProperty(ObjectNode var1, SerializerProvider var2) throws JsonMappingException;
}
