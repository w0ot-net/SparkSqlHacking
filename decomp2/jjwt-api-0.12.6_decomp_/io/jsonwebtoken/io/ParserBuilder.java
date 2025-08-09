package io.jsonwebtoken.io;

import io.jsonwebtoken.lang.Builder;
import java.security.Provider;

public interface ParserBuilder extends Builder {
   ParserBuilder provider(Provider var1);

   ParserBuilder json(Deserializer var1);
}
