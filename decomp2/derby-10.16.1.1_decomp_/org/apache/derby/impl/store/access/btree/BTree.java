package org.apache.derby.impl.store.access.btree;

import java.io.DataInput;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatIdUtil;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.conglomerate.ConglomerateUtil;
import org.apache.derby.impl.store.access.conglomerate.GenericConglomerate;
import org.apache.derby.impl.store.access.conglomerate.OpenConglomerateScratchSpace;
import org.apache.derby.impl.store.access.conglomerate.TemplateRow;
import org.apache.derby.shared.common.error.StandardException;

public abstract class BTree extends GenericConglomerate {
   public static final long ROOTPAGEID = 1L;
   public static final String PROPERTY_MAX_ROWS_PER_PAGE_PARAMETER = null;
   public static final String PROPERTY_ALLOWDUPLICATES = "allowDuplicates";
   public static final String PROPERTY_NKEYFIELDS = "nKeyFields";
   public static final String PROPERTY_NUNIQUECOLUMNS = "nUniqueColumns";
   public static final String PROPERTY_PARENTLINKS = "maintainParentLinks";
   public static final String PROPERTY_UNIQUE_WITH_DUPLICATE_NULLS = "uniqueWithDuplicateNulls";
   protected ContainerKey id;
   protected int nKeyFields;
   int nUniqueColumns;
   boolean allowDuplicates;
   boolean maintainParentLinks;
   boolean uniqueWithDuplicateNulls = false;
   static int maxRowsPerPage = Integer.MAX_VALUE;
   protected int conglom_format_id;
   protected int[] format_ids;
   protected boolean[] ascDescInfo;
   protected int[] collation_ids;
   protected boolean hasCollatedTypes;

   protected abstract BTreeLockingPolicy getBtreeLockingPolicy(Transaction var1, int var2, int var3, int var4, ConglomerateController var5, OpenBTree var6) throws StandardException;

   public abstract ConglomerateController lockTable(TransactionManager var1, int var2, int var3, int var4) throws StandardException;

   final DataValueDescriptor[] createBranchTemplate(Transaction var1, DataValueDescriptor var2) throws StandardException {
      return TemplateRow.newBranchRow(var1, this.format_ids, this.collation_ids, var2);
   }

   public final DataValueDescriptor[] createTemplate(Transaction var1) throws StandardException {
      return TemplateRow.newRow(var1, (FormatableBitSet)null, this.format_ids, this.collation_ids);
   }

   public final boolean isUnique() {
      return this.nKeyFields != this.nUniqueColumns;
   }

   public void setUniqueWithDuplicateNulls(boolean var1) {
      this.uniqueWithDuplicateNulls = var1;
   }

   public boolean isUniqueWithDuplicateNulls() {
      return this.uniqueWithDuplicateNulls;
   }

   public void addColumn(TransactionManager var1, int var2, Storable var3, int var4) throws StandardException {
      throw StandardException.newException("XSCB3.S", new Object[0]);
   }

   public final ContainerKey getId() {
      return this.id;
   }

   public void create(Transaction var1, int var2, long var3, DataValueDescriptor[] var5, Properties var6, int var7, int var8) throws StandardException {
      if (var6 == null) {
         throw StandardException.newException("XSCB2.S", new Object[]{"nKeyFields"});
      } else {
         this.allowDuplicates = Boolean.valueOf(var6.getProperty("allowDuplicates", "false"));
         String var9 = var6.getProperty("nKeyFields");
         if (var9 == null) {
            throw StandardException.newException("XSCB2.S", new Object[]{"nKeyFields"});
         } else {
            this.nKeyFields = Integer.parseInt(var9);
            var9 = var6.getProperty("nUniqueColumns");
            if (var9 == null) {
               throw StandardException.newException("XSCB2.S", new Object[]{"nUniqueColumns"});
            } else {
               this.nUniqueColumns = Integer.parseInt(var9);
               var9 = var6.getProperty("uniqueWithDuplicateNulls", "false");
               this.uniqueWithDuplicateNulls = Boolean.parseBoolean(var9);
               this.maintainParentLinks = Boolean.valueOf(var6.getProperty("maintainParentLinks", "true"));
               this.format_ids = ConglomerateUtil.createFormatIds(var5);
               this.conglom_format_id = var7;
               var6.put("derby.storage.pageReservedSpace", "0");
               var6.put("derby.storage.minimumRecordSize", "1");
               var6.put("derby.storage.reusableRecordId", "true");
               long var10 = var1.addContainer((long)var2, var3, 0, var6, var8);
               if (var10 <= 0L) {
                  throw StandardException.newException("XSCB0.S", new Object[0]);
               } else {
                  this.id = new ContainerKey((long)var2, var10);
               }
            }
         }
      }
   }

   public abstract void drop(TransactionManager var1) throws StandardException;

   public abstract long load(TransactionManager var1, boolean var2, RowLocationRetRowSource var3) throws StandardException;

   public long getContainerid() {
      return this.id.getContainerId();
   }

   public DynamicCompiledOpenConglomInfo getDynamicCompiledConglomInfo() throws StandardException {
      return new OpenConglomerateScratchSpace(this.format_ids, this.collation_ids, this.hasCollatedTypes);
   }

   public boolean isTemporary() {
      return this.id.getSegmentId() == -1L;
   }

   public abstract ConglomerateController open(TransactionManager var1, Transaction var2, boolean var3, int var4, int var5, LockingPolicy var6, StaticCompiledOpenConglomInfo var7, DynamicCompiledOpenConglomInfo var8) throws StandardException;

   public boolean isNull() {
      return this.id == null;
   }

   public void restoreToNull() {
      this.id = null;
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
      this.conglom_format_id = FormatIdUtil.readFormatIdInteger((DataInput)var1);
      long var2 = var1.readLong();
      int var4 = var1.readInt();
      this.nKeyFields = var1.readInt();
      this.nUniqueColumns = var1.readInt();
      this.allowDuplicates = var1.readBoolean();
      this.maintainParentLinks = var1.readBoolean();
      this.format_ids = ConglomerateUtil.readFormatIdArray(this.nKeyFields, var1);
      this.id = new ContainerKey((long)var4, var2);
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
      FormatIdUtil.writeFormatIdInteger(var1, this.conglom_format_id);
      var1.writeLong(this.id.getContainerId());
      var1.writeInt((int)this.id.getSegmentId());
      var1.writeInt(this.nKeyFields);
      var1.writeInt(this.nUniqueColumns);
      var1.writeBoolean(this.allowDuplicates);
      var1.writeBoolean(this.maintainParentLinks);
      ConglomerateUtil.writeFormatIdArray(this.format_ids, var1);
   }

   public String toString() {
      return super.toString();
   }
}
