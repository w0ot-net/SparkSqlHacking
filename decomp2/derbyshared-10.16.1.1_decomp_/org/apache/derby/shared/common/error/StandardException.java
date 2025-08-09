package org.apache.derby.shared.common.error;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import org.apache.derby.shared.common.i18n.MessageService;
import org.apache.derby.shared.common.util.ArrayUtil;

public class StandardException extends Exception {
   public static final int REPORT_DEFAULT = 0;
   public static final int REPORT_NEVER = 1;
   public static final int REPORT_ALWAYS = 2;
   private transient Object[] arguments;
   private int severity;
   private String textMessage;
   private String sqlState;
   private transient int report;
   private transient boolean isForPublicAPI;
   private transient SQLException next;

   protected StandardException(String var1) {
      this(var1, (Throwable)null, (Object[])null);
   }

   protected StandardException(String var1, Throwable var2, Object[] var3) {
      super(var1);
      this.severity = getSeverityFromIdentifier(var1);
      this.sqlState = getSQLStateFromIdentifier(var1);
      this.arguments = var3;
      if (var2 != null) {
         this.initCause(var2);
      }

   }

   private StandardException(String var1, String var2) {
      this(var1);
      this.textMessage = var2;
   }

   public final Object[] getArguments() {
      return ArrayUtil.copy(this.arguments);
   }

   public final int report() {
      return this.report;
   }

   public final void setReport(int var1) {
      this.report = var1;
   }

   public final void setSeverity(int var1) {
      this.severity = var1;
   }

   public final int getSeverity() {
      return this.severity;
   }

   public final int getErrorCode() {
      return this.severity;
   }

   public final String getSQLState() {
      return this.sqlState;
   }

   public final SQLException getNextException() {
      return this.next;
   }

   final void markAsPublicAPI() {
      this.isForPublicAPI = true;
   }

   public static String getSQLStateFromIdentifier(String var0) {
      return var0.length() == 5 ? var0 : var0.substring(0, 5);
   }

   public static int getSeverityFromIdentifier(String var0) {
      char var1 = 0;
      switch (var0.length()) {
         case 5:
            switch (var0.charAt(0)) {
               case '0':
                  switch (var0.charAt(1)) {
                     case '1':
                        var1 = 10000;
                        return var1;
                     case '7':
                     case 'A':
                        var1 = 20000;
                        return var1;
                     case '8':
                        var1 = '鱀';
                        return var1;
                  }
               case '1':
               default:
                  return var1;
               case '2':
               case '3':
                  var1 = 20000;
                  return var1;
               case '4':
                  switch (var0.charAt(1)) {
                     case '0':
                        var1 = 30000;
                        return var1;
                     case '2':
                        var1 = 20000;
                        return var1;
                     default:
                        return var1;
                  }
            }
         default:
            switch (var0.charAt(6)) {
               case 'C':
                  var1 = '鱀';
                  break;
               case 'D':
                  var1 = '꿈';
               case 'E':
               case 'F':
               case 'G':
               case 'H':
               case 'I':
               case 'J':
               case 'K':
               case 'L':
               case 'N':
               case 'O':
               case 'P':
               case 'Q':
               case 'R':
               default:
                  break;
               case 'M':
                  var1 = '썐';
                  break;
               case 'S':
                  var1 = 20000;
                  break;
               case 'T':
                  var1 = 30000;
                  break;
               case 'U':
                  var1 = 0;
            }
      }

      return var1;
   }

   public static StandardException normalClose() {
      StandardException var0 = newException("XXXXX.C.6");
      var0.report = 1;
      return var0;
   }

   public static StandardException newException(String var0, Object... var1) {
      return newException(var0, (Throwable)null, var1);
   }

   public static StandardException newException(String var0, Throwable var1, Object... var2) {
      return new StandardException(var0, var1, var2);
   }

   public static StandardException newException(String var0, Object var1, Throwable var2) throws BadMessageArgumentException {
      throw new BadMessageArgumentException();
   }

   public static StandardException newException(String var0, Object var1, Object var2, Throwable var3) throws BadMessageArgumentException {
      throw new BadMessageArgumentException();
   }

   public static StandardException newPreLocalizedException(String var0, Throwable var1, String var2) {
      StandardException var3 = new StandardException(var0, var2);
      if (var1 != null) {
         var3.initCause(var1);
      }

      return var3;
   }

