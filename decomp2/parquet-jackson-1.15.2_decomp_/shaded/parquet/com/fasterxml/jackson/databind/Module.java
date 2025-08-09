package shaded.parquet.com.fasterxml.jackson.databind;

import java.util.Collection;
import java.util.Collections;
import shaded.parquet.com.fasterxml.jackson.core.JsonFactory;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.ObjectCodec;
import shaded.parquet.com.fasterxml.jackson.core.Version;
import shaded.parquet.com.fasterxml.jackson.core.Versioned;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.MutableConfigOverride;
import shaded.parquet.com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import shaded.parquet.com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import shaded.parquet.com.fasterxml.jackson.databind.deser.Deserializers;
import shaded.parquet.com.fasterxml.jackson.databind.deser.KeyDeserializers;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiators;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.NamedType;
import shaded.parquet.com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import shaded.parquet.com.fasterxml.jackson.databind.ser.Serializers;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeFactory;
import shaded.parquet.com.fasterxml.jackson.databind.type.TypeModifier;

public abstract class Module implements Versioned {
   public abstract String getModuleName();

   public abstract Version version();

   public Object getTypeId() {
      return this.getClass().getName();
   }

   public abstract void setupModule(SetupContext var1);

   public Iterable getDependencies() {
      return Collections.emptyList();
   }

   public interface SetupContext {
      Version getMapperVersion();

      ObjectCodec getOwner();

      TypeFactory getTypeFactory();

      boolean isEnabled(MapperFeature var1);

      boolean isEnabled(DeserializationFeature var1);

      boolean isEnabled(SerializationFeature var1);

      boolean isEnabled(JsonFactory.Feature var1);

      boolean isEnabled(JsonParser.Feature var1);

      boolean isEnabled(JsonGenerator.Feature var1);

      MutableConfigOverride configOverride(Class var1);

      void addDeserializers(Deserializers var1);

      void addKeyDeserializers(KeyDeserializers var1);

      void addSerializers(Serializers var1);

      void addKeySerializers(Serializers var1);

      void addBeanDeserializerModifier(BeanDeserializerModifier var1);

      void addBeanSerializerModifier(BeanSerializerModifier var1);

      void addAbstractTypeResolver(AbstractTypeResolver var1);

      void addTypeModifier(TypeModifier var1);

      void addValueInstantiators(ValueInstantiators var1);

      void setClassIntrospector(ClassIntrospector var1);

      void insertAnnotationIntrospector(AnnotationIntrospector var1);

      void appendAnnotationIntrospector(AnnotationIntrospector var1);

      void registerSubtypes(Class... var1);

      void registerSubtypes(NamedType... var1);

      void registerSubtypes(Collection var1);

      void setMixInAnnotations(Class var1, Class var2);

      void addDeserializationProblemHandler(DeserializationProblemHandler var1);

      void setNamingStrategy(PropertyNamingStrategy var1);
   }
}
