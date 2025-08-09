package org.apache.derby.shared.common.error;

import java.sql.SQLException;

public abstract class ExceptionFactory {
   private static final ExceptionFactory INSTANCE;

   public static ExceptionFactory getInstance() {
      return INSTANCE;
   }

   public abstract SQLException getSQLException(String var1, String var2, SQLException var3, int var4, Throwable var5, Object... var6);

   public abstract SQLException getSQLException(String var1, SQLException var2, Throwable var3, Object... var4);

   static {
      String var0 = "org.apache.derby.impl.jdbc.SQLExceptionFactory";
      Object var1 = null;

      try {
         Class var2 = Class.forName(var0);
         var4 = (ExceptionFactory)var2.getConstructor().newInstance();
      } catch (Exception var3) {
         throw new ExceptionInInitializerError(var3);
      }

      INSTANCE = var4;
   }
}
