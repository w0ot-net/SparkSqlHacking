package org.apache.derby.iapi.util;

import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.shared.common.error.ShutdownException;
import org.apache.derby.shared.common.error.StandardException;

public class InterruptStatus {
   public static final int MAX_INTERRUPT_RETRIES = 120;
   public static final int INTERRUPT_RETRY_SLEEP = 500;
   private static final ThreadLocal exception = new ThreadLocal();

   public static void setInterrupted() {
      LanguageConnectionContext var0 = null;

      try {
         var0 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      } catch (ShutdownException var2) {
      }

      Thread.interrupted();
      StandardException var1 = StandardException.newException("08000", new Object[0]);
      if (var0 != null) {
         var0.setInterruptedException(var1);
      } else {
         exception.set(var1);
      }

   }

   public static void saveInfoFromLcc(LanguageConnectionContext var0) {
      StandardException var1 = var0.getInterruptedException();
      if (var1 != null) {
         exception.set(var1);
      }

   }

   public static boolean noteAndClearInterrupt(String var0, int var1, int var2) {
      if (Thread.currentThread().isInterrupted()) {
         setInterrupted();
         Thread.interrupted();
         return true;
      } else {
         return false;
      }
   }

   public static void restoreIntrFlagIfSeen() {
      LanguageConnectionContext var0 = null;

      try {
         var0 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      } catch (ShutdownException var2) {
      }

      if (var0 == null) {
         if (exception.get() != null) {
            exception.set((Object)null);
            Thread.currentThread().interrupt();
         }
      } else if (var0.getInterruptedException() != null) {
         var0.setInterruptedException((StandardException)null);
         Thread.currentThread().interrupt();
      }

   }

   public static void restoreIntrFlagIfSeen(LanguageConnectionContext var0) {
      if (var0.getInterruptedException() != null) {
         var0.setInterruptedException((StandardException)null);
         Thread.currentThread().interrupt();
      }

   }

   public static void throwIf(LanguageConnectionContext var0) throws StandardException {
      if (Thread.currentThread().isInterrupted()) {
         setInterrupted();
      }

      StandardException var1 = var0.getInterruptedException();
      if (var1 != null) {
         var0.setInterruptedException((StandardException)null);
         throw var1;
      }
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }
}
