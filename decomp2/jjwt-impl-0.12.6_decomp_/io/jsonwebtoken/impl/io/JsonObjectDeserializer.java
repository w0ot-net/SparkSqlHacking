package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.lang.Assert;
import java.io.Reader;
import java.util.Map;

public class JsonObjectDeserializer implements Function {
   private static final String MALFORMED_ERROR = "Malformed %s JSON: %s";
   private static final String MALFORMED_COMPLEX_ERROR = "Malformed or excessively complex %s JSON. If experienced in a production environment, this could reflect a potential malicious %s, please investigate the source further. Cause: %s";
   private final Deserializer deserializer;
   private final String name;

   public JsonObjectDeserializer(Deserializer deserializer, String name) {
      this.deserializer = (Deserializer)Assert.notNull(deserializer, "JSON Deserializer cannot be null.");
      this.name = (String)Assert.hasText(name, "name cannot be null or empty.");
   }

   public Map apply(Reader in) {
      Assert.notNull(in, "InputStream argument cannot be null.");

      try {
         Object value = this.deserializer.deserialize(in);
         if (value == null) {
            String msg = "Deserialized data resulted in a null value; cannot create Map<String,?>";
            throw new DeserializationException(msg);
         } else if (!(value instanceof Map)) {
            String msg = "Deserialized data is not a JSON Object; cannot create Map<String,?>";
            throw new DeserializationException(msg);
         } else {
            return (Map)value;
         }
      } catch (StackOverflowError e) {
         String msg = String.format("Malformed or excessively complex %s JSON. If experienced in a production environment, this could reflect a potential malicious %s, please investigate the source further. Cause: %s", this.name, this.name, e.getMessage());
         throw new DeserializationException(msg, e);
      } catch (Throwable t) {
         throw this.malformed(t);
      }
   }

   protected RuntimeException malformed(Throwable t) {
      String msg = String.format("Malformed %s JSON: %s", this.name, t.getMessage());
      throw new MalformedJwtException(msg, t);
   }
}
