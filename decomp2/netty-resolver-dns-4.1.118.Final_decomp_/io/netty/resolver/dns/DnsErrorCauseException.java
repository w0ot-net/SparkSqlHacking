package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsResponseCode;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.ThrowableUtil;

public final class DnsErrorCauseException extends RuntimeException {
   private static final long serialVersionUID = 7485145036717494533L;
   private final DnsResponseCode code;

   private DnsErrorCauseException(String message, DnsResponseCode code) {
      super(message);
      this.code = code;
   }

   @SuppressJava6Requirement(
      reason = "uses Java 7+ Exception.<init>(String, Throwable, boolean, boolean) but is guarded by version checks"
   )
   private DnsErrorCauseException(String message, DnsResponseCode code, boolean shared) {
      super(message, (Throwable)null, false, true);
      this.code = code;

      assert shared;

   }

   public Throwable fillInStackTrace() {
      return this;
   }

   public DnsResponseCode getCode() {
      return this.code;
   }

   static DnsErrorCauseException newStatic(String message, DnsResponseCode code, Class clazz, String method) {
      DnsErrorCauseException exception;
      if (PlatformDependent.javaVersion() >= 7) {
         exception = new DnsErrorCauseException(message, code, true);
      } else {
         exception = new DnsErrorCauseException(message, code);
      }

      return (DnsErrorCauseException)ThrowableUtil.unknownStackTrace(exception, clazz, method);
   }
}
