package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import shaded.parquet.com.fasterxml.jackson.annotation.JsonInclude;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MapperConfig;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import shaded.parquet.com.fasterxml.jackson.databind.util.Annotations;

public class AttributePropertyWriter extends VirtualBeanPropertyWriter {
   private static final long serialVersionUID = 1L;
   protected final String _attrName;

   protected AttributePropertyWriter(String attrName, BeanPropertyDefinition propDef, Annotations contextAnnotations, JavaType declaredType) {
      this(attrName, propDef, contextAnnotations, declaredType, propDef.findInclusion());
   }

   protected AttributePropertyWriter(String attrName, BeanPropertyDefinition propDef, Annotations contextAnnotations, JavaType declaredType, JsonInclude.Value inclusion) {
      super(propDef, contextAnnotations, declaredType, (JsonSerializer)null, (TypeSerializer)null, (JavaType)null, inclusion, (Class[])null);
      this._attrName = attrName;
   }

   public static AttributePropertyWriter construct(String attrName, BeanPropertyDefinition propDef, Annotations contextAnnotations, JavaType declaredType) {
      return new AttributePropertyWriter(attrName, propDef, contextAnnotations, declaredType);
   }

   protected AttributePropertyWriter(AttributePropertyWriter base) {
      super(base);
      this._attrName = base._attrName;
   }

   public VirtualBeanPropertyWriter withConfig(MapperConfig config, AnnotatedClass declaringClass, BeanPropertyDefinition propDef, JavaType type) {
      throw new IllegalStateException("Should not be called on this type");
   }

   protected Object value(Object bean, JsonGenerator jgen, SerializerProvider prov) throws Exception {
      return prov.getAttribute(this._attrName);
   }
}
