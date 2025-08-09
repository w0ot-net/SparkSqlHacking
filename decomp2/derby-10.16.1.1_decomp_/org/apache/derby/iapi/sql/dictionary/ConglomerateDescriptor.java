package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.util.ArrayUtil;

public final class ConglomerateDescriptor extends UniqueTupleDescriptor implements Provider {
   private long conglomerateNumber;
   private String name;
   private transient String[] columnNames;
   private final boolean indexable;
   private final boolean forConstraint;
   private final IndexRowGenerator indexRowGenerator;
   private final UUID uuid;
   private final UUID tableID;
   private final UUID schemaID;

   ConglomerateDescriptor(DataDictionary var1, long var2, String var4, boolean var5, IndexRowGenerator var6, boolean var7, UUID var8, UUID var9, UUID var10) {
      super(var1);
      this.conglomerateNumber = var2;
      this.name = var4;
      this.indexable = var5;
      this.indexRowGenerator = var6;
      this.forConstraint = var7;
      if (var8 == null) {
         UUIDFactory var11 = DataDescriptorGenerator.getMonitor().getUUIDFactory();
         var8 = var11.createUUID();
      }

      this.uuid = var8;
      this.tableID = var9;
      this.schemaID = var10;
   }

   public long getConglomerateNumber() {
      return this.conglomerateNumber;
   }

   public void setConglomerateNumber(long var1) {
      this.conglomerateNumber = var1;
   }

   public UUID getUUID() {
      return this.uuid;
   }

   public UUID getTableID() {
      return this.tableID;
   }

   public UUID getSchemaID() {
      return this.schemaID;
   }

   public boolean isIndex() {
      return this.indexable;
   }

   public boolean isConstraint() {
      return this.forConstraint;
   }

   public String getConglomerateName() {
      return this.name;
   }

   public void setConglomerateName(String var1) {
      this.name = var1;
   }

   public IndexRowGenerator getIndexDescriptor() {
      return this.indexRowGenerator;
   }

   public void setColumnNames(String[] var1) {
      this.columnNames = (String[])ArrayUtil.copy(var1);
   }

   public String[] getColumnNames() {
      return (String[])ArrayUtil.copy(this.columnNames);
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(135);
   }

   public String getObjectName() {
      return this.name;
   }

   public UUID getObjectID() {
      return this.uuid;
   }

   public String getClassType() {
      return this.indexable ? "Index" : "Heap";
   }

   public String toString() {
      return "";
   }

   public String getDescriptorType() {
      return this.indexable ? "Index" : "Table";
   }

   public String getDescriptorName() {
      return this.name;
   }

   public ConglomerateDescriptor drop(LanguageConnectionContext var1, TableDescriptor var2) throws StandardException {
      DataDictionary var3 = this.getDataDictionary();
      DependencyManager var4 = var3.getDependencyManager();
      TransactionController var5 = var1.getTransactionExecute();
      var4.invalidateFor(this, 2, var1);
      ConglomerateDescriptor[] var6 = var3.getConglomerateDescriptors(this.getConglomerateNumber());
      boolean var7 = false;
      ConglomerateDescriptor var8 = null;
      if (var6.length == 1) {
         var7 = true;
      } else {
         var8 = this.describeSharedConglomerate(var6, true);
         IndexRowGenerator var10 = var8.getIndexDescriptor();
         boolean var9 = this.indexRowGenerator.isUnique() && !var10.isUnique() || this.indexRowGenerator.isUniqueWithDuplicateNulls() && !var10.isUniqueWithDuplicateNulls();
         if (var9) {
            var7 = true;
         } else {
            var8 = null;
         }
      }

      var3.dropStatisticsDescriptors(var2.getUUID(), this.getUUID(), var5);
      if (var7) {
         var5.dropConglomerate(this.getConglomerateNumber());
      }

      var3.dropConglomerateDescriptor(this, var5);
      var2.removeConglomerateDescriptor(this);
      return var8;
   }

   public ConglomerateDescriptor describeSharedConglomerate(ConglomerateDescriptor[] var1, boolean var2) throws StandardException {
      if (!this.isIndex()) {
         ConglomerateDescriptor var5 = null;

         for(int var6 = 0; var6 < var1.length; ++var6) {
            if (this.getConglomerateNumber() == var1[var6].getConglomerateNumber()) {
               var5 = var1[var6];
            }
         }

         return var5;
      } else {
         ConglomerateDescriptor var3 = null;

         for(int var4 = 0; var4 < var1.length; ++var4) {
            if (var1[var4].isIndex() && this.getConglomerateNumber() == var1[var4].getConglomerateNumber() && (!var2 || !this.getUUID().equals(var1[var4].getUUID()) || !this.getConglomerateName().equals(var1[var4].getConglomerateName()))) {
               if (var1[var4].getIndexDescriptor().isUnique()) {
                  var3 = var1[var4];
                  break;
               }

               if (var1[var4].getIndexDescriptor().isUniqueWithDuplicateNulls()) {
                  var3 = var1[var4];
               } else if (var3 == null) {
                  var3 = var1[var4];
               }
            }
         }

         return var3;
      }
   }
}
