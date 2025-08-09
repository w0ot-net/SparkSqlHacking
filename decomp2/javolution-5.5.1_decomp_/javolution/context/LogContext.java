package javolution.context;

import javolution.lang.Configurable;
import javolution.text.Text;
import javolution.text.TextBuilder;
import javolution.util.StandardLog;

public abstract class LogContext extends Context {
   private static volatile LogContext _Default = new StandardLog();
   public static final Class STANDARD = StandardLog.class;
   public static final Class NULL = Null.class;
   public static final Class SYSTEM_OUT = SystemOut.class;
   public static final Class CONSOLE = Console.class;
   public static final Configurable DEFAULT;

   protected LogContext() {
   }

   public static LogContext getCurrentLogContext() {
      for(Context ctx = Context.getCurrentContext(); ctx != null; ctx = ctx.getOuter()) {
         if (ctx instanceof LogContext) {
            return (LogContext)ctx;
         }
      }

      return _Default;
   }

   public static LogContext getDefault() {
      return _Default;
   }

   public static boolean isDebugLogged() {
      return getCurrentLogContext().isLogged("debug");
   }

   public static void debug(CharSequence message) {
      getCurrentLogContext().logDebug(message);
   }

   public static void debug(Object message) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("debug")) {
         logContext.logDebug(Text.valueOf(message));
      }
   }

   public static void debug(Object... messages) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("debug")) {
         Text tmp = Text.valueOf(messages[0]);

         for(int i = 1; i < messages.length; ++i) {
            tmp = tmp.plus(messages[i]);
         }

         logContext.logDebug(tmp);
      }
   }

   public static boolean isInfoLogged() {
      return getCurrentLogContext().isLogged("info");
   }

   public static void info(CharSequence message) {
      getCurrentLogContext().logInfo(message);
   }

   public static void info(Object message) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("info")) {
         logContext.logInfo(Text.valueOf(message));
      }
   }

   public static void info(Object... messages) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("info")) {
         Text tmp = Text.valueOf(messages[0]);

         for(int i = 1; i < messages.length; ++i) {
            tmp = tmp.plus(messages[i]);
         }

         logContext.logInfo(tmp);
      }
   }

   public static boolean isWarningLogged() {
      return getCurrentLogContext().isLogged("warning");
   }

   public static void warning(CharSequence message) {
      getCurrentLogContext().logWarning(message);
   }

   public static void warning(Object message) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("warning")) {
         logContext.logWarning(Text.valueOf(message));
      }
   }

   public static void warning(Object... messages) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("warning")) {
         Text tmp = Text.valueOf(messages[0]);

         for(int i = 1; i < messages.length; ++i) {
            tmp = tmp.plus(messages[i]);
         }

         logContext.logWarning(tmp);
      }
   }

   public static boolean isErrorLogged() {
      return getCurrentLogContext().isLogged("error");
   }

   public static void error(Throwable error) {
      getCurrentLogContext().logError(error, (CharSequence)null);
   }

   public static void error(Throwable error, CharSequence message) {
      getCurrentLogContext().logError(error, message);
   }

   public static void error(Throwable error, Object message) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("error")) {
         logContext.logError(error, Text.valueOf(message));
      }
   }

   public static void error(Throwable error, Object... messages) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("error")) {
         Text tmp = Text.valueOf(messages[0]);

         for(int i = 1; i < messages.length; ++i) {
            tmp = tmp.plus(messages[i]);
         }

         logContext.logError(error, tmp);
      }
   }

   public static void error(CharSequence message) {
      getCurrentLogContext().logError((Throwable)null, message);
   }

   public static void error(Object message) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("error")) {
         logContext.logError((Throwable)null, Text.valueOf(message));
      }
   }

   public static void error(Object... messages) {
      LogContext logContext = getCurrentLogContext();
      if (logContext.isLogged("error")) {
         Text tmp = Text.valueOf(messages[0]);

         for(int i = 1; i < messages.length; ++i) {
            tmp = tmp.plus(messages[i]);
         }

         logContext.logError((Throwable)null, tmp);
      }
   }

   protected abstract void logMessage(String var1, CharSequence var2);

   protected boolean isLogged(String category) {
      return true;
   }

   protected void logDebug(CharSequence message) {
      this.logMessage("debug", message);
   }

   protected void logInfo(CharSequence message) {
      this.logMessage("info", message);
   }

   protected void logWarning(CharSequence message) {
      this.logMessage("warning", message);
   }

   protected void logError(Throwable error, CharSequence message) {
      TextBuilder tmp = TextBuilder.newInstance();

      try {
         if (error != null) {
            tmp.append(error.getClass().getName());
            tmp.append(" - ");
         }

         if (message != null) {
            tmp.append(message);
         } else if (error != null) {
            tmp.append(error.getMessage());
         }

         if (error != null) {
            StackTraceElement[] trace = error.getStackTrace();

            for(int i = 0; i < trace.length; ++i) {
               tmp.append("\n\tat ");
               tmp.append((Object)trace[i]);
            }
         }

         this.logMessage("error", tmp);
      } finally {
         TextBuilder.recycle(tmp);
      }

   }

   protected void enterAction() {
   }

   protected void exitAction() {
   }

   static {
      DEFAULT = new Configurable(STANDARD) {
         protected void notifyChange(Object oldValue, Object newValue) {
            LogContext._Default = (LogContext)ObjectFactory.getInstance((Class)newValue).object();
         }
      };
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Console();
         }
      }, CONSOLE);
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Null();
         }
      }, NULL);
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new SystemOut();
         }
      }, SYSTEM_OUT);
   }

   private static class SystemOut extends LogContext {
      private SystemOut() {
      }

      protected void logMessage(String category, CharSequence message) {
         System.out.print("[");
         System.out.print(category);
         System.out.print("] ");
         System.out.println(message);
      }
   }

   private static final class Null extends SystemOut {
      private Null() {
      }

      protected boolean isLogged(String category) {
         return false;
      }

      protected void logMessage(String category, CharSequence message) {
      }
   }

   private static class Console extends SystemOut {
      private Console() {
      }
   }
}
