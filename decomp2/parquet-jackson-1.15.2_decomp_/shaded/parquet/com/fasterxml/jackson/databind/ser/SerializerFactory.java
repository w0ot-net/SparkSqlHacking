package shaded.parquet.com.fasterxml.jackson.databind.ser;

import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class SerializerFactory {
   public abstract SerializerFactory withAdditionalSerializers(Serializers var1);

   public abstract SerializerFactory withAdditionalKeySerializers(Serializers var1);

   public abstract SerializerFactory withSerializerModifier(BeanSerializerModifier var1);

   public abstract JsonSerializer createSerializer(SerializerProvider var1, JavaType var2) throws JsonMappingException;

   public abstract TypeSerializer createTypeSerializer(SerializationConfig var1, JavaType var2) throws JsonMappingException;

   public JsonSerializer createKeySerializer(SerializerProvider prov, JavaType type, JsonSerializer defaultImpl) throws JsonMappingException {
      return this.createKeySerializer(prov.getConfig(), type, defaultImpl);
   }

   /** @deprecated */
   @Deprecated
   public abstract JsonSerializer createKeySerializer(SerializationConfig var1, JavaType var2, JsonSerializer var3) throws JsonMappingException;
}
