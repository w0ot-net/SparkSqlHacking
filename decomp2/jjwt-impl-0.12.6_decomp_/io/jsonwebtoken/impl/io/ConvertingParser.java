package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Converter;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.lang.Assert;
import java.io.Reader;
import java.util.Map;

public class ConvertingParser extends AbstractParser {
   private final Function deserializer;
   private final Converter converter;

   public ConvertingParser(Function deserializer, Converter converter) {
      this.deserializer = (Function)Assert.notNull(deserializer, "Deserializer function cannot be null.");
      this.converter = (Converter)Assert.notNull(converter, "Converter cannot be null.");
   }

   public final Object parse(Reader reader) {
      Assert.notNull(reader, "Reader cannot be null.");
      Map<String, ?> m = (Map)this.deserializer.apply(reader);
      return this.converter.applyFrom(m);
   }
}
