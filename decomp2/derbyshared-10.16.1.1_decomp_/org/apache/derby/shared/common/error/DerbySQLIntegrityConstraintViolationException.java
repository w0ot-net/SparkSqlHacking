package org.apache.derby.shared.common.error;

import java.sql.SQLIntegrityConstraintViolationException;

public class DerbySQLIntegrityConstraintViolationException extends SQLIntegrityConstraintViolationException {
   private String tableName;
   private String constraintName;

   public DerbySQLIntegrityConstraintViolationException(String var1, String var2, int var3, Throwable var4, Object var5, Object var6) {
      super(var1, var2, var3, var4);
      this.tableName = var6.toString();
      this.constraintName = var5.toString();
   }

   public DerbySQLIntegrityConstraintViolationException(String var1, String var2, int var3, Object var4, Object var5) {
      super(var1, var2, var3);
      this.tableName = var5.toString();
      this.constraintName = var4.toString();
   }

   public String getTableName() {
      return this.tableName;
   }

   public String getConstraintName() {
      return this.constraintName;
   }
}
