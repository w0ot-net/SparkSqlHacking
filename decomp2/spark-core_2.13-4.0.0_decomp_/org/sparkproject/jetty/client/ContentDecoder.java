package org.sparkproject.jetty.client;

import java.nio.ByteBuffer;

public interface ContentDecoder {
   default void beforeDecoding(HttpExchange exchange) {
   }

   ByteBuffer decode(ByteBuffer var1);

   default void release(ByteBuffer decoded) {
   }

   default void afterDecoding(HttpExchange exchange) {
   }

   public abstract static class Factory {
      private final String encoding;

      protected Factory(String encoding) {
         this.encoding = encoding;
      }

      public String getEncoding() {
         return this.encoding;
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof Factory)) {
            return false;
         } else {
            Factory that = (Factory)obj;
            return this.encoding.equals(that.encoding);
         }
      }

      public int hashCode() {
         return this.encoding.hashCode();
      }

      public abstract ContentDecoder newContentDecoder();
   }
}
