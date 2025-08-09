package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;

public interface Http2HeadersEncoder {
   SensitivityDetector NEVER_SENSITIVE = new SensitivityDetector() {
      public boolean isSensitive(CharSequence name, CharSequence value) {
         return false;
      }
   };
   SensitivityDetector ALWAYS_SENSITIVE = new SensitivityDetector() {
      public boolean isSensitive(CharSequence name, CharSequence value) {
         return true;
      }
   };

   void encodeHeaders(int var1, Http2Headers var2, ByteBuf var3) throws Http2Exception;

   Configuration configuration();

   public interface Configuration {
      void maxHeaderTableSize(long var1) throws Http2Exception;

      long maxHeaderTableSize();

      void maxHeaderListSize(long var1) throws Http2Exception;

      long maxHeaderListSize();
   }

   public interface SensitivityDetector {
      boolean isSensitive(CharSequence var1, CharSequence var2);
   }
}
