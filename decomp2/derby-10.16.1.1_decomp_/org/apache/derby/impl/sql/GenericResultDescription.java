package org.apache.derby.impl.sql;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.ResultSetMetaData;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class GenericResultDescription implements ResultDescription, Formatable {
   private ResultColumnDescriptor[] columns;
   private String statementType;
   private transient ResultSetMetaData metaData;
   private Map columnNameMap;

   public GenericResultDescription() {
   }

   public GenericResultDescription(ResultColumnDescriptor[] var1, String var2) {
      this.columns = (ResultColumnDescriptor[])ArrayUtil.copy(var1);
      this.statementType = var2;
   }

   public GenericResultDescription(ResultDescription var1, int[] var2) {
      this.columns = new ResultColumnDescriptor[var2.length];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         this.columns[var3] = var1.getColumnDescriptor(var2[var3]);
      }

      this.statementType = var1.getStatementType();
   }

   public String getStatementType() {
      return this.statementType;
   }

   public int getColumnCount() {
      return this.columns == null ? 0 : this.columns.length;
   }

   public ResultColumnDescriptor[] getColumnInfo() {
      return (ResultColumnDescriptor[])ArrayUtil.copy(this.columns);
   }

   public ResultColumnDescriptor getColumnInfo(int var1) {
      return this.columns[var1];
   }

   public ResultColumnDescriptor getColumnDescriptor(int var1) {
      return this.columns[var1 - 1];
   }

   public ResultDescription truncateColumns(int var1) {
      ResultColumnDescriptor[] var2 = new ResultColumnDescriptor[var1 - 1];
      System.arraycopy(this.columns, 0, var2, 0, var2.length);
      return new GenericResultDescription(var2, this.statementType);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      int var2 = this.columns == null ? 0 : this.columns.length;
      var1.writeObject(this.statementType);
      var1.writeInt(var2);

      for(; var2-- > 0; var1.writeObject(this.columns[var2])) {
         if (!(this.columns[var2] instanceof GenericColumnDescriptor)) {
            this.columns[var2] = new GenericColumnDescriptor(this.columns[var2]);
         }
      }

   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.columns = null;
      this.statementType = (String)var1.readObject();
      int var2 = var1.readInt();
      if (var2 > 0) {
         for(this.columns = new GenericColumnDescriptor[var2]; var2-- > 0; this.columns[var2] = (ResultColumnDescriptor)var1.readObject()) {
         }
      }

   }

   public int getTypeFormatId() {
      return 228;
   }

   public String toString() {
      return "";
   }

   public synchronized void setMetaData(ResultSetMetaData var1) {
      if (this.metaData == null) {
         this.metaData = var1;
      }

   }

   public synchronized ResultSetMetaData getMetaData() {
      return this.metaData;
   }

   public int findColumnInsenstive(String var1) {
      Map var2;
      synchronized(this) {
         if (this.columnNameMap == null) {
            HashMap var4 = new HashMap();

            for(int var5 = this.getColumnCount(); var5 >= 1; --var5) {
               String var6 = StringUtil.SQLToUpperCase(this.getColumnDescriptor(var5).getName());
               Integer var7 = var5;
               var4.put(var6, var7);
            }

            this.columnNameMap = Collections.unmodifiableMap(var4);
         }

         var2 = this.columnNameMap;
      }

      Integer var3 = (Integer)var2.get(var1);
      if (var3 == null) {
         var3 = (Integer)var2.get(StringUtil.SQLToUpperCase(var1));
      }

      return var3 == null ? -1 : var3;
   }
}
