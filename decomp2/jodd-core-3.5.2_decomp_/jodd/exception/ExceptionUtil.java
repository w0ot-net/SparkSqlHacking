package jodd.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ExceptionUtil {
   public static StackTraceElement[] getCurrentStackTrace() {
      StackTraceElement[] ste = (new Exception()).getStackTrace();
      if (ste.length > 1) {
         StackTraceElement[] result = new StackTraceElement[ste.length - 1];
         System.arraycopy(ste, 1, result, 0, ste.length - 1);
         return result;
      } else {
         return ste;
      }
   }

   public static StackTraceElement[] getStackTrace(Throwable t, String[] allow, String[] deny) {
      StackTraceElement[] st = t.getStackTrace();
      ArrayList<StackTraceElement> result = new ArrayList(st.length);

      label47:
      for(StackTraceElement element : st) {
         String className = element.getClassName();
         if (allow != null) {
            boolean validElemenet = false;

            for(String filter : allow) {
               if (className.indexOf(filter) != -1) {
                  validElemenet = true;
                  break;
               }
            }

            if (!validElemenet) {
               continue;
            }
         }

         if (deny != null) {
            for(String filter : deny) {
               if (className.indexOf(filter) != -1) {
                  continue label47;
               }
            }
         }

         result.add(element);
      }

      st = new StackTraceElement[result.size()];
      return (StackTraceElement[])result.toArray(st);
   }

   public static StackTraceElement[][] getStackTraceChain(Throwable t, String[] allow, String[] deny) {
      ArrayList<StackTraceElement[]> result;
      for(result = new ArrayList(); t != null; t = t.getCause()) {
         StackTraceElement[] stack = getStackTrace(t, allow, deny);
         result.add(stack);
      }

      StackTraceElement[][] allStacks = new StackTraceElement[result.size()][];

      for(int i = 0; i < allStacks.length; ++i) {
         allStacks[i] = (StackTraceElement[])result.get(i);
      }

      return allStacks;
   }

   public static Throwable[] getExceptionChain(Throwable throwable) {
      ArrayList<Throwable> list = new ArrayList();
      list.add(throwable);

      while((throwable = throwable.getCause()) != null) {
         list.add(throwable);
      }

      Throwable[] result = new Throwable[list.size()];
      return (Throwable[])list.toArray(result);
   }

   public static String exceptionToString(Throwable t) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw, true);
      t.printStackTrace(pw);
      pw.flush();
      sw.flush();
      return sw.toString();
   }

   public static String exceptionChainToString(Throwable t) {
      StringWriter sw = new StringWriter();

      PrintWriter pw;
      for(pw = new PrintWriter(sw, true); t != null; t = t.getCause()) {
         t.printStackTrace(pw);
      }

      pw.flush();
      sw.flush();
      return sw.toString();
   }

   public static String buildMessage(String message, Throwable cause) {
      if (cause != null) {
         cause = getRootCause(cause);
         StringBuilder buf = new StringBuilder();
         if (message != null) {
            buf.append(message).append("; ");
         }

         buf.append("<--- ").append(cause);
         return buf.toString();
      } else {
         return message;
      }
   }

   public static Throwable getRootCause(Throwable throwable) {
      Throwable cause = throwable.getCause();
      if (cause == null) {
         return throwable;
      } else {
         for(Throwable var2 = cause; (var2 = var2.getCause()) != null; cause = var2) {
         }

         return cause;
      }
   }

   public static Throwable findCause(Throwable throwable, Class cause) {
      while(throwable != null) {
         if (throwable.getClass().equals(cause)) {
            return throwable;
         }

         throwable = throwable.getCause();
      }

      return null;
   }

   public static SQLException rollupSqlExceptions(Collection exceptions) {
      SQLException parent = null;

      for(SQLException exception : exceptions) {
         if (parent != null) {
            exception.setNextException(parent);
         }

         parent = exception;
      }

      return parent;
   }

   public static void throwTargetException(InvocationTargetException itex) throws Exception {
      throw extractTargetException(itex);
   }

   public static Exception extractTargetException(InvocationTargetException itex) {
      Throwable target = itex.getTargetException();
      return (Exception)(target instanceof Exception ? (Exception)target : itex);
   }

   public static void throwExceptionAlt(Throwable throwable) {
      if (throwable instanceof RuntimeException) {
         throw (RuntimeException)throwable;
      } else {
         Thread.currentThread().stop(throwable);
      }
   }

   public static void throwException(Throwable throwable) {
      if (throwable instanceof RuntimeException) {
         throw (RuntimeException)throwable;
      } else if (!(throwable instanceof IllegalAccessException) && !(throwable instanceof InstantiationException)) {
         try {
            synchronized(ThrowableThrower.class) {
               ExceptionUtil.ThrowableThrower.throwable = throwable;
               ThrowableThrower.class.newInstance();
            }
         } catch (InstantiationException iex) {
            throw new RuntimeException(iex);
         } catch (IllegalAccessException iex) {
            throw new RuntimeException(iex);
         } finally {
            ExceptionUtil.ThrowableThrower.throwable = null;
         }

      } else {
         throw new IllegalArgumentException(throwable);
      }
   }

   private static class ThrowableThrower {
      private static Throwable throwable;

      ThrowableThrower() throws Throwable {
         throw throwable;
      }
   }
}
