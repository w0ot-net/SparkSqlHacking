package org.apache.logging.log4j.util;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.status.StatusLogger;

public final class Base64Util {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static Method encodeMethod = null;
   private static Object encoder = null;

   private Base64Util() {
   }

   /** @deprecated */
   @Deprecated
   public static String encode(final String str) {
      if (str == null) {
         return null;
      } else {
         byte[] data = str.getBytes(Charset.defaultCharset());
         if (encodeMethod != null) {
            try {
               return (String)encodeMethod.invoke(encoder, data);
            } catch (Exception ex) {
               throw new LoggingException("Unable to encode String", ex);
            }
         } else {
            throw new LoggingException("No Encoder, unable to encode string");
         }
      }
   }

   static {
      try {
         Class<?> clazz = LoaderUtil.loadClass("java.util.Base64");
         Class<?> encoderClazz = LoaderUtil.loadClass("java.util.Base64$Encoder");
         Method method = clazz.getMethod("getEncoder");
         encoder = method.invoke((Object)null);
         encodeMethod = encoderClazz.getMethod("encodeToString", byte[].class);
      } catch (Exception var4) {
         try {
            Class<?> clazz = LoaderUtil.loadClass("javax.xml.bind.DataTypeConverter");
            encodeMethod = clazz.getMethod("printBase64Binary");
         } catch (Exception ex2) {
            LOGGER.error((String)"Unable to create a Base64 Encoder", (Throwable)ex2);
         }
      }

   }
}
