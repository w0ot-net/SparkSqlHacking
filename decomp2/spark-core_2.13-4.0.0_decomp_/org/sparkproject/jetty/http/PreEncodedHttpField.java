package org.sparkproject.jetty.http;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.TypeUtil;

public class PreEncodedHttpField extends HttpField {
   private static final Logger LOG = LoggerFactory.getLogger(PreEncodedHttpField.class);
   private static final HttpFieldPreEncoder[] __encoders;
   private final byte[][] _encodedField;

   private static int index(HttpVersion version) {
      switch (version) {
         case HTTP_1_0:
         case HTTP_1_1:
            return 0;
         case HTTP_2:
            return 1;
         case HTTP_3:
            return 2;
         default:
            return -1;
      }
   }

   public PreEncodedHttpField(HttpHeader header, String name, String value) {
      super(header, name, value);
      this._encodedField = new byte[__encoders.length][];

      for(int i = 0; i < __encoders.length; ++i) {
         if (__encoders[i] != null) {
            this._encodedField[i] = __encoders[i].getEncodedField(header, name, value);
         }
      }

   }

   public PreEncodedHttpField(HttpHeader header, String value) {
      this(header, header.asString(), value);
   }

   public PreEncodedHttpField(String name, String value) {
      this((HttpHeader)null, name, value);
   }

   public void putTo(ByteBuffer bufferInFillMode, HttpVersion version) {
      bufferInFillMode.put(this._encodedField[index(version)]);
   }

   public int getEncodedLength(HttpVersion version) {
      return this._encodedField[index(version)].length;
   }

   static {
      List<HttpFieldPreEncoder> encoders = new ArrayList();
      TypeUtil.serviceProviderStream(ServiceLoader.load(HttpFieldPreEncoder.class)).forEach((provider) -> {
         try {
            HttpFieldPreEncoder encoder = (HttpFieldPreEncoder)provider.get();
            if (index(encoder.getHttpVersion()) >= 0) {
               encoders.add(encoder);
            }
         } catch (RuntimeException | Error e) {
            LOG.debug("Unable to add HttpFieldPreEncoder", e);
         }

      });
      LOG.debug("HttpField encoders loaded: {}", encoders);
      int size = 1;

      for(HttpFieldPreEncoder e : encoders) {
         size = Math.max(size, index(e.getHttpVersion()) + 1);
      }

      __encoders = new HttpFieldPreEncoder[size];

      for(HttpFieldPreEncoder e : encoders) {
         int i = index(e.getHttpVersion());
         if (__encoders[i] == null) {
            __encoders[i] = e;
         } else {
            LOG.warn("multiple PreEncoders for {}", e.getHttpVersion());
         }
      }

      if (__encoders[0] == null) {
         __encoders[0] = new Http1FieldPreEncoder();
      }

   }
}
