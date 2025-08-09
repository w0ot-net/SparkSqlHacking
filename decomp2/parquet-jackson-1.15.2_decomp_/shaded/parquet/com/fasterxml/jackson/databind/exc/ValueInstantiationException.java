package shaded.parquet.com.fasterxml.jackson.databind.exc;

import java.io.Closeable;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;

public class ValueInstantiationException extends JsonMappingException {
   protected final JavaType _type;

   protected ValueInstantiationException(JsonParser p, String msg, JavaType type, Throwable cause) {
      super((Closeable)p, (String)msg, (Throwable)cause);
      this._type = type;
   }

   protected ValueInstantiationException(JsonParser p, String msg, JavaType type) {
      super((Closeable)p, (String)msg);
      this._type = type;
   }

   public static ValueInstantiationException from(JsonParser p, String msg, JavaType type) {
      return new ValueInstantiationException(p, msg, type);
   }

   public static ValueInstantiationException from(JsonParser p, String msg, JavaType type, Throwable cause) {
      return new ValueInstantiationException(p, msg, type, cause);
   }

   public JavaType getType() {
      return this._type;
   }
}
