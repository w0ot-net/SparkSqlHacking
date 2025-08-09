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
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public class UpdateConstantAction extends WriteCursorConstantAction {
   int[] changedColumnIds;
   private boolean positionedUpdate;
   int numColumns;
   private String schemaName;
   private String tableName;
   private String[] columnNames;
   String identitySequenceUUIDString;
   RowLocation[] autoincRowLocation;
   private long[] autoincIncrement;

   public UpdateConstantAction() {
   }

   UpdateConstantAction(TableDescriptor var1, StaticCompiledOpenConglomInfo var2, IndexRowGenerator[] var3, long[] var4, StaticCompiledOpenConglomInfo[] var5, String[] var6, boolean var7, UUID var8, int var9, int[] var10, FKInfo[] var11, TriggerInfo var12, FormatableBitSet var13, int[] var14, int[] var15, int var16, boolean var17, boolean var18, RowLocation[] var19, boolean var20, String var21) throws StandardException {
      super(var1.getHeapConglomerateId(), var2, var3, var4, var5, var6, var7, (Properties)null, var8, var9, var11, var12, var13, var14, var15, var18, var20);
      this.changedColumnIds = var10;
      this.positionedUpdate = var17;
      this.numColumns = var16;
      this.schemaName = var1.getSchemaName();
      this.tableName = var1.getName();
      this.columnNames = var1.getColumnNamesArray();
      this.autoincIncrement = var1.getAutoincIncrementArray();
      this.identitySequenceUUIDString = var21;
      this.autoincRowLocation = var19;
   }

   public boolean hasAutoincrement() {
      return this.autoincRowLocation != null;
   }

   RowLocation[] getAutoincRowLocation() {
      return this.autoincRowLocation;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      super.readExternal(var1);
      this.changedColumnIds = ArrayUtil.readIntArray(var1);
      this.positionedUpdate = var1.readBoolean();
      this.numColumns = var1.readInt();
      this.autoincIncrement = ArrayUtil.readLongArray(var1);
      this.identitySequenceUUIDString = (String)var1.readObject();
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      super.writeExternal(var1);
      ArrayUtil.writeIntArray(var1, this.changedColumnIds);
      var1.writeBoolean(this.positionedUpdate);
      var1.writeInt(this.numColumns);
      ArrayUtil.writeLongArray(var1, this.autoincIncrement);
      var1.writeObject(this.identitySequenceUUIDString);
   }

   public int getTypeFormatId() {
      return 39;
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
}
