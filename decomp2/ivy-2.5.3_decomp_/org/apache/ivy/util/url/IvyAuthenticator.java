package org.apache.ivy.util.url;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.Authenticator.RequestorType;
import org.apache.ivy.util.Credentials;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;

public final class IvyAuthenticator extends Authenticator {
   private Authenticator original;
   private static boolean securityWarningLogged = false;

   private IvyAuthenticator(Authenticator original) {
      this.original = original;
   }

   public static void install() {
      Authenticator original = getCurrentAuthenticator();
      if (!(original instanceof IvyAuthenticator)) {
         try {
            Authenticator.setDefault(new IvyAuthenticator(original));
         } catch (SecurityException var2) {
            if (!securityWarningLogged) {
               securityWarningLogged = true;
               Message.warn("Not enough permissions to set the IvyAuthenticator. HTTP(S) authentication will be disabled!");
            }
         }

      }
   }

   protected PasswordAuthentication getPasswordAuthentication() {
      PasswordAuthentication result = null;
      if (this.isProxyAuthentication()) {
         String proxyUser = System.getProperty("http.proxyUser");
         if (!StringUtils.isNullOrEmpty(proxyUser)) {
            String proxyPass = System.getProperty("http.proxyPassword", "");
            Message.debug("authenticating to proxy server with username [" + proxyUser + "]");
            result = new PasswordAuthentication(proxyUser, proxyPass.toCharArray());
         }
      } else {
         Credentials c = CredentialsStore.INSTANCE.getCredentials(this.getRequestingPrompt(), this.getRequestingHost());
         Message.debug("authentication: k='" + Credentials.buildKey(this.getRequestingPrompt(), this.getRequestingHost()) + "' c='" + c + "'");
         if (c != null) {
            String password = c.getPasswd() == null ? "" : c.getPasswd();
            result = new PasswordAuthentication(c.getUserName(), password.toCharArray());
         }
      }

      if (result == null && this.original != null) {
         Authenticator.setDefault(this.original);

         try {
            result = Authenticator.requestPasswordAuthentication(this.getRequestingHost(), this.getRequestingSite(), this.getRequestingPort(), this.getRequestingProtocol(), this.getRequestingPrompt(), this.getRequestingScheme(), this.getRequestingURL(), this.getRequestorType());
         } finally {
            Authenticator.setDefault(this);
         }
      }

      return result;
   }

   static Authenticator getCurrentAuthenticator() {
      return getJavaVersion() < 9 ? getTheAuthenticator() : getDefaultAuthenticator();
   }

   private boolean isProxyAuthentication() {
      return RequestorType.PROXY.equals(this.getRequestorType());
   }

   private static Authenticator getDefaultAuthenticator() {
      try {
         Method m = Authenticator.class.getDeclaredMethod("getDefault");
         return (Authenticator)m.invoke((Object)null);
      } catch (Throwable t) {
         handleReflectionException(t);
         return null;
      }
   }

   private static Authenticator getTheAuthenticator() {
      try {
         Field f = Authenticator.class.getDeclaredField("theAuthenticator");
         f.setAccessible(true);
         return (Authenticator)f.get((Object)null);
      } catch (Throwable t) {
         handleReflectionException(t);
         return null;
      }
   }

   private static void handleReflectionException(Throwable t) {
      Message.debug("Error occurred while getting the original authenticator: " + t.getMessage());
   }

   private static int getJavaVersion() {
      String[] version = System.getProperty("java.specification.version").split("\\.");
      int major = Integer.parseInt(version[0]);
      return major == 1 ? Integer.parseInt(version[1]) : major;
   }
}
