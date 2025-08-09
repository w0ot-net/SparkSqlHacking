package shaded.parquet.com.fasterxml.jackson.databind.ser.impl;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.ser.std.ToEmptyObjectSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.util.NativeImageUtil;

public class UnknownSerializer extends ToEmptyObjectSerializer {
   public UnknownSerializer() {
      super(Object.class);
   }

   public UnknownSerializer(Class cls) {
      super(cls);
   }

   public void serialize(Object value, JsonGenerator gen, SerializerProvider ctxt) throws IOException {
      if (ctxt.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS)) {
         this.failForEmpty(ctxt, value);
      }

      super.serialize(value, gen, ctxt);
   }

   public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider ctxt, TypeSerializer typeSer) throws IOException {
      if (ctxt.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS)) {
         this.failForEmpty(ctxt, value);
      }

      super.serializeWithType(value, gen, ctxt, typeSer);
   }

   protected void failForEmpty(SerializerProvider prov, Object value) throws JsonMappingException {
      Class<?> cl = value.getClass();
      if (NativeImageUtil.needsReflectionConfiguration(cl)) {
         prov.reportBadDefinition(this.handledType(), String.format("No serializer found for class %s and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS). This appears to be a native image, in which case you may need to configure reflection for the class that is to be serialized", cl.getName()));
      } else {
         prov.reportBadDefinition(this.handledType(), String.format("No serializer found for class %s and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)", cl.getName()));
      }

   }
}
