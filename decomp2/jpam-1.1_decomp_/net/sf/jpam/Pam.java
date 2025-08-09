package net.sf.jpam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Pam {
   private static final Log LOG;
   private static final String JPAM_SHARED_LIBRARY_NAME = "jpam";
   private String serviceName;
   public static final String DEFAULT_SERVICE_NAME = "net-sf-jpam";
   // $FF: synthetic field
   static Class class$net$sf$jpam$Pam;

   public Pam() {
      this("net-sf-jpam");
   }

   public Pam(String serviceName) throws NullPointerException, IllegalArgumentException {
      if (serviceName == null) {
         throw new NullPointerException("Service name is null");
      } else if (serviceName.length() == 0) {
         throw new IllegalArgumentException("Service name is empty");
      } else {
         this.serviceName = serviceName;
      }
   }

   native boolean isSharedLibraryWorking();

   private void callback() {
   }

   public boolean authenticateSuccessful(String username, String credentials) {
      PamReturnValue success = PamReturnValue.PAM_SUCCESS;
      PamReturnValue actual = this.authenticate(username, credentials);
      return actual.equals(success);
   }

   public PamReturnValue authenticate(String username, String credentials) throws NullPointerException {
      boolean debug = LOG.isDebugEnabled();
      LOG.debug("Debug mode active.");
      if (this.serviceName == null) {
         throw new NullPointerException("Service name is null");
      } else if (username == null) {
         throw new NullPointerException("User name is null");
      } else if (credentials == null) {
         throw new NullPointerException("Credentials are null");
      } else {
         synchronized(class$net$sf$jpam$Pam == null ? (class$net$sf$jpam$Pam = class$("net.sf.jpam.Pam")) : class$net$sf$jpam$Pam) {
            PamReturnValue pamReturnValue = PamReturnValue.fromId(this.authenticate(this.serviceName, username, credentials, debug));
            return pamReturnValue;
         }
      }
   }

   public static void main(String[] args) {
      Pam pam = new Pam();
      PamReturnValue pamReturnValue = pam.authenticate(args[0], args[1]);
      System.out.println("Response: " + pamReturnValue);
   }

   private native int authenticate(String var1, String var2, String var3, boolean var4);

   public static String getLibraryName() {
      return System.mapLibraryName("jpam");
   }

   public String getServiceName() {
      return this.serviceName;
   }

   // $FF: synthetic method
   static Class class$(String x0) {
      try {
         return Class.forName(x0);
      } catch (ClassNotFoundException x1) {
         throw new NoClassDefFoundError(((Throwable)x1).getMessage());
      }
   }

   static {
      LOG = LogFactory.getLog((class$net$sf$jpam$Pam == null ? (class$net$sf$jpam$Pam = class$("net.sf.jpam.Pam")) : class$net$sf$jpam$Pam).getName());
      System.loadLibrary("jpam");
   }
}
