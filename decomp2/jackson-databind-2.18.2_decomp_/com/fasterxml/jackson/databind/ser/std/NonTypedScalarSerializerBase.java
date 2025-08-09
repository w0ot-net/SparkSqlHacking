package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

/** @deprecated */
@Deprecated
public abstract class NonTypedScalarSerializerBase extends StdScalarSerializer {
   protected NonTypedScalarSerializerBase(Class t) {
      super(t);
   }

   protected NonTypedScalarSerializerBase(Class t, boolean bogus) {
      super(t, bogus);
   }

   public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      this.serialize(value, gen, provider);
   }
}
