package org.apache.commons.crypto.jna;

public final class OpenSslJna {
   static void debug(String format, Object... args) {
      if (Boolean.getBoolean("commons.crypto.debug")) {
         System.out.println(String.format(format, args));
      }

   }

   public static Class getCipherClass() {
      return OpenSslJnaCipher.class;
   }

   public static Class getRandomClass() {
      return OpenSslJnaCryptoRandom.class;
   }

   private static void info(String format, Object... args) {
      System.out.println(String.format(format, args));
   }

   public static Throwable initialisationError() {
      return OpenSslNativeJna.INIT_ERROR;
   }

   public static boolean isEnabled() {
      return OpenSslNativeJna.INIT_OK;
   }

   public static void main(String[] args) {
      info("isEnabled(): %s", isEnabled());
      Throwable initialisationError = initialisationError();
      info("initialisationError(): %s", initialisationError);
      if (initialisationError != null) {
         System.err.flush();
         initialisationError.printStackTrace();
      }

   }

   static String OpenSSLVersion(int type) {
      return OpenSslNativeJna.OpenSSLVersion(type);
   }
}
