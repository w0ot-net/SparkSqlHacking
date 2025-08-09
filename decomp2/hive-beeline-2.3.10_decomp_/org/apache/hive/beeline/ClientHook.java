package org.apache.hive.beeline;

public abstract class ClientHook {
   protected String sql;

   public ClientHook(String sql) {
      this.sql = sql;
   }

   abstract void postHook(BeeLine var1);
}
