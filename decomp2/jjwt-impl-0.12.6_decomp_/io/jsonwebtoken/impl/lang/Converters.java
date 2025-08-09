package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.impl.io.Codec;
import io.jsonwebtoken.impl.security.JwtX509StringConverter;
import java.math.BigInteger;
import java.net.URI;
import java.security.cert.X509Certificate;

public final class Converters {
   public static final Converter URI = forEncoded(URI.class, new UriStringConverter());
   public static final Converter BASE64URL_BYTES;
   public static final Converter X509_CERTIFICATE;
   public static final Converter BIGINT_UBYTES;
   public static final Converter BIGINT;

   private Converters() {
   }

   public static Converter forType(Class clazz) {
      return new RequiredTypeConverter(clazz);
   }

   public static Converter forSet(Converter elementConverter) {
      return CollectionConverter.forSet(elementConverter);
   }

   public static Converter forList(Converter elementConverter) {
      return CollectionConverter.forList(elementConverter);
   }

   public static Converter forEncoded(Class elementType, Converter elementConverter) {
      return new EncodedObjectConverter(elementType, elementConverter);
   }

   public static Converter compound(Converter aConv, Converter bConv) {
      return new CompoundConverter(aConv, bConv);
   }

   static {
      BASE64URL_BYTES = forEncoded(byte[].class, Codec.BASE64URL);
      X509_CERTIFICATE = forEncoded(X509Certificate.class, JwtX509StringConverter.INSTANCE);
      BIGINT_UBYTES = new BigIntegerUBytesConverter();
      BIGINT = forEncoded(BigInteger.class, compound(BIGINT_UBYTES, Codec.BASE64URL));
   }
}
