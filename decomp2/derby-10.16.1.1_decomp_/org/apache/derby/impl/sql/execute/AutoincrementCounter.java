package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class AutoincrementCounter {
   private Long start;
   private long increment;
   private String identity;
   private long finalValue;
   private String schemaName;
   private String tableName;
   private String columnName;
   private long counter;
   private int columnPosition;
   private boolean initialized = false;

   public AutoincrementCounter(Long var1, long var2, long var4, String var6, String var7, String var8, int var9) {
      this.increment = var2;
      this.start = var1;
      this.initialized = false;
      this.identity = makeIdentity(var6, var7, var8);
      this.finalValue = var4;
      this.schemaName = var6;
      this.tableName = var7;
      this.columnName = var8;
      this.columnPosition = var9;
   }

   public static String makeIdentity(String var0, String var1, String var2) {
      return var0 + "." + var1 + "." + var2;
   }

   public static String makeIdentity(TableDescriptor var0, ColumnDescriptor var1) {
      String var10000 = var0.getSchemaName();
      return var10000 + "." + var0.getName() + "." + var1.getColumnName();
   }

   public void reset(boolean var1) {
      if (var1) {
         this.initialized = false;
      } else {
         this.counter = this.finalValue;
         this.initialized = true;
      }

   }

   public long update(long var1) {
      this.counter = var1;
      this.initialized = true;
      return this.counter;
   }

   public long update() throws StandardException {
      if (!this.initialized) {
         this.initialized = true;
         if (this.start == null) {
            throw StandardException.newException("42Z25", new Object[0]);
         }

         this.counter = this.start;
      } else {
         this.counter += this.increment;
      }

      return this.counter;
   }

   public Long getCurrentValue() {
      return !this.initialized ? null : this.counter;
   }

   public String getIdentity() {
      return this.identity;
   }

   public void flushToDisk(TransactionController var1, DataDictionary var2, UUID var3) throws StandardException {
      var2.setAutoincrementValue(var1, var3, this.columnName, this.counter, true);
   }

   public int getColumnPosition() {
      return this.columnPosition;
   }

   public Long getStartValue() {
      return this.start;
   }

   public String toString() {
      return "counter: " + this.identity + " current: " + this.counter + " start: " + this.start + " increment: " + this.increment + " final: " + this.finalValue;
   }
}
