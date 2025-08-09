package io.jsonwebtoken.jackson.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.jsonwebtoken.lang.Supplier;
import java.io.IOException;

final class JacksonSupplierSerializer extends StdSerializer {
   static final JacksonSupplierSerializer INSTANCE = new JacksonSupplierSerializer();

   public JacksonSupplierSerializer() {
      super(Supplier.class, false);
   }

   public void serialize(Supplier supplier, JsonGenerator generator, SerializerProvider provider) throws IOException {
      Object value = supplier.get();
      if (value == null) {
         provider.defaultSerializeNull(generator);
      } else {
         Class<?> clazz = value.getClass();
         JsonSerializer<Object> ser = provider.findTypedValueSerializer(clazz, true, (BeanProperty)null);
         ser.serialize(value, generator, provider);
      }
   }
}
