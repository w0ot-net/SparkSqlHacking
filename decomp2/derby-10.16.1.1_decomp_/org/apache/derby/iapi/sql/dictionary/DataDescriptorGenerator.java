package org.apache.derby.iapi.sql.dictionary;

import java.sql.Timestamp;
import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class DataDescriptorGenerator {
   private UUIDFactory uuidf;
   protected final DataDictionary dataDictionary;

   public DataDescriptorGenerator(DataDictionary var1) {
      this.dataDictionary = var1;
   }

   public SchemaDescriptor newSchemaDescriptor(String var1, String var2, UUID var3) throws StandardException {
      return new SchemaDescriptor(this.dataDictionary, var1, var2, var3, this.dataDictionary.isSystemSchemaName(var1));
   }

   public TableDescriptor newTableDescriptor(String var1, SchemaDescriptor var2, int var3, char var4) {
      return new TableDescriptor(this.dataDictionary, var1, var2, var3, var4);
   }

   public TableDescriptor newTableDescriptor(String var1, SchemaDescriptor var2, int var3, boolean var4, boolean var5) {
      return new TableDescriptor(this.dataDictionary, var1, var2, var3, var4, var5);
   }

   public ViewDescriptor newViewDescriptor(UUID var1, String var2, String var3, int var4, UUID var5) {
      return new ViewDescriptor(this.dataDictionary, var1, var2, var3, var4, var5);
   }

   public ReferencedKeyConstraintDescriptor newUniqueConstraintDescriptor(TableDescriptor var1, String var2, boolean var3, boolean var4, int[] var5, UUID var6, UUID var7, SchemaDescriptor var8, boolean var9, int var10) {
      return new ReferencedKeyConstraintDescriptor(3, this.dataDictionary, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   public ReferencedKeyConstraintDescriptor newPrimaryKeyConstraintDescriptor(TableDescriptor var1, String var2, boolean var3, boolean var4, int[] var5, UUID var6, UUID var7, SchemaDescriptor var8, boolean var9, int var10) {
      return new ReferencedKeyConstraintDescriptor(2, this.dataDictionary, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   public ForeignKeyConstraintDescriptor newForeignKeyConstraintDescriptor(TableDescriptor var1, String var2, boolean var3, boolean var4, int[] var5, UUID var6, UUID var7, SchemaDescriptor var8, ReferencedKeyConstraintDescriptor var9, boolean var10, int var11, int var12) {
      return new ForeignKeyConstraintDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }

   public ForeignKeyConstraintDescriptor newForeignKeyConstraintDescriptor(TableDescriptor var1, String var2, boolean var3, boolean var4, int[] var5, UUID var6, UUID var7, SchemaDescriptor var8, UUID var9, boolean var10, int var11, int var12) {
      return new ForeignKeyConstraintDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
   }

   public CheckConstraintDescriptor newCheckConstraintDescriptor(TableDescriptor var1, String var2, boolean var3, boolean var4, UUID var5, String var6, ReferencedColumns var7, SchemaDescriptor var8, boolean var9) {
      return new CheckConstraintDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public CheckConstraintDescriptor newCheckConstraintDescriptor(TableDescriptor var1, String var2, boolean var3, boolean var4, UUID var5, String var6, int[] var7, SchemaDescriptor var8, boolean var9) {
      ReferencedColumnsDescriptorImpl var10 = new ReferencedColumnsDescriptorImpl(var7);
      return new CheckConstraintDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var10, var8, var9);
   }

   public ConglomerateDescriptor newConglomerateDescriptor(long var1, String var3, boolean var4, IndexRowGenerator var5, boolean var6, UUID var7, UUID var8, UUID var9) {
      return new ConglomerateDescriptor(this.dataDictionary, var1, var3, var4, var5, var6, var7, var8, var9);
   }

   public TriggerDescriptor newTriggerDescriptor(SchemaDescriptor var1, UUID var2, String var3, int var4, boolean var5, boolean var6, boolean var7, TableDescriptor var8, UUID var9, UUID var10, Timestamp var11, int[] var12, int[] var13, String var14, boolean var15, boolean var16, String var17, String var18, String var19) throws StandardException {
      return new TriggerDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19);
   }

   protected UUIDFactory getUUIDFactory() {
      if (this.uuidf == null) {
         this.uuidf = getMonitor().getUUIDFactory();
      }

      return this.uuidf;
   }

   public FileInfoDescriptor newFileInfoDescriptor(UUID var1, SchemaDescriptor var2, String var3, long var4) {
      return new FileInfoDescriptor(this.dataDictionary, var1, var2, var3, var4);
   }

   public UserDescriptor newUserDescriptor(String var1, String var2, char[] var3, Timestamp var4) {
      return new UserDescriptor(this.dataDictionary, var1, var2, var3, var4);
   }

   public TablePermsDescriptor newTablePermsDescriptor(TableDescriptor var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8) throws StandardException {
      return "N".equals(var2) && "N".equals(var3) && "N".equals(var4) && "N".equals(var5) && "N".equals(var6) && "N".equals(var7) ? null : new TablePermsDescriptor(this.dataDictionary, (String)null, var8, var1.getUUID(), var2, var3, var4, var5, var6, var7);
   }

   public ColPermsDescriptor newColPermsDescriptor(TableDescriptor var1, String var2, FormatableBitSet var3, String var4) throws StandardException {
      return new ColPermsDescriptor(this.dataDictionary, (String)null, var4, var1.getUUID(), var2, var3);
   }

   public RoutinePermsDescriptor newRoutinePermsDescriptor(AliasDescriptor var1, String var2) throws StandardException {
      return new RoutinePermsDescriptor(this.dataDictionary, (String)null, var2, var1.getUUID());
   }

   public RoleGrantDescriptor newRoleGrantDescriptor(UUID var1, String var2, String var3, String var4, boolean var5, boolean var6) throws StandardException {
      return new RoleGrantDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6);
   }

   public SequenceDescriptor newSequenceDescriptor(SchemaDescriptor var1, UUID var2, String var3, DataTypeDescriptor var4, Long var5, long var6, long var8, long var10, long var12, boolean var14) {
      return new SequenceDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var8, var10, var12, var14);
   }

   public PermDescriptor newPermDescriptor(UUID var1, String var2, UUID var3, String var4, String var5, String var6, boolean var7) {
      return new PermDescriptor(this.dataDictionary, var1, var2, var3, var4, var5, var6, var7);
   }

   static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
