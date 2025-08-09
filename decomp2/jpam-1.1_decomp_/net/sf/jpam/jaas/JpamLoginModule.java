package net.sf.jpam.jaas;

import java.io.IOException;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import net.sf.jpam.Pam;
import net.sf.jpam.PamReturnValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JpamLoginModule implements LoginModule {
   private static final Log LOG;
   private static final String SERVICE_NAME_OPTION = "serviceName";
   private Subject subject;
   private CallbackHandler callbackHandler;
   private Map sharedState;
   private Map options;
   private Pam pam;
   // $FF: synthetic field
   static Class class$net$sf$jpam$jaas$JpamLoginModule;

   public boolean abort() throws LoginException {
      return true;
   }

   public boolean commit() throws LoginException {
      return true;
   }

   public boolean login() throws LoginException {
      this.pam = this.createPam();
      Callback[] callbacks = new Callback[2];
      String username = null;
      NameCallback nameCallback = new NameCallback("Enter Username: ");
      callbacks[0] = nameCallback;
      String credentials = null;
      PasswordCallback passwordCallback = new PasswordCallback("Enter Credentials: ", false);
      callbacks[1] = passwordCallback;

      try {
         this.callbackHandler.handle(callbacks);
      } catch (IOException e) {
         LOG.error("IOException handling login: " + ((Throwable)e).getMessage(), e);
         throw new LoginException(((Throwable)e).getMessage());
      } catch (UnsupportedCallbackException e) {
         LOG.error("UnsupportedCallbackException handling login: " + ((Throwable)e).getMessage(), e);
         throw new LoginException(((Throwable)e).getMessage());
      }

      username = nameCallback.getName();
      credentials = String.copyValueOf(passwordCallback.getPassword());
      boolean authenticated = false;
      PamReturnValue pamReturnValue = this.pam.authenticate(username, credentials);
      if (pamReturnValue.equals(PamReturnValue.PAM_SUCCESS)) {
         authenticated = true;
         return authenticated;
      } else if (pamReturnValue.equals(PamReturnValue.PAM_ACCT_EXPIRED)) {
         throw new AccountExpiredException(PamReturnValue.PAM_ACCT_EXPIRED.toString());
      } else if (pamReturnValue.equals(PamReturnValue.PAM_CRED_EXPIRED)) {
         throw new CredentialExpiredException(PamReturnValue.PAM_CRED_EXPIRED.toString());
      } else {
         throw new FailedLoginException(pamReturnValue.toString());
      }
   }

   private Pam createPam() {
      String serviceName = (String)this.options.get("serviceName");
      if (serviceName == null) {
         LOG.debug("No serviceName configured in JAAS configuration file. Using default service name of net-sf-jpam");
         serviceName = "net-sf-jpam";
      } else {
         LOG.debug("Using service name of " + serviceName + " from JAAS configuration file");
      }

      Pam pam = new Pam(serviceName);
      return pam;
   }

   public boolean logout() throws LoginException {
      return true;
   }

   public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) {
      this.subject = subject;
      this.callbackHandler = callbackHandler;
      this.sharedState = sharedState;
      this.options = options;
   }

   public Pam getPam() {
      return this.pam;
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
      LOG = LogFactory.getLog((class$net$sf$jpam$jaas$JpamLoginModule == null ? (class$net$sf$jpam$jaas$JpamLoginModule = class$("net.sf.jpam.jaas.JpamLoginModule")) : class$net$sf$jpam$jaas$JpamLoginModule).getName());
   }
}
