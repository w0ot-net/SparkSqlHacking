package org.apache.logging.log4j.core.appender;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TlsSyslogFrame {
   private final String message;
   private final int byteLength;

   public TlsSyslogFrame(final String message) {
      this.message = message;
      byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
      this.byteLength = messageBytes.length;
   }

   public String getMessage() {
      return this.message;
   }

   public String toString() {
      return Integer.toString(this.byteLength) + ' ' + this.message;
   }

   public int hashCode() {
      return 31 + Objects.hashCode(this.message);
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof TlsSyslogFrame)) {
         return false;
      } else {
         TlsSyslogFrame other = (TlsSyslogFrame)obj;
         return Objects.equals(this.message, other.message);
      }
   }
}