   public static StandardException getArgumentFerry(SQLException var0) {
      Throwable var1 = var0.getCause();
      return var1 instanceof StandardException ? (StandardException)var1 : null;
   }

   private static boolean isVacuousWrapper(Throwable var0) {
      if (var0 instanceof InvocationTargetException) {
         return var0.getCause() != null;
      } else {
         return false;
      }
   }

   public static StandardException unexpectedUserException(Throwable var0) {
      if (isVacuousWrapper(var0)) {
         return unexpectedUserException(var0.getCause());
      } else {
         StandardException var1 = null;
         if (var0 instanceof SQLException) {
            SQLException var2 = (SQLException)var0;
            var1 = getArgumentFerry(var2);
            if (var1 != null && var1.isForPublicAPI) {
               var1.next = var2.getNextException();
               return var1;
            }
         }

         if (var0 instanceof SQLException && var1 == null) {
            SQLException var5 = (SQLException)var0;
            String var3 = var5.getSQLState();
            if (var3 != null && var3.length() == 5 && var3.startsWith("38") && !var3.equals("38000")) {
               StandardException var4 = new StandardException(var3, var5.getMessage());
               if (var5.getNextException() != null) {
                  var4.initCause(var5.getNextException());
               }

               return var4;
            }
         }

         if (var0 instanceof StandardException) {
            return (StandardException)var0;
         } else {
            String var6 = var0.getMessage();
            if (var6 == null) {
               var6 = "";
            } else {
               var6 = var6.trim();
            }

            if (var6.length() == 0) {
               var6 = var0.getClass().getName();
            } else {
               String var10000 = var0.getClass().getName();
               var6 = var10000 + ": " + var6;
            }

            return newException("38000", var0, var6);
         }
      }
   }

   public static StandardException plainWrapException(Throwable var0) {
      if (isVacuousWrapper(var0)) {
         return plainWrapException(var0.getCause());
      } else if (var0 instanceof StandardException) {
         return (StandardException)var0;
      } else {
         if (var0 instanceof SQLException) {
            SQLException var1 = (SQLException)var0;
            String var2 = var1.getSQLState();
            if (var2 != null) {
               int var10003 = var1.getErrorCode();
               StandardException var3 = new StandardException(var2, "(" + var10003 + ") " + var1.getMessage());
               var1 = var1.getNextException();
               if (var1 != null) {
                  var3.initCause(plainWrapException(var1));
               }

               return var3;
            }
         }

         String var4 = var0.getMessage();
         if (var4 == null) {
            var4 = "";
         } else {
            var4 = var4.trim();
         }

         StandardException var7 = newException("XJ001.U", var0, var4, var0.getClass().getName());
         return var7;
      }
   }

   public static StandardException closeException() {
      StandardException var0 = newException("close.C.1");
      var0.setReport(1);
      return var0;
   }

   public String getMessage() {
      if (this.textMessage == null) {
         this.textMessage = MessageService.getTextMessage(this.getMessageId(), this.getArguments());
      }

      return this.textMessage;
   }

   public final String getMessageId() {
      return super.getMessage();
   }

   public String getErrorProperty(String var1) {
      return getErrorProperty(this.getMessageId(), var1);
   }

   public String toString() {
      String var1 = this.getMessage();
      String var10000 = this.getSQLState();
      return "ERROR " + var10000 + ": " + var1;
   }

   private static String getErrorProperty(String var0, String var1) {
      return MessageService.getProperty(var0, var1);
   }

   public static StandardException interrupt(InterruptedException var0) {
      StandardException var1 = newException("08000", (Throwable)var0, (Object[])());
      return var1;
   }

   public static SQLWarning newWarning(String var0, Object... var1) {
      String var2 = MessageService.getTextMessage(var0, var1);
      String var3 = getSQLStateFromIdentifier(var0);
      SQLWarning var4 = new SQLWarning(var2, var3, 10000);
      return var4;
   }

   public final boolean isLockTimeout() {
      return "40XL1".equals(this.getSQLState());
   }

   public final boolean isSelfDeadlock() {
      return "40XL2".equals(this.getSQLState());
   }

   public final boolean isLockTimeoutOrDeadlock() {
      return "40XL1".equals(this.getSQLState()) || "40001".equals(this.getSQLState());
   }

   public static class BadMessageArgumentException extends Throwable {
   }
}
