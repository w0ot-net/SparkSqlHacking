package io.jsonwebtoken.impl.security;

import io.jsonwebtoken.impl.io.ConvertingParser;
import io.jsonwebtoken.io.Parser;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkParserBuilder;

public class DefaultJwkParserBuilder extends AbstractJwkParserBuilder implements JwkParserBuilder {
   public Parser doBuild() {
      JwkDeserializer deserializer = new JwkDeserializer(this.deserializer);
      JwkBuilderSupplier supplier = new JwkBuilderSupplier(this.provider, this.operationPolicy);
      JwkConverter<Jwk<?>> converter = new JwkConverter(supplier);
      return new ConvertingParser(deserializer, converter);
   }
}
