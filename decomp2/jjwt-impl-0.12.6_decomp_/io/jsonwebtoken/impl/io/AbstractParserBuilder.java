package io.jsonwebtoken.impl.io;

import io.jsonwebtoken.impl.lang.Services;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.io.ParserBuilder;
import java.security.Provider;
import java.util.Map;

public abstract class AbstractParserBuilder implements ParserBuilder {
   protected Provider provider;
   protected Deserializer deserializer;

   protected final ParserBuilder self() {
      return this;
   }

   public ParserBuilder provider(Provider provider) {
      this.provider = provider;
      return this.self();
   }

   public ParserBuilder json(Deserializer reader) {
      this.deserializer = reader;
      return this.self();
   }

   public final Parser build() {
      if (this.deserializer == null) {
         this.deserializer = (Deserializer)Services.get(Deserializer.class);
      }

      return this.doBuild();
   }

   protected abstract Parser doBuild();
}
