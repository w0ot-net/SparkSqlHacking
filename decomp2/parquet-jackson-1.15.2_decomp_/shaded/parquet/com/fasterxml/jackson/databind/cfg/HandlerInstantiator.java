package shaded.parquet.com.fasterxml.jackson.databind.cfg;

import shaded.parquet.com.fasterxml.jackson.annotation.ObjectIdGenerator;
import shaded.parquet.com.fasterxml.jackson.annotation.ObjectIdResolver;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.Annotated;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import shaded.parquet.com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import shaded.parquet.com.fasterxml.jackson.databind.util.Converter;

public abstract class HandlerInstantiator {
   public abstract JsonDeserializer deserializerInstance(DeserializationConfig var1, Annotated var2, Class var3);

   public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig var1, Annotated var2, Class var3);

   public abstract JsonSerializer serializerInstance(SerializationConfig var1, Annotated var2, Class var3);

   public abstract TypeResolverBuilder typeResolverBuilderInstance(MapperConfig var1, Annotated var2, Class var3);

   public abstract TypeIdResolver typeIdResolverInstance(MapperConfig var1, Annotated var2, Class var3);

   public ValueInstantiator valueInstantiatorInstance(MapperConfig config, Annotated annotated, Class resolverClass) {
      return null;
   }

   public ObjectIdGenerator objectIdGeneratorInstance(MapperConfig config, Annotated annotated, Class implClass) {
      return null;
   }

   public ObjectIdResolver resolverIdGeneratorInstance(MapperConfig config, Annotated annotated, Class implClass) {
      return null;
   }

   public PropertyNamingStrategy namingStrategyInstance(MapperConfig config, Annotated annotated, Class implClass) {
      return null;
   }

   public Converter converterInstance(MapperConfig config, Annotated annotated, Class implClass) {
      return null;
   }

   public VirtualBeanPropertyWriter virtualPropertyWriterInstance(MapperConfig config, Class implClass) {
      return null;
   }

   public Object includeFilterInstance(SerializationConfig config, BeanPropertyDefinition forProperty, Class filterClass) {
      return null;
   }
}
