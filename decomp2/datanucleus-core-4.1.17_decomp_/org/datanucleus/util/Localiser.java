package org.datanucleus.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ClassConstants;

public class Localiser {
   private static Locale locale = null;
   private static boolean displayCodesInMessages = false;
   private static Map properties = new ConcurrentHashMap();
   private static Map msgFormats = new ConcurrentHashMap();

   public static void registerBundle(String bundleName, ClassLoader loader) {
      try {
         ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, loader);

         for(String key : bundle.keySet()) {
            properties.put(key, bundle.getString(key));
         }
      } catch (MissingResourceException var5) {
         NucleusLogger.GENERAL.error("ResourceBundle " + bundleName + " for locale " + locale + " was not found!");
      }

   }

   public static String msg(String messageKey) {
      return getMessage(messageKey);
   }

   public static String msg(String messageKey, long arg) {
      Object[] args = new Object[]{String.valueOf(arg)};
      return getMessage(messageKey, args);
   }

   public static String msg(String messageKey, Object... args) {
      return getMessage(messageKey, args);
   }

   private static final String getMessage(String messageKey, Object... msgArgs) {
      if (messageKey == null) {
         NucleusLogger.GENERAL.error("Attempt to retrieve resource with NULL name !");
         return null;
      } else {
         if (msgArgs != null) {
            for(int i = 0; i < msgArgs.length; ++i) {
               if (msgArgs[i] == null) {
                  msgArgs[i] = "";
               } else if (Throwable.class.isAssignableFrom(msgArgs[i].getClass())) {
                  msgArgs[i] = getStringFromException((Throwable)msgArgs[i]);
               }
            }
         }

         String stringForKey = (String)properties.get(messageKey);
         if (stringForKey == null) {
            NucleusLogger.GENERAL.error("Message \"" + messageKey + "\" doesn't exist in any registered ResourceBundle");
            return null;
         } else {
            if (displayCodesInMessages) {
               char c = messageKey.charAt(0);
               if (c >= '0' && c <= '9') {
                  stringForKey = "[DN-" + messageKey + "] " + stringForKey;
               }
            }

            if (msgArgs != null) {
               MessageFormat formatter = (MessageFormat)msgFormats.get(stringForKey);
               if (formatter == null) {
                  formatter = new MessageFormat(stringForKey);
                  msgFormats.put(stringForKey, formatter);
               }

               return formatter.format(msgArgs);
            } else {
               return stringForKey;
            }
         }
      }
   }

   private static String getStringFromException(Throwable exception) {
      StringBuilder msg = new StringBuilder();
      if (exception != null) {
         StringWriter stringWriter = new StringWriter();
         PrintWriter printWriter = new PrintWriter(stringWriter);
         exception.printStackTrace(printWriter);
         printWriter.close();

         try {
            stringWriter.close();
         } catch (Exception var5) {
         }

         msg.append(exception.getMessage());
         msg.append('\n');
         msg.append(stringWriter.toString());
         if (exception instanceof SQLException) {
            if (((SQLException)exception).getNextException() != null) {
               msg.append('\n');
               msg.append(getStringFromException(((SQLException)exception).getNextException()));
            }
         } else if (exception instanceof InvocationTargetException && ((InvocationTargetException)exception).getTargetException() != null) {
            msg.append('\n');
            msg.append(getStringFromException(((InvocationTargetException)exception).getTargetException()));
         }
      }

      return msg.toString();
   }

   static {
      String language = System.getProperty("datanucleus.localisation.language");
      if (language == null) {
         locale = Locale.getDefault();
      } else {
         locale = new Locale(language);
      }

      String messageCodes = System.getProperty("datanucleus.localisation.messageCodes");
      if (messageCodes != null) {
         displayCodesInMessages = Boolean.parseBoolean(messageCodes);
      }

      registerBundle("org.datanucleus.Localisation", ClassConstants.NUCLEUS_CONTEXT_LOADER);
   }
}
