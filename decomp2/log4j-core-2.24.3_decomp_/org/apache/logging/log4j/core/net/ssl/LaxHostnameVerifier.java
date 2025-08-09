package org.apache.logging.log4j.core.net.ssl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public final class LaxHostnameVerifier implements HostnameVerifier {
   public static final HostnameVerifier INSTANCE = new LaxHostnameVerifier();

   private LaxHostnameVerifier() {
   }

   @SuppressFBWarnings({"WEAK_HOSTNAME_VERIFIER"})
   public boolean verify(final String s, final SSLSession sslSession) {
      return true;
   }
}
