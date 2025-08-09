package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.ConvertingParser;
import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.security.JwkSetParserBuilder;

public class DefaultJwkSetParserBuilder extends AbstractJwkParserBuilder implements JwkSetParserBuilder {
   private boolean ignoreUnsupported = true;

   public JwkSetParserBuilder ignoreUnsupported(boolean ignore) {
      this.ignoreUnsupported = ignore;
      return this;
   }

   public Parser doBuild() {
      JwkSetDeserializer deserializer = new JwkSetDeserializer(this.deserializer);
      JwkBuilderSupplier supplier = new JwkBuilderSupplier(this.provider, this.operationPolicy);
      JwkSetConverter converter = new JwkSetConverter(supplier, this.ignoreUnsupported);
      return new ConvertingParser(deserializer, converter);
   }
}
