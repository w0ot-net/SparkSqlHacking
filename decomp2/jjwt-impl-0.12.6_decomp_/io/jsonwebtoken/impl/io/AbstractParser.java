package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.lang.Assert;
import java.io.InputStream;
import java.io.Reader;

public abstract class AbstractParser implements Parser {
   public final Object parse(CharSequence input) {
      Assert.hasText(input, "CharSequence cannot be null or empty.");
      return this.parse(input, 0, input.length());
   }

   public Object parse(CharSequence input, int start, int end) {
      Assert.hasText(input, "CharSequence cannot be null or empty.");
      Reader reader = new CharSequenceReader(input, start, end);
      return this.parse((Reader)reader);
   }

   public final Object parse(InputStream in) {
      Assert.notNull(in, "InputStream cannot be null.");
      Reader reader = Streams.reader(in);
      return this.parse((Reader)reader);
   }
}
