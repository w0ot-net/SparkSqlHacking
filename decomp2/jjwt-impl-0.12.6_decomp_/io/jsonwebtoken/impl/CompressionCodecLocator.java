package io.jsonwebtoken.impl;

import io.jsonwebtoken.CompressionCodecResolver;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Locator;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.io.CompressionAlgorithm;
import io.jsonwebtoken.lang.Assert;

public class CompressionCodecLocator implements Function, Locator {
   private final CompressionCodecResolver resolver;

   public CompressionCodecLocator(CompressionCodecResolver resolver) {
      this.resolver = (CompressionCodecResolver)Assert.notNull(resolver, "CompressionCodecResolver cannot be null.");
   }

   public CompressionAlgorithm apply(Header header) {
      return this.locate(header);
   }

   public CompressionAlgorithm locate(Header header) {
      return this.resolver.resolveCompressionCodec(header);
   }
}
