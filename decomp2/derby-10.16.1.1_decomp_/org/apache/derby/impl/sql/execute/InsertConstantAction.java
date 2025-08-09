package org.apache.derby.impl.sql.execute;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.util.ArrayUtil;

public class InsertConstantAction extends WriteCursorConstantAction {
   boolean[] indexedCols;
   private String schemaName;
   private String tableName;
   private String[] columnNames;
   RowLocation[] autoincRowLocation;
   private long[] autoincIncrement;
   private transient int firstAutoGenColumn = -1;
   public final boolean hasDeferrableChecks;
   String identitySequenceUUIDString;

   public InsertConstantAction() {
      this.hasDeferrableChecks = false;
   }

   InsertConstantAction(TableDescriptor var1, long var2, StaticCompiledOpenConglomInfo var4, IndexRowGenerator[] var5, long[] var6, StaticCompiledOpenConglomInfo[] var7, String[] var8, boolean var9, boolean var10, Properties var11, UUID var12, int var13, FKInfo[] var14, TriggerInfo var15, int[] var16, boolean[] var17, boolean var18, RowLocation[] var19, boolean var20, String var21) {
      super(var2, var4, var5, var6, var7, var8, var9, var11, var12, var13, var14, var15, (FormatableBitSet)null, (int[])null, var16, var18, var20);
      this.indexedCols = var17;
      this.autoincRowLocation = var19;
      this.schemaName = var1.getSchemaName();
      this.tableName = var1.getName();
      this.columnNames = var1.getColumnNamesArray();
      this.autoincIncrement = var1.getAutoincIncrementArray();
      this.indexNames = var8;
      this.hasDeferrableChecks = var10;
      this.identitySequenceUUIDString = var21;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      Object var2 = null;
      super.readExternal(var1);
      this.indexedCols = ArrayUtil.readBooleanArray(var1);
      var2 = ArrayUtil.readObjectArray(var1);
      if (var2 != null) {
         this.autoincRowLocation = new RowLocation[((Object[])var2).length];

         for(int var3 = 0; var3 < ((Object[])var2).length; ++var3) {
            this.autoincRowLocation[var3] = (RowLocation)((Object[])var2)[var3];
         }
      }

      this.schemaName = (String)var1.readObject();
      this.tableName = (String)var1.readObject();
      var2 = ArrayUtil.readObjectArray(var1);
      if (var2 != null) {
         this.columnNames = new String[((Object[])var2).length];

         for(int var6 = 0; var6 < ((Object[])var2).length; ++var6) {
            this.columnNames[var6] = (String)((Object[])var2)[var6];
         }
      }

      this.autoincIncrement = ArrayUtil.readLongArray(var1);
      this.identitySequenceUUIDString = (String)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      ArrayUtil.writeBooleanArray(var1, this.indexedCols);
      ArrayUtil.writeArray(var1, this.autoincRowLocation);
      var1.writeObject(this.schemaName);
      var1.writeObject(this.tableName);
      ArrayUtil.writeArray(var1, this.columnNames);
      ArrayUtil.writeLongArray(var1, this.autoincIncrement);
      var1.writeObject(this.identitySequenceUUIDString);
   }

   public int getAutoGenColumn() {
      if (!this.hasAutoincrement()) {
         return -1;
      } else {
         if (this.firstAutoGenColumn < 0) {
            for(int var1 = 0; var1 < this.autoincIncrement.length; ++var1) {
               if (this.autoincIncrement[var1] > 0L) {
                  this.firstAutoGenColumn = var1;
                  break;
               }
            }
         }

         return this.firstAutoGenColumn;
      }
   }

   public String getSchemaName() {
      return this.schemaName;
   }

   public String getTableName() {
      return this.tableName;
   }

   public String getColumnName(int var1) {
      return this.columnNames[var1];
   }

   String[] getColumnNames() {
      return this.columnNames;
   }

   public long getAutoincIncrement(int var1) {
      return this.autoincIncrement[var1];
   }

   public boolean hasAutoincrement() {
      return this.autoincRowLocation != null;
   }

   RowLocation[] getAutoincRowLocation() {
      return this.autoincRowLocation;
   }

   public int getTypeFormatId() {
      return 38;
   }
}
